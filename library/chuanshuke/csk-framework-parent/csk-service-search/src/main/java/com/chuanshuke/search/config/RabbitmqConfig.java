package com.chuanshuke.search.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
	
	//交换机名称
	public static final String EX_ROUTING_CMS_BOOK = "ex_routing_cms_book";
	//队列bean名称
	public static final String QUEUE_CMS_BOOK_ADD = "queue_cms_book_add";
	//队列名称
	@Value("${chuanshuke.mq.queue.add}")
	public String QUEUE_CMS_BOOK_ADD_NAME;
	
	//routingKey
	@Value("${chuanshuke.mq.routingKey.add}")
	public String QUEUE_ROUTING_KEY_CMS_BOOK_ADD;
	
	//声明交换机
	@Bean(EX_ROUTING_CMS_BOOK)
	public Exchange EXCHANGE_TOPICS_INFO() {
		return ExchangeBuilder.directExchange(EX_ROUTING_CMS_BOOK).durable(true).build();
	}
	
	//声明队列
	@Bean(QUEUE_CMS_BOOK_ADD)
	public Queue QUEUE_CMS_BOOK_ADD() {
		Queue queue = new Queue(QUEUE_CMS_BOOK_ADD_NAME);
		return queue;
	}
	
	//绑定队列到交换机
	@Bean
	public Binding BINDING_QUEUE_INFO_ADD(@Qualifier(QUEUE_CMS_BOOK_ADD) Queue queue, 
			@Qualifier(EX_ROUTING_CMS_BOOK) Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(QUEUE_ROUTING_KEY_CMS_BOOK_ADD).noargs();
	}

}
