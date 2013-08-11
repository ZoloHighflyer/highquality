package com.bluecreation.search;

import java.util.List;

/**
 * Searches through all objects that have been indexed using a free text query.
 * To index an object allowing it to show up in the search, use Indexer
 *
 * @see org.petsoar.search.Searcher
 */
public interface Searcher {
    /**
     * Performs the search operation and returns a List of found items.
     */
    List search(String query);
}
