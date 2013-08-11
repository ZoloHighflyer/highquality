package com.bluecreation.search.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LuceneIndexStore {

    private static final String DEFAULT_INDEX_DIR = "index";

    private final String indexDir;

    public LuceneIndexStore() {
        this(DEFAULT_INDEX_DIR);
    }

    public LuceneIndexStore(String indexDir) {
        this.indexDir = indexDir;
        try {
            File indexFolder = new File(indexDir);
            boolean createIndexFolder = !indexFolder.isDirectory();

            new IndexWriter(getDirectory(createIndexFolder), null, createIndexFolder).close();
        } catch (IOException e) {
            throw new LuceneException("Cannot create index directory", e);
        }
    }

    public IndexReader createReader() throws IOException {
        return IndexReader.open(getDirectory(false));
    }

    public IndexWriter createWriter(Analyzer analyzer) throws IOException {
        return new IndexWriter(getDirectory(false), analyzer, false);
    }

    public IndexSearcher createSearcher() throws IOException {
        return new IndexSearcher(getDirectory(false));
    }

    public int getNumDocs() throws IOException {
        IndexReader reader = createReader();
        int result = reader.numDocs();
        reader.close();
        return result;
    }

    protected Directory getDirectory(boolean create) throws IOException {
        return FSDirectory.getDirectory(indexDir, create);
    }

}
