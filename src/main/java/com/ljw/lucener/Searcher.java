package com.ljw.lucener;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 
 * 索引工具类，使用这个类，调用索引
 * 
 * @author ljw 
 *
 */
public class Searcher {

	/**
	 * 如果使用这个方法，修改此方法，把需要的结果进行整理
	 */
	public static void search(String indexDir, String query) throws IOException, ParseException {
		Directory dir = FSDirectory.open(Paths.get(indexDir));
		IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(dir));
		
		Analyzer analyzer = new IKAnalyzer(true);
		QueryParser parser = new QueryParser("contents", analyzer);
		parser.setDefaultOperator(QueryParser.AND_OPERATOR);
		Query q = parser.parse(query);
		
		
		
		long start = System.currentTimeMillis();
		
		TopDocs hits = searcher.search(q, 10);//搜索过程
		
		long end = System.currentTimeMillis();
		
		
		//搜索条数
		System.out.println(hits.totalHits);
		
		//使用时间
		System.out.println(end - start);
		
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.doc(scoreDoc.doc);
			//搜索结果全路径
			System.out.println(doc.get("fullpath"));
			
		}
	}

	public static void main(String[] args) throws IOException, ParseException {
		String indexDir = "D:/lucene_index";
		String query = "李金悟";
		search(indexDir, query);
	}
}
