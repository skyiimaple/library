package com.chuanshuke.search.test;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.search.dao.TbBookRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SolrIndexInport {
	
	@Autowired
	SolrClient solrClient;
	
	@Autowired
	TbBookRepository bookRepository;
	
	@Test
	public void testInputIndex() throws SolrServerException, IOException {
		List<TbBook> list = bookRepository.findAll();
		if(list.size() >0) {
			solrClient.addBeans(list);
		}
		
		
		
	}

}
