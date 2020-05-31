package com.chuanshuke.portal.test;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.domain.portal.RecommendData;
import com.chuanshuke.framework.model.response.QueryResponseResult;
import com.chuanshuke.protal.PortalApplication;
import com.chuanshuke.protal.mapper.RecommendMapper;
import com.chuanshuke.protal.service.RecommendService;

@SpringBootTest(classes = PortalApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RecommendTest {
	
	@Autowired
	RecommendMapper recommendMapper;
	
	@Autowired
	RecommendService recommendService;
	
	@Test
	public void getData() {
		List<RecommendData> data = recommendMapper.getData();
		for (RecommendData recommendData : data) {
			System.out.println(recommendData.toString());
			
		}
	}
	
	@Test
	public void genTrainDataTest() {
		List<RecommendData> data = recommendMapper.getData();
//		Map<String, Set<String>> genTrainData = recommendService.genTrainData(data);
//		Set<Entry<String, Set<String>>> entrySet = genTrainData.entrySet();
//		for (Entry<String, Set<String>> entry : entrySet) {
//			System.out.println(entry.getKey()+ "====" + entry.getValue());
//		}
//		Map<String, Set<String>> userItems = recommendService.getUserItems(genTrainData);
//		
//		Set<Entry<String, Set<String>>> entrySet1 = userItems.entrySet();
//		for (Entry<String, Set<String>> entry : entrySet1) {
//			System.out.println(entry.getKey()+ "====" + entry.getValue());
//		}
		
	}
	
	@Test
	public void recommendTest() {
		String username = "test";
//		List<String> genRecommendBook = recommendService.genRecommendBook(username);
//		System.out.println(genRecommendBook);
		QueryResponseResult<TbBook> genRecommendBook = recommendService.genRecommendBook(username);
		System.out.println(genRecommendBook);
		
	}
	
	@Test
	public void recommendHot() {
		List<TbBook> hotTop = recommendMapper.getHotTop();
		System.out.println(hotTop);
	}

}
