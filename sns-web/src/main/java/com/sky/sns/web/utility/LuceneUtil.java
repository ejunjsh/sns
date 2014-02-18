package com.sky.sns.web.utility;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.sky.sns.mongo.entity.SearchKeyWord;
import com.sky.sns.mongo.iRepository.ISearchKeyWordRepository;
import com.sky.sns.mybatis.entity.DocumentEntity;
import com.sky.sns.mybatis.searchEntity.DocSearchParams;


public class LuceneUtil {

	private String indexPath;

	private int maxRecordNumber;
	
	private ISearchKeyWordRepository searchKeyWordRepository;

	public void createIndex(List<DocumentEntity> entities) {
		try {
			String realPath = LuceneUtil.class.getResource("/").getPath();
			realPath = realPath.substring(0, realPath.lastIndexOf("WEB-INF"));
			realPath = realPath + indexPath;

			Directory dir = FSDirectory.open(new File(realPath));
			Analyzer analyzer = new IKAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_43,
					analyzer);
			iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
			IndexWriter writer = new IndexWriter(dir, iwc);

			for (DocumentEntity entity : entities) {
				Document doc = new Document();
				doc.add(new StringField("id", entity.getId(), Field.Store.YES));
				doc.add(new TextField("content", entity.getContent(),
						Field.Store.YES));
				doc.add(new StringField("date", DateTools.dateToString(
						entity.getDate(), DateTools.Resolution.MILLISECOND),
						Field.Store.YES));
				doc.add(new StringField("fromName", entity.getFromName(),
						Field.Store.YES));
				doc.add(new StringField("fromValue",
						entity.getFromValue() + "", Field.Store.YES));
				doc.add(new StringField("remark", entity.getRemark(),
						Field.Store.YES));
				doc.add(new TextField("tagString", entity.getTagString(),
						Field.Store.YES));
				doc.add(new TextField("title", entity.getTitle(),
						Field.Store.YES));
				doc.add(new IntField("type", entity.getType(), Field.Store.YES));
				writer.updateDocument(new Term("id", entity.getId()), doc);
			}

			writer.close();
			dir.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<DocumentEntity> searchIndex(DocSearchParams params) {
		try {
			String realPath = LuceneUtil.class.getResource("/").getPath();
			realPath = realPath.substring(0, realPath.lastIndexOf("WEB-INF"));
			realPath = realPath + indexPath;

			IndexReader reader = DirectoryReader.open(FSDirectory
					.open(new File(realPath)));
			IndexSearcher searcher = new IndexSearcher(reader);
			Analyzer analyzer = new IKAnalyzer();
			
			//statistic search key
			List<String> keys=splitKeywords(params.getKeyWord(),analyzer);
			if(keys!=null&&keys.size()>0)
			{
				for(String k :keys)
				{
					SearchKeyWord key=new SearchKeyWord();
					key.setCreatedDate(new Date());
					key.setKeyWord(k);
					searchKeyWordRepository.insert(key);
				}
			}
			

			String[] fields = { "title", "content", "tagString" };
			BooleanClause.Occur[] flags = { BooleanClause.Occur.SHOULD,
					BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD };
			TopDocs topDocs = null;
			Query multiQuery = null;
            
			BooleanQuery query = new BooleanQuery();
			multiQuery = MultiFieldQueryParser.parse(Version.LUCENE_43,
					params.getKeyWord(), fields, flags, analyzer);
			NumericRangeQuery<Integer> rangeQuery;
			if (params.getType() > 0) {
				rangeQuery = NumericRangeQuery.newIntRange("type",
						params.getType(), params.getType(), true, true);
			} else {
				rangeQuery = NumericRangeQuery.newIntRange("type", 1, 4, true,
						true);
			}
			query.add(multiQuery, BooleanClause.Occur.MUST);
			query.add(rangeQuery, BooleanClause.Occur.MUST);

			topDocs = searcher.search(query, maxRecordNumber);

			// 输出结果
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;

			params.setTotal(topDocs.totalHits);

			// 查询起始记录位置
			int begin = params.getPageSize() * (params.getPageIndex());
			// 查询终止记录位置
			int end = Math.min(begin + params.getPageSize(), scoreDocs.length);

			List<DocumentEntity> docs = new ArrayList<DocumentEntity>();
			for (int i = begin; i < end; i++) {
				Document targetDoc = searcher.doc(scoreDocs[i].doc);
				DocumentEntity doc = new DocumentEntity();
				doc.setContent(highligher(targetDoc.get("content"), multiQuery,
						"content", analyzer));
				doc.setDate(DateTools.stringToDate(targetDoc.get("date")));
				if (targetDoc.get("fromName") != null)
					doc.setFromName(targetDoc.get("fromName"));
				if (targetDoc.get("fromValue") != null)
					doc.setFromValue(Long.parseLong(targetDoc.get("fromValue")));
				doc.setId(targetDoc.get("id").substring(1));
				doc.setRemark(targetDoc.get("remark"));
				doc.setTagString(highligher(targetDoc.get("tagString"),
						multiQuery, "tagString", analyzer));
				doc.setTitle(highligher(targetDoc.get("title"), multiQuery,
						"title", analyzer));
				doc.setType(Integer.parseInt(targetDoc.get("type")));

				docs.add(doc);
			}

			return docs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String highligher(String text, Query query, String field,
			Analyzer analyzer) throws IOException, InvalidTokenOffsetsException {
		try {
			QueryScorer scorer = new QueryScorer(query);
			Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
			// 关键字被包住的内容
			Formatter formatter = new SimpleHTMLFormatter(
					"<font style='color:red'>", "</font>");
			Highlighter lighter = new Highlighter(formatter, scorer);
			lighter.setTextFragmenter(fragmenter);
			String ht = lighter.getBestFragment(analyzer, field, text);
			if (ht == null) {
				// 当内容超过两百字时，截取字符串
				if (text.length() >= 200) {
					text = text.substring(0, 200);
					text = text + "....";
				}
				return text;
			} else {
				return ht.trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	
	private List<String> splitKeywords(String keyword,Analyzer analyzer)
	{
		 TokenStream ts = null;
		 List<String> list=new ArrayList<String>(10);
			try {
				ts = analyzer.tokenStream("myfield", new StringReader(keyword));
			    //获取词元文本属性
			    CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
			    
			    
			    //重置TokenStream（重置StringReader）
				ts.reset(); 
				//迭代获取分词结果
				while (ts.incrementToken()) {
				   list.add(term.toString());
				}
				//关闭TokenStream（关闭StringReader）
				ts.end();   // Perform end-of-stream operations, e.g. set the final offset.

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				//释放TokenStream的所有资源
				if(ts != null){
			      try {
					ts.close();
			      } catch (IOException e) {
					e.printStackTrace();
			      }
				}
		    }
			return list;
	}

	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}

	public void setMaxRecordNumber(int maxRecordNumber) {
		this.maxRecordNumber = maxRecordNumber;
	}

	public void setSearchKeyWordRepository(ISearchKeyWordRepository searchKeyWordRepository) {
		this.searchKeyWordRepository = searchKeyWordRepository;
	}
}
