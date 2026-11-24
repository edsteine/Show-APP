// File: ShowsViewModel.kt
// Date updated: 2024-10-15
// Author: Adil Ajdaa
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: ViewModel for managing the UI-related data for shows in the ShowsApp.
// It handles fetching show details and shows list, managing loading states, and error handling.
// Used Libraries: Android SDK, Kotlin Coroutines, Dagger Hilt

package com.edsteine.showsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edsteine.showsapp.data.model.ShowDetails
import com.edsteine.showsapp.data.model.ShowItem
import com.edsteine.showsapp.data.repository.ShowsRepository
import com.edsteine.showsapp.data.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(private val showsRepository: ShowsRepository) : ViewModel() {

    private val _showsUiState = MutableStateFlow<ShowsUiState>(ShowsUiState.Loading)
    val showsUiState: StateFlow<ShowsUiState> = _showsUiState

    private val _showsDetailsUiState = MutableStateFlow<ShowsDetailsUiState>(ShowsDetailsUiState.Loading)
    val showsDetailsUiState: StateFlow<ShowsDetailsUiState> = _showsDetailsUiState

    init {
        fetchShows() // Fetch shows upon initialization
    }

    // Fetch details for a specific show
    // Fetch details for a specific show
     fun getShowsDetails(showsId: Int) {
        Timber.tag("ShowsViewModel").d("Fetching details for show ID: $showsId")
        viewModelScope.launch {
            _showsDetailsUiState.value = ShowsDetailsUiState.Loading // Set loading state
            showsRepository.getShowDetailsFlow(showsId)
                .onEach { resource ->
                    Timber.tag("ShowsViewModel").d("Received resource for show details: $resource")
                    _showsDetailsUiState.value = when (resource) {
                        is Resource.Success -> {
                            Timber.tag("ShowsViewModel").d("Successfully fetched show details")
                            ShowsDetailsUiState.Success(resource.data)
                        }
                        is Resource.Error -> {
                            Timber.tag("ShowsViewModel").e("Error fetching show details: ${resource.message}")
                            ShowsDetailsUiState.Error(resource.message ?: "Unknown error")
                        }
                        is Resource.Loading -> {
                            // Handle loading state if necessary
                            ShowsDetailsUiState.Loading
                        }
                    }
                }
                .catch { e ->
                    Timber.tag("ShowsViewModel").e(e, "Error during fetching show details: ${e.message}")
                    _showsDetailsUiState.value = ShowsDetailsUiState.Error("Network Error: ${e.message}")
                }
                .launchIn(viewModelScope)
        }
    }


    // Fetch shows from repository
     fun fetchShows() {
        Timber.tag("ShowsViewModel").d("Fetching shows")
        viewModelScope.launch {
            _showsUiState.value = ShowsUiState.Loading // Set loading state
            showsRepository.getShowsFlow()
                .onEach { resource ->
                    Timber.tag("ShowsViewModel").d("Received resource for shows: $resource")
                    _showsUiState.value = when (resource) {
                        is Resource.Success -> {
                            val showsList = resource.data ?: emptyList() // Use the data directly
                            Timber.tag("ShowsViewModel").d("Successfully fetched shows: $showsList")
                            ShowsUiState.Success(showsList)
                        }
                        is Resource.Error -> {
                            Timber.tag("ShowsViewModel").e("Error fetching shows: ${resource.message}")
                            ShowsUiState.Error(resource.message ?: "Unknown error")
                        }
                        is Resource.Loading -> {
                            // Handle loading state if necessary
                            ShowsUiState.Loading
                        }
                    }
                }
                .catch { e ->
                    Timber.tag("ShowsViewModel").e(e, "Error during fetching shows: ${e.message}")
                    _showsUiState.value = ShowsUiState.Error("Network Error: ${e.message}")
                }
                .launchIn(viewModelScope)
        }
    }

    // State classes for UI representation
    sealed class ShowsDetailsUiState {
        object Loading : ShowsDetailsUiState()
        data class Success(val showDetails: ShowDetails?) : ShowsDetailsUiState()
        data class Error(val message: String) : ShowsDetailsUiState()
    }

    sealed class ShowsUiState {
        object Loading : ShowsUiState()
        data class Success(val shows: List<ShowItem>) : ShowsUiState()
        data class Error(val message: String) : ShowsUiState()
    }
}
