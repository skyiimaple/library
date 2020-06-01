package com.chuanshuke.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.domain.search.SearchResult;

@Component
public class SearchDao {
	
	@Autowired
	private SolrClient solrClient;
	
	public SearchResult search(SolrQuery query) throws Exception {
		//根据query查询索引库
		QueryResponse queryResponse = solrClient.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		//取查询结果总记录数
		long numFound = solrDocumentList.getNumFound();
		SearchResult result = new SearchResult();
		result.setRecordCount(numFound);
		//获取列表，需要取高亮显示
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		List<TbBook> bookList = new ArrayList<TbBook>();
		for (SolrDocument solrDocument : solrDocumentList) {
			TbBook book = new TbBook();
			book.setName((String) solrDocument.get("name"));
			book.setSubtitle((String) solrDocument.get("subtitle"));
			book.setAuthor((String) solrDocument.get("author"));
			book.setTranslator((String) solrDocument.get("translator"));
			book.setIsbn((String) solrDocument.get("ISBN"));
			book.setPublisher((String) solrDocument.get("publisher"));
			book.setImage((String) solrDocument.get("image"));
			book.setBid((Long) solrDocument.get("book_category_name"));
			//取高亮显示
			List<String> list = highlighting.get(solrDocument.get("id")).get("name");
			String name = "";
			if(list != null && list.size() > 0) {
				name = list.get(0);
			}else {
				name = (String) solrDocument.get("name");
			}
			book.setName(name);
			//添加到列表
			bookList.add(book);
		}
		result.setList(bookList);
		//返回结果
		return result;	
		
	}

}
