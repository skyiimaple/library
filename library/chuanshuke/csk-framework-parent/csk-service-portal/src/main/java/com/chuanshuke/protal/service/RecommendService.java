package com.chuanshuke.protal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.domain.portal.RecommendData;
import com.chuanshuke.framework.model.response.CommonCode;
import com.chuanshuke.framework.model.response.QueryResponseResult;
import com.chuanshuke.framework.model.response.QueryResult;
import com.chuanshuke.protal.mapper.RecommendBookRepository;
import com.chuanshuke.protal.mapper.RecommendMapper;

@Service
public class RecommendService {
	
	@Autowired
	RecommendMapper recommendMapper;
	
	@Autowired
	RecommendBookRepository recommendBookRepository;
	
	//未登录热门推荐
	public QueryResponseResult<TbBook> recommendHot(){
		QueryResult<TbBook> queryResult = new QueryResult<TbBook>();
		List<TbBook> hotTop = recommendMapper.getHotTop();
		if(hotTop != null && hotTop.size() > 0) {
			queryResult.setList(hotTop);
		}
		return new QueryResponseResult<TbBook>(CommonCode.SUCCESS, queryResult);
		
	}
	
	//个性化推荐图书
	public QueryResponseResult<TbBook> genRecommendBook(String username){
		Map<String,Set<String>> trainData = genTrainData(recommendMapper.getData());
		Map<String,Integer> item_id = getItemId(trainData);
//		System.out.println("训练数据（a-物品，A-用户）：");
//		for(String key:trainData.keySet()) {
//			System.out.println(key + " " + trainData.get(key));
//		}
		
		int S = trainData.size();
		int[][] sparseMatrix = new int[S][S];
		
		//倒排表
		Map<String,Set<String>> user_items = getUserItems(trainData);
		
//		System.out.println("用户物品倒排表：");
//		for(String key:user_items.keySet()) {
//			System.out.println(key + " " + user_items.get(key));
//		}
		Map<String,Set<String>> relatedItemMap = new HashMap<String,Set<String>>();
		int[] N = new int[S];
		for(Entry<String,Set<String>> entry:user_items.entrySet()) {
			for(String i:entry.getValue()) {
				N[item_id.get(i)] += 1;
				for(String j:entry.getValue()) {
					if(i.equals(j)) {
						continue;
					}
					sparseMatrix[item_id.get(i)][item_id.get(j)] += 1;
					if(relatedItemMap.get(i) != null) {
						relatedItemMap.get(i).add(j);
					} else {
						Set<String> relatedUser = new HashSet<String>();
						relatedUser.add(j);
						relatedItemMap.put(i, relatedUser);
					}
				}
			}
		}
		double[][] W = new double[S][S];
		for(String i:relatedItemMap.keySet()) {
			for(String j:relatedItemMap.get(i)) {
				int iid = item_id.get(i);
				int jid = item_id.get(j);
				W[iid][jid] = sparseMatrix[iid][jid] / (Math.sqrt(N[iid] * N[jid]));
			}
		}
//		System.out.println("物品相似度矩阵：");
//		for(int i = 0; i < W.length; i++) {
//			for(int j = 0; j < W[i].length; j++) {
//				System.out.printf("%-10s\t",(float)W[i][j]);
//			}
//			System.out.println();
//		}
		
		String recUser = username;
		Set<String> item_hasd = user_items.get(recUser);
		Map<String,Float> rank = new HashMap<String,Float>();
		for(String i:item_hasd) {
			for(String r:relatedItemMap.get(i)) {
				if(item_hasd.contains(r)) {
					continue;
				}
				if(rank.get(r) != null) {
					float score = rank.get(r);
					score += W[item_id.get(i)][item_id.get(r)];
					rank.put(r, score);
				} else {
					rank.put(r, (float) W[item_id.get(i)][item_id.get(r)]);
				}
			}
		}
//		System.out.println("推荐结果：");
//		for(Entry<String,Float> entry:rank.entrySet()) {
//			System.out.println(entry.getKey() + " " + entry.getValue());
//		}
		List<String> books = new ArrayList<String>();
		books.addAll(rank.keySet());
		List<TbBook> list = recommendBookRepository.findByNameIn(books);
		QueryResult<TbBook> queryResult = new QueryResult<TbBook>();
		queryResult.setList(list);
		
		return new QueryResponseResult<TbBook>(CommonCode.SUCCESS, queryResult);
	}
	
	
	//用户物品倒排表
	private static Map<String, Set<String>> getUserItems(Map<String, Set<String>> trainData) {
		Map<String, Set<String>> item_users = new HashMap<String, Set<String>>();
		for (Entry<String, Set<String>> entry : trainData.entrySet()) {
			for (String item : entry.getValue()) {
				if (item_users.get(item) != null) {
					item_users.get(item).add(entry.getKey());
				} else {
					Set<String> users = new HashSet<String>();
					users.add(entry.getKey());
					item_users.put(item, users);
				}
			}
		}

		return item_users;
	}
	
	//辅助表
	private static Map<String,Integer> getItemId(Map<String,Set<String>> trainData) {
		Map<String,Integer> user_id = new HashMap<String,Integer>();
		int id = 0;
		for(String user:trainData.keySet()) {
			user_id.put(user, id);
			id++;
		}
		return user_id;
	}
	
	//生成训练数据
	private static Map<String, Set<String>> genTrainData(List<RecommendData> data){
		
		Set<String> books = new HashSet<String>();
		Map<String, Set<String>> trainData = new HashMap<String, Set<String>>();
		for (RecommendData recommendData : data) {
			books.add(recommendData.getBook());			
		}
		for (String book : books) {
			for(RecommendData reData: data) {
				if(book.equals(reData.getBook())) {
					if(trainData.get(book) != null) {
						trainData.get(book).add(reData.getUser());						
					}else {
						Set<String> users = new HashSet<String>();
						users.add(reData.getUser());
						trainData.put(book, users);
					}
				}
			}
		}
		return trainData;
	}
	

}
