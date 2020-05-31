package com.chuanshuke.search.test;

import java.io.IOException;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.search.SearchApplication;

@SpringBootTest(classes = SearchApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSolrClient {
	
	@Autowired
	private SolrClient solrClient;
	
	@Test
	public void add() throws IOException, SolrServerException {
		TbBook book = new TbBook();
		book.setName("test");
		book.setAuthor("test");
		book.setImage("test");
		book.setIsbn("test");
		book.setPublisher("test");
		solrClient.addBean(book);
		solrClient.commit();
		
	}
	
	@Test
	public void query() throws SolrServerException, IOException {
//		String query = "name:test";
//		SolrQuery solrQuery = new SolrQuery(query);
		String keywords = "张三";
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("df","name");
		solrQuery.setQuery(keywords);
		QueryResponse response = solrClient.query(solrQuery);
		SolrDocumentList results = response.getResults();
		long numFound = results.getNumFound();
		System.out.println("总条数" + numFound);
		for (SolrDocument solrDocument : results) {
			Collection<String> fieldNames = solrDocument.getFieldNames();
			for(String str : fieldNames) {
				System.out.println(str);
				
			}
		}
		
	}
}
