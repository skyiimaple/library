package com.chuanshuke.manage_cms.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
	
	//交换机
	public static final String EX_ROUTING_CMS_BOOK = "ex_routing_cms_book";
	
	@Bean("EX_ROUTING_CMS_BOOK")
	public Exchange EXCHANGE_TOPICS_INFO() {
		return ExchangeBuilder.directExchange(EX_ROUTING_CMS_BOOK).durable(true).build();
	}

}
