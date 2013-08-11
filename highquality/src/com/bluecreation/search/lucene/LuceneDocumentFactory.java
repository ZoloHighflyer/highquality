package com.bluecreation.search.lucene;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;

public interface LuceneDocumentFactory {

    Document createDocument(Object obj);

    String getHandleAttributeName(Object obj);

    String getHandleFieldName(Object obj);
   
    List getFieldNames(Object obj);
    
    Analyzer createAnalyzer();

}
