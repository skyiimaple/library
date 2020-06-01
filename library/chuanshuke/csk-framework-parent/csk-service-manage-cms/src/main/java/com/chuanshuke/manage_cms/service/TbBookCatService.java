package com.chuanshuke.manage_cms.service;

import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chuanshuke.framework.domain.cms.TbBookCat;
import com.chuanshuke.framework.domain.cms.ext.TbBookCatNode;
import com.chuanshuke.framework.domain.cms.response.CmsBookCatResult;
import com.chuanshuke.framework.domain.cms.response.CmsCode;
import com.chuanshuke.framework.exception.ExceptionCast;
import com.chuanshuke.framework.model.response.CommonCode;
import com.chuanshuke.framework.model.response.QueryResponseResult;
import com.chuanshuke.framework.model.response.QueryResult;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.manage_cms.dao.TbBookCatMapper;
import com.chuanshuke.manage_cms.dao.TbBookCatRepository;

@Service
public class TbBookCatService {
	
	@Autowired
	private TbBookCatRepository tbBookCatRepository;
	
	@Autowired
	private TbBookCatMapper tbbookCatMapper;
	
	public QueryResponseResult<TbBookCat> findList(int page, int size){
		
//		if(queryBookCatRequest == null) {
//			queryBookCatRequest = new QueryBookCatRequest();
//		}
		//自定义查询条件
//		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("isParent", ExampleMatcher.GenericPropertyMatchers.contains());
		//条件值对象
//		TbBookCat tbBookCat = new TbBookCat();
		//设置条件值
		//是否为父节点
//		if(StringUtils.isNotEmpty(queryBookCatRequest.getIsParent().toString())) {
//			tbBookCat.setIsParent(queryBookCatRequest.getIsParent());
//		}
		//父节点
//		if(StringUtils.isNotEmpty(queryBookCatRequest.getParentId().toString())) {
//			tbBookCat.setParentId(queryBookCatRequest.getParentId());
//		}
		//定义条件对象
//		Example<TbBookCat> example = Example.of(tbBookCat, exampleMatcher);
		//分页参数
		if(page <= 0) {
			page = 1;
		}
		page = page -1;
		if(size <= 0) {
			size = 10;
		}
		Page<TbBookCat> findAll = tbBookCatRepository.findAll(PageRequest.of(page, size));  //自定义查询条件并分页
		QueryResult<TbBookCat> queryResult = new QueryResult<TbBookCat>();
		queryResult.setList(findAll.getContent());
		queryResult.setTotal(findAll.getTotalElements());
		QueryResponseResult<TbBookCat> queryResponseResult = new QueryResponseResult<TbBookCat>(CommonCode.SUCCESS, queryResult);		
		return queryResponseResult;
	}
	
	@Transactional
	public CmsBookCatResult add(TbBookCat tbBookCat) {
		if(tbBookCat == null) {
			
		}
		
		//查询分类，如果已存在，添加失败
		TbBookCat bookCat = tbBookCatRepository.findByName(tbBookCat.getName());
		if(bookCat != null) {
			//标签已存在
			ExceptionCast.cast(CmsCode.CMS_CAT_ISEXIT);
		}
		
		tbBookCat.setId(null);
		tbBookCat.setCreated(new Date());
		tbBookCat.setUpdated(new Date());
		tbBookCatRepository.save(tbBookCat);
		
		return new CmsBookCatResult(CommonCode.SUCCESS, tbBookCat);
	}
	
	//根据ID查询分类
	public TbBookCat getByid(Long id) {
		Optional<TbBookCat> optional = tbBookCatRepository.findById(id);
		if(optional.isPresent()) {
			TbBookCat tbBookCat = optional.get();
			return tbBookCat;
		}		
		return null;
	}
	
	//编辑分类信息
	@Transactional
	public CmsBookCatResult update(Long id, TbBookCat tbBookCat) {
		TbBookCat one = getByid(id);
		if(one != null) {
			one.setName(tbBookCat.getName());
			one.setIsParent(tbBookCat.getIsParent());
			one.setParentId(tbBookCat.getParentId());
			one.setSortOrder(tbBookCat.getSortOrder());
			one.setStatus(tbBookCat.getStatus());
			one.setUpdated(new Date());
			tbBookCatRepository.save(one);
			return new CmsBookCatResult(CommonCode.SUCCESS, one);
		}
		return new CmsBookCatResult(CommonCode.FAIL, null);
	}
	
	public ResponseResult delete(Long id) {
		TbBookCat one = tbBookCatRepository.getOne(id);
		if(one == null || one.getIsParent() == Integer.valueOf("1")) {
			
			return new ResponseResult(CmsCode.CMS_CAT_IS_NOT_LEAF);
		}
		tbBookCatRepository.deleteById(id);
		return new ResponseResult(CommonCode.SUCCESS);
	}
	
	//分类标签查询
	public TbBookCatNode findTbBookCatList() {
		return tbbookCatMapper.selectList();
	}
	

}
