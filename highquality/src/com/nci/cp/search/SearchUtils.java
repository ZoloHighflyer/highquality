package com.nci.cp.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public final class SearchUtils {
	public static void indexRecord(String indexDirString, String indexId,
			Content content) throws Exception {
		File indexDir = new File(indexDirString);
		if (!indexDir.exists() || !indexDir.isDirectory()) {
			throw new IOException(indexDir
					+ " does not exist or is not a directory");
		}
		IndexWriter writer = new IndexWriter(indexDir, new StandardAnalyzer(),
				false);
		//writer.setUseCompoundFile(false);
		Document doc = new Document();
		/*doc.add(Field.Text(indexId, content.getContent()));
		doc.add(Field.Keyword("id", "" + content.getId() + ""));*/
		writer.addDocument(doc);
		writer.optimize();
		writer.close();
	}
    public static List<Content> searchRecord(String indexDirString,String indexId,String querystr) throws Exception {
    	File indexDir = new File(indexDirString);
		if (!indexDir.exists() || !indexDir.isDirectory()) {
			throw new IOException(indexDir
					+ " does not exist or is not a directory");
		}
		Directory fsDir = FSDirectory.getDirectory(indexDir, false);
    	IndexSearcher is = new IndexSearcher(fsDir);
		Query query =null;
		
		Hits hits = is.search(query);
		List results = new ArrayList();
		for (int i = 0; i < hits.length(); i++) {
			Document doc = hits.doc(i);
			Content c = new Content();
			c.setContent(doc.get("id"));
			results.add(c);
		}
    	return results;
    }
}
