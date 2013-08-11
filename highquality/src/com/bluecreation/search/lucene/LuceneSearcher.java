package com.bluecreation.search.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import com.bluecreation.search.Searcher;

public class LuceneSearcher implements ILuceneDocumentFactory, ILuceneIndexStore {

    private LuceneDocumentFactory luceneDocumentFactory;
    private LuceneIndexStore indexStore;

    public void setIndexStore(LuceneIndexStore indexStore) {
        this.indexStore = indexStore;
    }

    public LuceneDocumentFactory getLuceneDocumentFactory() {
        return luceneDocumentFactory;
    }

    public void setLuceneDocumentFactory(
            LuceneDocumentFactory luceneDocumentFactory) {
        this.luceneDocumentFactory = luceneDocumentFactory;
    }

    public void setSearcher(Searcher searcher) {
		
	}

	public List search(String query) {
        QueryParser qp = null;
        Query myquery = null;

        try {
            qp = new QueryParser("description",
                    luceneDocumentFactory.createAnalyzer());
            myquery = qp.parse(query);
        } catch (Throwable e) {
            throw new LuceneException("Couldn't parse the query successfully:" +
                    e.getMessage());
        }

        IndexSearcher searcher = null;

        try {
            searcher = indexStore.createSearcher();

            Hits hits = searcher.search(myquery);
            List result = new ArrayList(hits.length());
            for (int i = 0; i < hits.length(); i++) {
                Document doc = hits.doc(i);
                result.add(Long.valueOf(doc.get("handle")));
            }

            return result;
        } catch (Throwable e) {
            throw new LuceneException("Couldn't complete search successfully", e);
        } finally {
            try {
                if (searcher != null)
                    searcher.close();
            } catch (IOException e) {
                throw new LuceneException("Couldn't complete search successfully", e);
            }
        }
    }

}
