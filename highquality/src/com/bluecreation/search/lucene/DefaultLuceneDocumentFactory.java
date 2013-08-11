package com.bluecreation.search.lucene;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.digester.Digester;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LetterTokenizer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

/**
 * The default implementation of LuceneDocumentFactory that looks after a file named <class-name>.lucene.xml and reads
 * the Lucene Document creation instuctions from it.
 */
public class DefaultLuceneDocumentFactory implements LuceneDocumentFactory {

    private Map classConfigurations = new HashMap();

    public Document createDocument(Object obj) {
        ClassConfiguration classConfig = getClassConfiguration(obj);

        return createDocumentForObjectFromClassConfiguration(obj, classConfig);
    }
    
    /**取得配置里attributeName字段的数据*/
    public String getHandleAttributeName(Object obj) {
        ClassConfiguration class_config = getClassConfiguration(obj);

        return class_config.getHandleField().getAttributeName();
    }
    
    /**取得配置里fieldName字段的数据*/
    public String getHandleFieldName(Object obj) {
        ClassConfiguration class_config = getClassConfiguration(obj);

        return class_config.getHandleField().getFieldName();
    }
    
    /**取得配置里field字段的数据列表*/
    public List getFieldNames(Object obj){
    	ClassConfiguration class_config = getClassConfiguration(obj);
    	List fieldNames = new ArrayList();
    	List fieldConfigs = class_config.getFieldConfigurations();
    	for (int i =0; i<fieldConfigs.size();i++) {
    		FieldConfiguration fieldConfig = (FieldConfiguration)fieldConfigs.get(i);
    		fieldNames.add(fieldConfig.getFieldName());
    	}
    	//fieldNames.
    	return fieldNames;
    }
    
    /**创建分词分析器*/
    public Analyzer createAnalyzer() {
        return new ChineseAnalyzer();
    }
    
    /**把对象配置和对象名建立map表*/
    private ClassConfiguration getClassConfiguration(Object obj) {
        ClassConfiguration class_config;

        synchronized (classConfigurations) {
            class_config = (ClassConfiguration) classConfigurations.get(obj.getClass().getName());

            if (class_config == null) {
                class_config = loadClassConfiguration(obj.getClass());
            }
        }

        return class_config;
    }

    private Document createDocumentForObjectFromClassConfiguration(Object obj,
                                                                   ClassConfiguration class_config) {
        Iterator iter = class_config.getFieldConfigurations().iterator();
        Document doc = new Document();

        while (iter.hasNext()) {
            FieldConfiguration fieldConfiguration = (FieldConfiguration) iter.next();
            String strContent = getStringContentOfAttribute(obj,
                    fieldConfiguration.getAttributeName());
            Field field = null;
            String fieldName=obj.getClass().getName()+"."+fieldConfiguration.getFieldName();
            if (fieldConfiguration.getType().equals(FieldConfiguration.TYPE_UNINDEXED)) {
                field = new Field(fieldName,strContent,Field.Store.YES,Field.Index.UN_TOKENIZED);
            } else if (fieldConfiguration.getType().equals(FieldConfiguration.TYPE_INDEX)) {
                field = new Field(fieldName,strContent,Field.Store.YES,Field.Index.TOKENIZED);
            } else if (fieldConfiguration.getType().equals(FieldConfiguration.TYPE_HANDLE)) {
                field = new Field(fieldName,strContent,Field.Store.YES,Field.Index.UN_TOKENIZED);
            } else {
                throw new LuceneException(
                        "Unknown type for a field, fieldName=" +
                        fieldConfiguration.getFieldName());
            }

            doc.add(field);
        }

        return doc;
    }

    private static String getStringContentOfAttribute(Object obj,
                                                      String attributeName) {
        try {
            String str = BeanUtils.getProperty(obj, attributeName);
            
            return (str == null) ? "" : str;
        } catch (Exception e) {
            throw new LuceneException(
                    "Couldn't get string content of attribute, attributeName=" +
                    attributeName);
        }
    }

    private void addClassConfiguration(Class clazz,
                                       ClassConfiguration classConfiguration) {
        classConfigurations.put(clazz.getName(), classConfiguration);
    }

    private ClassConfiguration loadClassConfiguration(Class clazz) {
        InputStream configXml = loadConfigFile(clazz);

        ClassConfiguration newClassConfig = new ClassConfiguration();
        /**利用Digester工具生成bean*/
        Digester digester = new Digester();

        digester.push(newClassConfig);
        digester.addObjectCreate("configuration/field",
                FieldConfiguration.class.getName());
        digester.addSetProperties("configuration/field");
        digester.addSetNext("configuration/field", "addFieldConfiguration",
                FieldConfiguration.class.getName());

        try {
            digester.parse(new InputStreamReader(configXml));

            addClassConfiguration(clazz, newClassConfig);

            return newClassConfig;
        } catch (Exception e) {
            throw new LuceneException(
                    "Couldn't load lucene config file successfully, file=" + clazz, e);
        }
    }

    private InputStream loadConfigFile(Class clazz) {
        String configFileName = clazz.getName().replace('.', '/') + ".lucene.xml";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFileName);

        if (is != null) {
            return is;
        } else {
            // Hibernate generates a CGLIB-based subclass when the POJO is a proxy, so check the superclass too
            configFileName = clazz.getSuperclass().getName().replace('.', '/') + ".lucene.xml";
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFileName);
            return is;
        }
    }

    //~ Classes ----------------------------------------------------------------

    public static final class ClassConfiguration {
        private List fieldConfigurations = new ArrayList();

        public void addFieldConfiguration(FieldConfiguration fieldConfiguration) {
            fieldConfigurations.add(fieldConfiguration);
        }

        public List getFieldConfigurations() {
            return fieldConfigurations;
        }

        private FieldConfiguration getHandleField() {
            for (int i = 0; i < fieldConfigurations.size(); i++) {
                FieldConfiguration fieldConfiguration = (FieldConfiguration) fieldConfigurations.get(i);

                if (fieldConfiguration.getType().equals(FieldConfiguration.TYPE_HANDLE)) {
                    return fieldConfiguration;
                }
            }

            throw new LuceneException("No handle field found.");
        }

        public boolean equals(Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }

        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    public static final class FieldConfiguration {
        public static final String TYPE_UNINDEXED = "UnIndexed";
        public static final String TYPE_INDEX = "Indexed";
        public static final String TYPE_HANDLE = "Handle";
        private String type;
        private String fieldName;
        private String attributeName;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }

        public boolean equals(Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }

        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    public static class DefaultAnalyzer extends Analyzer {
        public TokenStream tokenStream(String fieldName, Reader reader) {
            LetterTokenizer tokenizer = new LetterTokenizer(reader);
            TokenStream result = null;
            result = new LowerCaseFilter(tokenizer);
            result = new StopFilter(result, StopAnalyzer.ENGLISH_STOP_WORDS);
            result = new PorterStemFilter(result);

            return result;
        }
    }
}
