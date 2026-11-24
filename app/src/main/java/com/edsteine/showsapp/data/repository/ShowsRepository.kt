// File: ShowsRepository.kt
// Date updated: 2024-10-15
// Author: Adil Ajdaa
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: ShowsRepository handles the logic of retrieving show data
//              from the remote ShowsClient. It provides methods to fetch
//              a list of shows, details of a specific show, and supports
//              paginated loading of shows.
// Used Libraries: Retrofit, Kotlin Coroutines, Dagger Hilt, AndroidX Paging

package com.edsteine.showsapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.edsteine.showsapp.data.model.ShowDetails
import com.edsteine.showsapp.data.model.ShowItem
import com.edsteine.showsapp.data.remote.ShowsClient
import com.edsteine.showsapp.data.utils.AppUtils.START_PAGE
import com.edsteine.showsapp.data.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ShowsRepository @Inject constructor(private val showsClient: ShowsClient) : PagingSource<Int, ShowItem>() {

    override fun getRefreshKey(state: PagingState<Int, ShowItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    // Flow to fetch paginated list of shows
    fun getShowsFlow(page: Int = START_PAGE): Flow<Resource<List<ShowItem>>> = flow {
        emit(Resource.Loading())
        emit(fetchShowsPage(page))
    }

    // Method to fetch shows page
    private suspend fun fetchShowsPage(page: Int): Resource<List<ShowItem>> {
        return try {
            val response = showsClient.getShowsList(page)
            if (response.isSuccessful) {
                val shows = response.body()?.shows?.values?.mapNotNull { show ->
                    // Handle nullable data; skip shows with incomplete data
                    if (show.webChannel != null) {
                        show
                    } else {
                        Timber.tag("ShowsRepository").w("Skipping show with incomplete data: ${show.id}. WebChannel is null.")
                        null
                    }
                } ?: emptyList()

                // Log a warning if no shows are found for the requested page
                if (shows.isEmpty()) {
                    Timber.tag("ShowsRepository").w("No shows found for page: $page")
                }

                Resource.Success(shows)
            } else {
                Timber.tag("ShowsRepository").e("Error fetching shows: ${response.errorBody()?.string()}")
                Resource.Error("Network Error ${response.code()} ${response.message()}")
            }
        } catch (e: IOException) {
            Timber.tag("ShowsRepository").e(e, "Network error: ${e.message}")
            Resource.Error("IOException: ${e.message}")
        } catch (e: HttpException) {
            Timber.tag("ShowsRepository").e(e, "HTTP error: ${e.message}")
            Resource.Error("HttpException: ${e.message}")
        } catch (e: Exception) {
            Timber.tag("ShowsRepository").e(e, "Unexpected error: ${e.message}")
            Resource.Error("An unexpected error occurred: ${e.message}")
        }
    }

    // Overridden load method for paginated list of shows
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowItem> {
        val position = params.key ?: START_PAGE
        Timber.tag("ShowsPagingSource").d("Loading page: $position")

        return when (val result = fetchShowsPage(position)) {
            is Resource.Success -> {
                LoadResult.Page(
                    data = result.data ?: emptyList(), // Handle the nullable case
                    prevKey = if (position == START_PAGE) null else position - 1,
                    nextKey = if (result.data.isNullOrEmpty()) null else position + 1
                )
            }
            is Resource.Error -> LoadResult.Error(Exception(result.message))
            else -> LoadResult.Error(Exception("Unexpected error occurred"))
        }
    }

    // Flow to fetch details of a specific show
    fun getShowDetailsFlow(showId: Int): Flow<Resource<ShowDetails>> = flow {
        emit(Resource.Loading())
        emit(getShowDetails(showId))
    }

    // Method to fetch show details
    private suspend fun getShowDetails(showId: Int): Resource<ShowDetails> {
        return try {
            val response = showsClient.getShowDetails(showId)
            if (response.isSuccessful) {
                val showDetails = response.body()
                if (showDetails != null) {
                    Resource.Success(showDetails)
                } else {
                    Resource.Error("Show details body is null")
                }
            } else {
                Resource.Error("Network Error ${response.code()} ${response.message()}")
            }
        } catch (e: IOException) {
            Resource.Error("Network error: ${e.message}")
        } catch (e: HttpException) {
            Resource.Error("HTTP error: ${e.message}")
        } catch (e: Exception) {
            Resource.Error("An unexpected error occurred: ${e.message}")
        }
    }
}
