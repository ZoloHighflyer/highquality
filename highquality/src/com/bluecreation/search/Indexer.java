package com.bluecreation.search;

/**
 * Indexes the data stored in an object for fast+flexible search querying.
 * To search for an object that has been indexed, use Searcher.
 *
 * @see org.petsoar.search.Searcher
 */
public interface Indexer {

    void index(Object obj);

    void unIndex(Object obj);

}
