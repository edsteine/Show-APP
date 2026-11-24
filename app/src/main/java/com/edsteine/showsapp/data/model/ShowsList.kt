// File: ShowsList.kt
// Date updated: 2024-10-15
// Author: Your Name
// Email: your.email@example.com
// Phone: +1234567890
// Project: ShowsApp
// Description: Represents a response containing a map of Shows keyed by their IDs.
//              This class encapsulates a collection of shows and provides utility functions
//              for retrieving shows by ID and getting a list of show names.
// Used Libraries: Android SDK, Kotlinx Parcelize

package com.edsteine.showsapp.data.model

/**
 * 15-10-2024
 * Represents a response containing a map of Shows keyed by their IDs.
 *
 * This class encapsulates a collection of shows and provides utility functions
 * for retrieving shows by ID and getting a list of show names.
 *
 * @property shows A map where the keys are the show IDs and the values are the corresponding [ShowItem].
 */
data class ShowsList(
    val shows: MutableMap<Int, ShowItem> = mutableMapOf() // Changed to MutableMap
) : MutableList<ShowItem> {

    override val size: Int
        get() = shows.size // Return the size of the shows map

    override fun clear() {
        shows.clear() // Clear the map of shows
    }

    override fun addAll(elements: Collection<ShowItem>): Boolean {
        return elements.all { add(it) } // Add all elements to the map
    }

    override fun addAll(index: Int, elements: Collection<ShowItem>): Boolean {
        return elements.all { add(it) } // Add all elements to the map
    }

    override fun add(index: Int, element: ShowItem) {
        shows[element.id] = element // Add element at a specific index (keyed by ID)
    }

    override fun add(element: ShowItem): Boolean {
        return shows.put(element.id, element) == null // Add element if it's not already present
    }

    override fun contains(element: ShowItem): Boolean {
        return shows.containsValue(element) // Check if the element exists in the map values
    }

    override fun containsAll(elements: Collection<ShowItem>): Boolean {
        return elements.all { shows.containsValue(it) } // Check if all elements exist in the map values
    }

    override fun get(index: Int): ShowItem {
        return shows.values.elementAt(index) // Retrieve the show item by index
    }

    override fun indexOf(element: ShowItem): Int {
        return shows.values.indexOf(element) // Get the index of the show item
    }

    override fun isEmpty(): Boolean {
        return shows.isEmpty() // Check if the map is empty
    }

    override fun iterator(): MutableIterator<ShowItem> {
        return shows.values.iterator() // Provide an iterator over the show items
    }

    override fun lastIndexOf(element: ShowItem): Int {
        return shows.values.toList().lastIndexOf(element) // Get the last index of the show item
    }

    override fun listIterator(): MutableListIterator<ShowItem> {
        return shows.values.toList().listIterator() as MutableListIterator<ShowItem> // Provide a list iterator
    }

    override fun listIterator(index: Int): MutableListIterator<ShowItem> {
        return shows.values.toList().listIterator(index) as MutableListIterator<ShowItem> // Provide a list iterator starting at the given index
    }

    override fun removeAt(index: Int): ShowItem {
        val showItem = shows.values.elementAt(index)
        shows.remove(showItem.id) // Remove the item by its ID
        return showItem // Return the removed item
    }

    override fun set(index: Int, element: ShowItem): ShowItem {
        val oldItem = shows.values.elementAt(index)
        shows[element.id] = element // Update the show item by ID
        return oldItem // Return the old item
    }

    override fun retainAll(elements: Collection<ShowItem>): Boolean {
        val toRemove = shows.values.filter { it !in elements }
        toRemove.forEach { shows.remove(it.id) } // Remove elements not in the collection
        return toRemove.isNotEmpty() // Return true if any elements were removed
    }

    override fun removeAll(elements: Collection<ShowItem>): Boolean {
        val removed = elements.any { shows.remove(it.id) != null } // Remove elements in the collection
        return removed // Return true if any elements were removed
    }

    override fun remove(element: ShowItem): Boolean {
        return shows.remove(element.id) != null // Remove the element by ID
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<ShowItem> {
        return shows.values.toList().subList(fromIndex, toIndex) as MutableList<ShowItem> // Provide a sublist of show items
    }

    /**
     * Efficiently retrieves a Show by its ID using the map.
     *
     * @param id The ID of the Show to retrieve.
     * @return The corresponding [ShowItem] if found, or null otherwise.
     */
    fun getShowById(id: Int): ShowItem? {
        return shows[id] // Direct map lookup, O(1) on average
    }

    /**
     * Retrieves a list of Show names.
     *
     * @return A list of Show names as strings. Nullable names are filtered out.
     */
    fun getShowsNames(): List<String> {
        return shows.values.mapNotNull { it.name } // Extract non-null names from the Show items
    }

    /**
     * Retrieves a list of shows that are considered 'popular' based on a minimum rating.
     *
     * @param minRating The minimum rating a show must have to be considered popular.
     * @return A list of [ShowItem] that meet the popularity criteria.
     */
    fun getPopularShows(minRating: Double): List<ShowItem> {
        return shows.values.filter {
            (it.rating?.average ?: 0.0) >= minRating // Ensure correct precedence with parentheses
        } // Filter shows by rating
    }

    companion object {
        /**
         * Creates a [ShowsList] from a list of [ShowItem],
         * generating the ID-based map.
         *
         * This function is useful when receiving data as a list from your API.
         *
         * @param showsList The list of [ShowItem] to convert into a ShowsList.
         * @return A [ShowsList] instance containing the mapped shows.
         */
        fun fromList(showsList: List<ShowItem>): ShowsList {
            return ShowsList(showsList.associateBy { it.id }.toMutableMap()) // Convert to MutableMap
        }
    }
}
