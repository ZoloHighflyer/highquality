package com.bluecreation.search.lucene;

/**
 * Thrown when a disaster happens in the underlying Lucene layer.
 */
public class LuceneException extends RuntimeException {

    public LuceneException(String message) {
        super(message);
    }

    public LuceneException(Throwable cause) {
        super(cause);
    }

    public LuceneException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
