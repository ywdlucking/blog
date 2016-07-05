package com.ywd.blog.lucene;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.ywd.blog.entity.Blog;
import com.ywd.blog.entity.Config;
import com.ywd.blog.util.DateUtil;
import com.ywd.blog.util.StringUtil;

/**
 * 博客索引
 * @author ywd
 *
 */
public class BlogIndex {
	
	private Directory dir;
	
	/**
	 * 获取IndexWriter实例
	 * @return
	 * @throws Exception
	 */
	private IndexWriter getWriter()throws Exception{
		dir=FSDirectory.open(Paths.get(Config.blog_lucene_path));
		SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
		IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
		IndexWriter writer=new IndexWriter(dir, iwc);
		return writer;
	}
	
	/**
	 * 添加博客索引
	 * @param blog
	 * @throws Exception
	 */
	public void addIndex(Blog blog)throws Exception{
		IndexWriter writer = getWriter();
		Document doc = new Document();
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
		doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));
		doc.add(new TextField("content",blog.getNoTagContent(),Field.Store.YES));
		writer.addDocument(doc);
		writer.close();
	}
	
	/**
	 * 更新博客索引
	 * @param blog
	 * @throws Exception
	 */
	public void updateIndex(Blog blog)throws Exception{
		IndexWriter writer = getWriter();
		Document doc = new Document();
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
		doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));
		doc.add(new TextField("content",blog.getNoTagContent(),Field.Store.YES));
		writer.updateDocument(new Term("id",String.valueOf(blog.getId())), doc);
		writer.close();
	}
	
	/**
	 * 收索bolg
	 * @param q
	 * @return
	 * @throws Exception
	 */
	public List<Blog> search(String q) throws Exception {
		List<Blog> result = new ArrayList<Blog>();
		dir=FSDirectory.open(Paths.get(Config.blog_lucene_path));
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();//中文解析器
		
		BooleanQuery.Builder builder = new BooleanQuery.Builder();//构建多条件查询器
		QueryParser queryTitle = new QueryParser("title", analyzer);
		Query query1 = queryTitle.parse(q);
		QueryParser queryContent = new QueryParser("content", analyzer);
		Query query2 = queryContent.parse(q);
		builder.add(query1, BooleanClause.Occur.SHOULD);
		builder.add(query2, BooleanClause.Occur.SHOULD);
		
		TopDocs hits = searcher.search(builder.build(), 100);//获取得分高的前100条记录
		QueryScorer queryScorer = new QueryScorer(query1);//title得分高的排在前面
		
		Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);//关键字高亮
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
		Highlighter highlighter = new Highlighter(formatter, queryScorer);
		highlighter.setTextFragmenter(fragmenter);
		
		for(ScoreDoc scoreDoc:hits.scoreDocs){
			Blog blog = new Blog();
			Document doc = searcher.doc(scoreDoc.doc);
			blog.setId(Integer.parseInt(doc.get("id")));
			blog.setReleaseDateStr(doc.get("releaseDate"));
			String title=doc.get("title");
			String content=StringEscapeUtils.escapeHtml(doc.get("content"));//转义html标签
			if(title!=null){
				TokenStream tokenStream=analyzer.tokenStream("title", new StringReader(title));
				String hTitle=highlighter.getBestFragment(tokenStream, title);
				if(StringUtil.isEmpty(hTitle)){
					blog.setTitle(title);
				}else{
					blog.setTitle(hTitle);
				}
			}
			
			if(content!=null){
				TokenStream tokenStream=analyzer.tokenStream("content", new StringReader(content));
				String hContent=highlighter.getBestFragment(tokenStream, content);
				if(StringUtil.isEmpty(hContent)){
					if(content.length()<=200){
						blog.setContent(content);						
					}else{
						blog.setContent(content.substring(0, 200));	
					}
				}else{
					blog.setContent(hContent);
				}
			}
			result.add(blog);
		}
		
		return result;
	}
	
	/**
	 * 删除指定博客的索引
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception {
		IndexWriter writer = getWriter();
		writer.deleteDocuments(new Term("id", id));
		writer.forceMergeDeletes();//强制删除
		writer.commit();
		writer.close();
	}
}
