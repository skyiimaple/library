package com.chuanshuke.search.controller;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuanshuke.api.search.SearchControllerApi;
import com.chuanshuke.framework.domain.search.SearchResult;
import com.chuanshuke.search.service.SearchService;


@RestController
@RequestMapping("/search")
public class SearchController implements SearchControllerApi {
	
	@Autowired
	private SolrClient solrClient;
	
	@Autowired
	private SearchService searchService;

	@GetMapping("/get")
	@Override
	public SearchResult search(@RequestParam String keywords) {
		
		try {
			return searchService.getBookFromSolr(keywords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
