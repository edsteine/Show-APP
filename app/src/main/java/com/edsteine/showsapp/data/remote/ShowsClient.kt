// File: ShowsClient.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Interface for accessing shows-related API endpoints.
//              This interface provides methods to fetch details about shows,
//              including specific show details and a list of shows. Each method
//              returns a Retrofit [Response] containing the relevant data model.
// Used Libraries: Retrofit

package com.edsteine.showsapp.data.remote

import com.edsteine.showsapp.data.model.ShowDetails
import com.edsteine.showsapp.data.model.ShowsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface for accessing shows-related API endpoints.
 *
 * Updated on: 15-10-2024
 * This interface provides methods to fetch details about shows, including
 * specific show details and a list of shows. Each method returns a Retrofit
 * [Response] containing the relevant data model.
 */
interface ShowsClient {

    /**
     * Fetch details of a specific show by ID.
     * Example: https://api.tvmaze.com/shows/82 (for "Game of Thrones")
     *
     * @param showId The ID of the show to retrieve.
     * @return A [Response] containing the [ShowDetails] for the requested show.
     *
     * @throws Exception if there is an issue with the network call or data retrieval.
     */
    @GET("/shows/{id}")
    suspend fun getShowDetails(@Path("id") showId: Int): Response<ShowDetails>

    /**
     * Get a paginated list of shows.
     * Example: https://api.tvmaze.com/shows?page=0
     *
     * @param page The page number to retrieve.
     * @return A [Response] containing the [ShowsList] for the requested page.
     *
     * @throws Exception if there is an issue with the network call or data retrieval.
     */
    @GET("/shows")
    suspend fun getShowsList(@Query("page") page: Int): Response<ShowsList>

//    /**
//     * Search for shows by name.
//     * Example: https://api.tvmaze.com/search/shows?q=friends
//     *
//     * @param query The name of the show to search for.
//     * @return A [Response] containing a list of [ShowsSearchResponse] for the search results.
//     */
//    @GET("/search/shows")
//    suspend fun searchShows(@Query("q") query: String): Response<List<ShowsSearchResponse>>
//
//    /**
//     * Fetch episodes of a specific show by ID.
//     * Example: https://api.tvmaze.com/shows/82/episodes (for "Game of Thrones")
//     *
//     * @param showId The ID of the show whose episodes to retrieve.
//     * @return A [Response] containing a list of [Episode] for the requested show.
//     */
//    @GET("/shows/{id}/episodes")
//    suspend fun getShowEpisodes(@Path("id") showId: String): Response<List<Episode>>
//
//    /**
//     * Fetch cast details of a show by ID.
//     * Example: https://api.tvmaze.com/shows/82/cast
//     *
//     * @param showId The ID of the show whose cast to retrieve.
//     * @return A [Response] containing a list of [CastResponse] for the requested show.
//     */
//    @GET("/shows/{id}/cast")
//    suspend fun getShowCast(@Path("id") showId: String): Response<List<CastResponse>>
//
//    /**
//     * Fetch crew details of a show by ID.
//     * Example: https://api.tvmaze.com/shows/82/crew
//     *
//     * @param showId The ID of the show whose crew to retrieve.
//     * @return A [Response] containing a list of [CrewResponse] for the requested show.
//     */
//    @GET("/shows/{id}/crew")
//    suspend fun getShowCrew(@Path("id") showId: String): Response<List<CrewResponse>>
//
//    /**
//     * Fetch list of seasons for a specific show.
//     * Example: https://api.tvmaze.com/shows/1/seasons (for "Under the Dome")
//     *
//     * @param showId The ID of the show whose seasons to retrieve.
//     * @return A [Response] containing a list of [SeasonResponse] for the requested show.
//     */
//    @GET("/shows/{id}/seasons")
//    suspend fun getShowSeasons(@Path("id") showId: String): Response<List<SeasonResponse>>
//
//    /**
//     * Fetch schedule for a specific country and date.
//     * Example: https://api.tvmaze.com/schedule?country=US&date=2024-10-15
//     *
//     * @param countryCode The ISO code of the country.
//     * @param date The date for which to retrieve the schedule (format: YYYY-MM-DD).
//     * @return A [Response] containing a list of [ScheduleResponse] for the requested date.
//     */
//    @GET("/schedule")
//    suspend fun getFullSchedule(
//        @Query("country") countryCode: String,
//        @Query("date") date: String
//    ): Response<List<ScheduleResponse>>
//
//    /**
//     * Fetch web/streaming schedule.
//     * Example: https://api.tvmaze.com/schedule/web?country=US&date=2024-10-15
//     *
//     * @param countryCode The ISO code of the country.
//     * @param date The date for which to retrieve the web schedule (format: YYYY-MM-DD).
//     * @return A [Response] containing a list of [ScheduleResponse] for the requested date.
//     */
//    @GET("/schedule/web")
//    suspend fun getWebSchedule(
//        @Query("country") countryCode: String,
//        @Query("date") date: String
//    ): Response<List<ScheduleResponse>>
//
//    /**
//     * Fetch information about a specific episode.
//     * Example: https://api.tvmaze.com/episodes/4952
//     *
//     * @param episodeId The ID of the episode to retrieve.
//     * @return A [Response] containing the [EpisodeDetails] for the requested episode.
//     */
//    @GET("/episodes/{id}")
//    suspend fun getEpisodeDetails(@Path("id") episodeId: String): Response<EpisodeDetails>
//
//    /**
//     * Fetch person details by ID.
//     * Example: https://api.tvmaze.com/people/104 (for "Jennifer Aniston")
//     *
//     * @param personId The ID of the person to retrieve.
//     * @return A [Response] containing the [PersonDetails] for the requested person.
//     */
//    @GET("/people/{id}")
//    suspend fun getPersonDetails(@Path("id") personId: String): Response<PersonDetails>
//
//    /**
//     * Fetch the list of shows where a person has been a cast member.
//     * Example: https://api.tvmaze.com/people/104/castcredits
//     *
//     * @param personId The ID of the person to retrieve cast credits for.
//     * @return A [Response] containing a list of [CastCreditResponse] for the person's shows.
//     */
//    @GET("/people/{id}/castcredits")
//    suspend fun getPersonCastCredits(@Path("id") personId: String): Response<List<CastCreditResponse>>
//
//    /**
//     * Fetch crew credits of a person by ID.
//     * Example: https://api.tvmaze.com/people/104/crewcredits
//     *
//     * @param personId The ID of the person to retrieve crew credits for.
//     * @return A [Response] containing a list of [CrewCreditResponse] for the person's crew credits.
//     */
//    @GET("/people/{id}/crewcredits")
//    suspend fun getPersonCrewCredits(@Path("id") personId: String): Response<List<CrewCreditResponse>>
//
//    /**
//     * Fetch show updates since a specific timestamp.
//     * Example: https://api.tvmaze.com/updates/shows?since=1630454400
//     *
//     * @param since The timestamp to retrieve updates since (in seconds).
//     * @return A [Response] containing the [UpdatesResponse] for the requested updates.
//     */
//    @GET("/updates/shows")
//    suspend fun getShowUpdates(@Query("since") since: String): Response<UpdatesResponse>
//
//    /**
//     * Fetch people updates since a specific timestamp.
//     * Example: https://api.tvmaze.com/updates/people?since=1630454400
//     *
//     * @param since The timestamp to retrieve updates since (in seconds).
//     * @return A [Response] containing the [UpdatesResponse] for the requested updates.
//     */
//    @GET("/updates/people")
//    suspend fun getPeopleUpdates(@Query("since") since: String): Response<UpdatesResponse>
//
//    /**
//     * Fetch alternative lists for a specific show by ID.
//     * Example: https://api.tvmaze.com/shows/82/alternatelists
//     *
//     * @param showId The ID of the show to retrieve alternative lists for.
//     * @return A [Response] containing a list of [AlternateListResponse] for the requested show.
//     */
//    @GET("/shows/{id}/alternatelists")
//    suspend fun getAlternateLists(@Path("id") showId: String): Response<List<AlternateListResponse>>
//
//    /**
//     * Fetch information about a specific alternate list.
//     * Example: https://api.tvmaze.com/alternatelists/1234
//     *
//     * @param listId The ID of the alternate list to retrieve.
//     * @return A [Response] containing the [AlternateListDetails] for the requested list.
//     */
//    @GET("/alternatelists/{id}")
//    suspend fun getAlternateListDetails(@Path("id") listId: String): Response<AlternateListDetails>
}
