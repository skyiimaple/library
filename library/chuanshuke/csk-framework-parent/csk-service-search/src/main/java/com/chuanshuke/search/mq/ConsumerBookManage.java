package com.chuanshuke.search.mq;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.search.service.SearchService;

@Component
public class ConsumerBookManage {
	private final static Logger LOGGER = LoggerFactory.getLogger(ConsumerBookManage.class);
	
	@Autowired
	private SearchService searchService;
	
	@RabbitListener(queues = "${chuanshuke.mq.queue.add}")
	public void addIndex(String msg) {
		//解析消息
        Map map = JSON.parseObject(msg, Map.class);
        //得到消息中图书名
        String name = (String) map.get("name");
        //校验页面是否合法
        TbBook book = searchService.getBookByName(name);
        if(book == null){
            LOGGER.error("receive postpage msg,book is null,name:{}",name);
            return ;
        }
        //将图书信息添加到solr
        try {
        	searchService.saveBookToSolr(book);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			LOGGER.error("添加索引失败");
			e.printStackTrace();
		}
		
	}

}
