// File: Resource.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: A sealed class representing the resource state for data operations,
//              encapsulating the data state during API calls. It allows for a clear
//              representation of success, error, and loading states.
// Used Libraries: Android SDK, Kotlinx

package com.edsteine.showsapp.data.utils

/**
 * A sealed class representing the resource state for data operations.
 *
 * This class encapsulates the data state during API calls,
 * allowing for a clear representation of success, error, and loading states.
 *
 * @param T The type of data associated with the resource.
 * @property data The data returned from the API call, if successful.
 * @property message An optional message describing an error, if applicable.
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {

    /**
     * Represents a successful data retrieval.
     *
     * @param data The data returned from the API.
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * Represents an error during data retrieval.
     *
     * @param message A message describing the error.
     * @param data Optional data related to the error.
     */
    class Error<T>(
        message: String,
        data: T? = null
    ) : Resource<T>(data, message)

    /**
     * Represents a loading state during data retrieval.
     *
     * @param data Optional data related to the loading state.
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)

    companion object {
        /**
         * Creates a loading state for a resource.
         *
         * @param data Optional data related to the loading state.
         * @return A loading instance of the Resource class.
         */
        fun <T> loading(data: T? = null): Resource<T> {
            return Loading(data)
        }

        /**
         * Creates a success state for a resource.
         *
         * @param data The successful data to be wrapped.
         * @return A success instance of the Resource class.
         */
        fun <T> success(data: T): Resource<T> {
            return Success(data)
        }

        /**
         * Creates an error state for a resource.
         *
         * @param message A message describing the error.
         * @param data Optional data related to the error.
         * @return An error instance of the Resource class.
         */
        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Error(message, data)
        }
    }
}
