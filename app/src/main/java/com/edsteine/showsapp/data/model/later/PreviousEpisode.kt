//package com.edsteine.showsapp.data.model.later
//
//import android.os.Parcelable
//import kotlinx.parcelize.Parcelize
//
///**
// * Represents a previous episode of a show.
// * The `PreviousEpisode` class contains a single property that provides
// * a URL to the previous episode's information.
// *
// * This data class implements `Parcelable`, allowing instances of this class
// * to be passed between Android components.
// *
// * @property href URL to the previous episode's details.
// */
//@Parcelize
//data class PreviousEpisode(
//    val href: String // URL to the previous episode's details
//) : Parcelable {
//
//    /**
//     * Returns a boolean indicating whether the URL is valid.
//     *
//     * @return True if the URL is not empty and starts with "http", otherwise false.
//     */
//    fun isValidUrl(): Boolean {
//        return href.isNotEmpty() && href.startsWith("http")
//    }
//
//    /**
//     * Provides a user-friendly string representation of the URL.
//     *
//     * @return A shortened or formatted version of the URL for display.
//     */
//    fun getDisplayUrl(): String {
//        return if (href.length > 40) {
//            "${href.take(37)}..." // Shorten the URL if it's too long
//        } else {
//            href
//        }
//    }
//}
