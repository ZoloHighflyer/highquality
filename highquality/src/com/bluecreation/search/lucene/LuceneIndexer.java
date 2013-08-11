package com.bluecreation.search.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.PhraseQuery;

import com.bluecreation.search.Indexer;


/**
 * Yuandian Studio
 * @author Oliver Chen  2008-2-20
 * 
 */
public class LuceneIndexer {

    private LuceneDocumentFactory luceneDocumentFactory;
    private LuceneIndexStore indexStore;
    
    public void setIndexStore(LuceneIndexStore indexStore) {
        this.indexStore = indexStore;
    }

    public void setLuceneDocumentFactory(
            LuceneDocumentFactory luceneDocumentFactory) {
        this.luceneDocumentFactory = luceneDocumentFactory;
    }

    
    public LuceneIndexStore getIndexStore() {
		return indexStore;
	}

	public LuceneDocumentFactory getLuceneDocumentFactory() {
		return luceneDocumentFactory;
	}

	public synchronized void index(Object obj) {
        unIndex(obj);
        try {
            Analyzer analyzer = luceneDocumentFactory.createAnalyzer();
            IndexWriter writer = indexStore.createWriter(analyzer);
            try {
                Document doc = luceneDocumentFactory.createDocument(obj);
                writer.addDocument(doc);
                writer.optimize();
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            throw new LuceneException("Cannot update index", e);
        }
    }

    public synchronized void unIndex(Object obj) {
        String handleAttributeName = luceneDocumentFactory.getHandleAttributeName(obj);
        String handleFieldName = luceneDocumentFactory.getHandleFieldName(obj);
        String handleAttributeValue = null;

        try {
            handleAttributeValue = BeanUtils.getProperty(obj, handleAttributeName);
            
        } catch (Exception e) {
            throw new LuceneException("Cannot identify object", e);
        }

        try {
            IndexReader reader = indexStore.createReader();

            try {
                Term t = new Term(handleFieldName, handleAttributeValue);
                
             //   TermQuery termQuery= new TermQuery(t);
                
                reader.deleteDocuments(t);
            } catch(Exception e){
            	System.out.println("delete field name "+handleFieldName+" error  ");
            } finally {
                
                reader.close();
            }
        } catch (IOException e) {
            throw new LuceneException("Cannot delete from index", e);
        }
        
       
    }
    public synchronized List listIndexes(Object model ,Object vo,Map queryMap){
    	List returnList = new ArrayList();
    	 try {
             IndexReader reader = indexStore.createReader();
             PhraseQuery pQuery = new PhraseQuery();
 			 
 			 Set set = queryMap.keySet();
 			 Iterator it =set.iterator();
 			 while (it.hasNext()) {
 				String key = (String)it.next();
 				Term t = new Term(key,(String)queryMap.get(key));
 				pQuery.add(t);
 			 }
 			 Hits hits = indexStore.createSearcher().search(pQuery);
             try {
            	 for(int i=0;i<hits.length();i++)
            	 {
            	    //get the document of index which is stored in file system
 		    		Document doc = hits.doc(i);
 		    		
 		    		//use the vo object to new instance
 		    		Class clz = vo.getClass();
 		    		Object o = clz.newInstance();
 		    		//get the list the field names which defined in the configuration 
 		    		List fields = luceneDocumentFactory.getFieldNames(model);
 		    		// get all the attribute value from list of  fields from the lucene cofiguration
 		    		for (int j =0 ;j<fields.size();j++) 
 		    		    BeanUtils.copyProperty(o, (String)fields.get(j), (String)doc.get((String)fields.get(j)));
 		    		   
 		    	     returnList.add(o);            	 
            	 }
             } catch(Exception e){
             	System.out.println("list index document name  error  "+e.getMessage());
             } finally {
             
                 reader.close();
             }
         } catch (IOException e) {
             throw new LuceneException("Cannot delete from index", e);
         }
    	return returnList;
    	
    }

}
