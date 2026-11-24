/**
 * File: AppModule.kt
 * Date updated: 2024-10-16
 * Author: Your Name
 * Email: your.email@example.com
 * Phone: +1234567890
 * Project: ShowsApp
 * Description:
 * Dagger module for providing application-level dependencies using Hilt.
 * This module configures Retrofit for network calls and OkHttp for HTTP logging.
 * Used Libraries: Dagger, Retrofit, OkHttp, Timber, Gson
 */

package com.edsteine.showsapp.data.di

import android.app.Application
import com.edsteine.showsapp.data.remote.ShowsClient
import com.edsteine.showsapp.data.utils.AppUtils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import timber.log.Timber
import com.edsteine.showsapp.BuildConfig
import com.edsteine.showsapp.data.model.*
import com.google.gson.GsonBuilder
import dagger.hilt.android.HiltAndroidApp

/**
 * Custom Application class for the ShowsApp.
 *
 * This class is annotated with @HiltAndroidApp, which triggers Hilt's code generation
 * and serves as the application-level dependency container.
 */
@HiltAndroidApp
class ShowsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize Timber for logging in debug mode
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

/**
 * Dagger module for providing application-level dependencies.
 *
 * This module is responsible for providing the necessary instances of
 * the ShowsClient API interface using Retrofit and OkHttp.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule { // Changed to an object for better singleton management

    /**
     * Provides an instance of [ShowsClient].
     *
     * This method creates a Retrofit instance configured with the
     * specified base URL, a Gson converter, and an OkHttp client
     * with logging capabilities for HTTP requests and responses.
     *
     * @return An instance of [ShowsClient].
     */
    @Singleton
    @Provides
    fun provideShowsApi(): ShowsClient {
        // Create a Gson instance with custom deserializers for various model classes
        val gson = GsonBuilder()
            .registerTypeAdapter(ShowItem::class.java, ShowItemDeserializer())
            .registerTypeAdapter(Country::class.java, CountryDeserializer()) // Register CountryDeserializer
            .registerTypeAdapter(Externals::class.java, ExternalsDeserializer())
            .registerTypeAdapter(Image::class.java, ImageDeserializer())
            .registerTypeAdapter(Links::class.java, LinksDeserializer())
            .registerTypeAdapter(Network::class.java, NetworkDeserializer())
            .registerTypeAdapter(Rating::class.java, RatingDeserializer())
            .registerTypeAdapter(Schedule::class.java, ScheduleDeserializer())
            .registerTypeAdapter(Self::class.java, SelfDeserializer())
            .registerTypeAdapter(ShowDetails::class.java, ShowDetailsDeserializer())
            .registerTypeAdapter(WebChannel::class.java, WebChannelDeserializer())
            .create()

        // Create an interceptor for logging HTTP requests and responses
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Log the body of requests/responses
        }

        // Build the OkHttpClient with the logging interceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        // Build and return the Retrofit instance
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Set the base URL for API calls
            .addConverterFactory(GsonConverterFactory.create(gson)) // Set the converter for JSON
            .client(okHttpClient) // Set the OkHttp client
            .build()
            .create(ShowsClient::class.java) // Create the ShowsClient interface
    }
}
