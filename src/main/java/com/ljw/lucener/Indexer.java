package com.ljw.lucener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.mysql.jdbc.Buffer;

import scala.annotation.bridge;

/**
 * 
 * 用来创建索引,提高查询搜索效率
 * 
 * 大致说一下Lucene就是通过创建索引这个类似书目录那样的东西来提升查询效率的一个框架，
 * 
 * 所谓索引: 我理解就是将文档等数据源的不同组成部分的指示标志，索引会指引使用者快速找到这些数据源不同组成部分。
 * 
 * @author ljw
 *
 */
public class Indexer {

	private IndexWriter writer;// 这个类用来写入索引

	
	
	// 下面这个类是FileFilter的实现类，用来过滤符合条件的文档。
	private static class TextFilesFilter implements FileFilter {
		
		@Override//过滤文件
		public boolean accept(File pathname) {
			boolean result = pathname.getName().toLowerCase().endsWith(".txt");
			return result;
		}
	}

	
	
	//构造方法，用来传入索引存放路径
	public Indexer(String indexDir) throws IOException {
	
		/**
		 * 分词器: 
		 * 进行数据解析的数据解析器，此解析器根据数据不同，可以选择不同的解析器
		 * 
		 * 1.new StandardAnalyzer() ： 支持英文单词，不支持中文，不支持单词拆分
		 * 2.new IKAnalyzer() : 可以支持中文检索，可以是2个中文词是不连续的，可以支持英文单词检索，但是对于中文的细分粒度无法确定，比如：西村镇，只能搜索西村镇出来，搜索西村，不能
		 * 
		 */
		Analyzer analyzer = new IKAnalyzer(true);
		
		
		Directory dir = FSDirectory.open(Paths.get(indexDir));//需要创建索引的数据目录
		
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		
		config.setOpenMode(OpenMode.CREATE_OR_APPEND);

		this.writer = new IndexWriter(dir, config);
	}

	
	
	// 关闭indexWriter
	public void close() throws IOException {
		writer.close();
	}

	
	
	// 这个方法是遍历文件夹下所有文件并选择符合条件文件写入索引的方法，返回写入的文档总数
	public int index(String dataDir, FileFilter filter) throws IOException {
		File[] files = new File(dataDir).listFiles();
		if (files == null) {
			throw new IOException("files目录	->"+dataDir+"不存在");
		}
		for (File file : files) {
			boolean result = (!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead() && (filter == null))
					|| filter.accept(file);
			if (result) {
				indexFile(file);
			}
		}
		return writer.numDocs();//创建的索引文件个数
	}

	// 这个方法是写入索引的方法，将生成的document对象写入到索引中
	private void indexFile(File file) throws IOException {
		System.out.println("indexing..." + file.getCanonicalPath());
		Document doc = getDocument(file);
		writer.addDocument(doc);
	}

	// 这个方法是生成Document对象的方法，Document对象就是对文档各个属性的封装
	protected Document getDocument(File file) throws IOException {
		
		
		Document doc = new Document();
		
		//doc.add(new Field("contents", new FileReader(file), TextField.TYPE_NOT_STORED));//如果文件内部有中文，无法识别文件内容
		doc.add(new Field("contents", new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK")), TextField.TYPE_NOT_STORED));
		doc.add(new Field("filename", file.getName(), TextField.TYPE_STORED));
		doc.add(new Field("fullpath", file.getCanonicalPath(), TextField.TYPE_STORED));
		return doc;
	}

	public static void main(String[] args) throws IOException {
		
		
		String indexDir = "D:/lucene_index";
		
		String dataDir = "D:/lucene_data";//此文件要存在，相当于给此文件内部的.txt创建索引

		long start = System.currentTimeMillis();
		
		Indexer indexer = new Indexer(indexDir);
		int numberIndexed = indexer.index(dataDir, new TextFilesFilter());
		indexer.close();
		
		long end = System.currentTimeMillis();
		
		
		
		System.out.println(numberIndexed);
		System.out.println(end - start);
	}
}
