package com.chuanshuke.search.service;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.domain.search.SearchResult;
import com.chuanshuke.search.dao.SearchDao;
import com.chuanshuke.search.dao.TbBookRepository;

@Service
public class SearchService {
	
	@Autowired
	private TbBookRepository tbBookRepository;
	
	@Autowired
	private SearchDao searchDao;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private SolrClient solrClient;
	
	public TbBook getBookByName(String name) {
		return tbBookRepository.findByName(name);
	}
	
	public void saveBookToSolr(TbBook book) throws IOException, SolrServerException {
		solrClient.addBean(book);
		solrClient.commit();
	}
	
	public SearchResult getBookFromSolr(String keywords) throws Exception {
		//新建查询
		SolrQuery solrQuery = new SolrQuery();
		//设置查询条件
		solrQuery.setQuery(keywords);
		//设置默认搜索域
		solrQuery.set("df", "book_keywords");
		//开启高亮显示
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("name");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		SearchResult searchResult = searchDao.search(solrQuery);		
		return searchResult;
	}
	
	

}
