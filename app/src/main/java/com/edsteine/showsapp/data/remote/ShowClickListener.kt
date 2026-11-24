// File: ShowClickListener.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Interface for handling show click events.
//              This interface defines a contract for classes that implement
//              show click handling functionality. The implementing class will
//              provide the logic to be executed when a show item is clicked.
// Used Libraries: None

package com.edsteine.showsapp.data.remote

import com.edsteine.showsapp.data.model.ShowItem

/**
 * Interface for handling show click events.
 *
 * This interface defines a contract for classes that implement
 * show click handling functionality. The implementing class will
 * provide the logic to be executed when a show item is clicked.
 *
 * Updated on: 15-10-2024
 */
interface ShowClickListener {

    /**
     * Called when a show item is clicked.
     *
     * @param showItem The clicked show item represented by [ShowItem].
     */
    fun onShowClicked(showItem: ShowItem) // Renamed parameter for clarity
}
