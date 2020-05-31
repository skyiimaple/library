package com.chuanshuke.manage_cms.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.domain.cms.TbBookDesc;
import com.chuanshuke.manage_cms.dao.TbBookDescRepository;
import com.chuanshuke.manage_cms.dao.TbBookRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DataInportTest {
	
	@Autowired
	private TbBookRepository bookRepository;
	
	@Autowired
	private TbBookDescRepository bookDescRepository;
	
	@Test
	public void insertBook() throws IOException, InterruptedException {
//		Stream<String> stramValue = Stream.of(new String(Files.readAllBytes(Paths.get("CommonTest.java")),StandardCharsets.UTF_8));
//		
//		System.out.println(string.substring(0,1).toUpperCase().concat(string.substring(1)));
				
				
		ClassPathResource resource = new ClassPathResource("/data/wenhua/bookDetail_wenhua_begin1_end2.json");
		
		File file = resource.getFile();
		String readFileToString = FileUtils.readFileToString(file, "utf-8");
//		System.out.println(readFileToString);
//		Map map = (Map) JSON.parse(readFileToString);
		List<Map> javaList = JSON.parseArray(readFileToString).toJavaList(Map.class);
//		List<TbBook> books = new ArrayList<TbBook>();
//		List<TbBookDesc> descs = new ArrayList<TbBookDesc>();
		for (Map map : javaList) {
			String author = String.valueOf(map.get("author"));
			String authorProfile = String.valueOf(map.get("authorProfile"));
			if (author == null || author == "" /* || authorProfile == null || authorProfile == "" */) {
				continue;
			}
			TbBook book = new TbBook();
			book.setName(String.valueOf(map.get("name")));  //name
			book.setSubtitle(String.valueOf(map.get("subtitle")));  //subtitle
			book.setTranslator(String.valueOf(map.get("translator")));  //translator
			book.setIsbn(String.valueOf(map.get("isbn")));  //isbn
			book.setPublisher(String.valueOf(map.get("publisher")));  //publisher
			book.setTotalPage(Long.valueOf(String.valueOf(map.get("totalPage"))));  //totalPage
			book.setImage(String.valueOf(map.get("image")));  //image
			book.setStatus(Integer.valueOf(String.valueOf(map.get("status"))));  //status
			book.setBid(4L);  //bid
			book.setAuthor(author);//author
			book.setCreated(new Date());
			book.setUpdated(new Date());
			
			TbBook one = bookRepository.findByName(String.valueOf(map.get("name")));
			if(one == null) {
				bookRepository.save(book);
				Thread.sleep(100);
			}
//			books.add(book);
			TbBook abook = bookRepository.findByName(String.valueOf(map.get("name")));
			TbBookDesc desc = new TbBookDesc();
			desc.setBookId(abook.getId());
			desc.setCatalogue(String.valueOf(map.get("catalogue")));
			desc.setContentDesc(String.valueOf(map.get("contentDesc")));
			desc.setAuthorProfile(authorProfile);
//			descs.add(desc);
			bookDescRepository.save(desc);
			
			
		}
//		bookRepository.saveAll(books);  
//		bookDescRepository.saveAll(descs);
		
		
		
		
		
		
		
	}
	

}
