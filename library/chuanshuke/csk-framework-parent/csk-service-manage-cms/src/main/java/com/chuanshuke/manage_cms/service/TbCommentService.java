package com.chuanshuke.manage_cms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chuanshuke.framework.domain.cms.TbComment;
import com.chuanshuke.framework.domain.cms.response.CmsCode;
import com.chuanshuke.framework.domain.cms.response.CmsCommentResult;
import com.chuanshuke.framework.domain.portal.CommentInfo;
import com.chuanshuke.framework.model.response.CommonCode;
import com.chuanshuke.framework.model.response.QueryResponseResult;
import com.chuanshuke.framework.model.response.QueryResult;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.manage_cms.dao.TbCommentMapper;
import com.chuanshuke.manage_cms.dao.TbCommentRepository;

@Service
public class TbCommentService {
	
	@Autowired
	private TbCommentRepository tbCommentRepository;
	
	@Autowired
	private TbCommentMapper tbCommentMapper;
	
	public QueryResponseResult<TbComment> list(int page, int size){
		
		if(page <= 0) {
			page = 1;
		}
		page = page-1;
		if(size <= 0) {
			size = 10;
		}
		Page<TbComment> list = tbCommentRepository.findAll(PageRequest.of(page, size));
		QueryResult<TbComment> result = new QueryResult<TbComment>();
		result.setList(list.getContent());
		QueryResponseResult<TbComment> queryResponseResult = new QueryResponseResult<TbComment>(CommonCode.SUCCESS, result);
		
		return  queryResponseResult;
		
	}
	
	@Transactional
	public CmsCommentResult add(Long uid, Long bid, TbComment tbComment) {
		
		if(tbComment == null) {
			return new CmsCommentResult(CommonCode.FAIL, null);
		}
		TbComment comment = new TbComment();
		comment.setUid(uid);
		comment.setBid(bid);
		comment.setScore(tbComment.getScore());
		comment.setContent(tbComment.getContent());
		comment.setCreated(new Date());
		comment.setUpdated(new Date());		
		
		tbCommentRepository.save(comment);
		
		return new CmsCommentResult(CommonCode.SUCCESS, comment);
	}
	
	public TbComment getCommentById(Long id) {
		
		Optional<TbComment> optional = tbCommentRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public ResponseResult delete(Long id) {
		
		TbComment comment = getCommentById(id);
		
		if(comment == null) {
			return new ResponseResult(CmsCode.CMS_COMMENT_IS_NOT_EXIT_OR_DELETED);
		}
		
		tbCommentRepository.deleteById(id);
		
		return new ResponseResult(CommonCode.SUCCESS);
	}
	
	public QueryResponseResult<CommentInfo> listByBid(Long bid){
		List<CommentInfo> comments = tbCommentMapper.listCommentInBookDetail(bid);
		QueryResult<CommentInfo> queryResult = new QueryResult<CommentInfo>();
		queryResult.setList(comments);
		queryResult.setTotal(comments.size());
		return new QueryResponseResult<CommentInfo>(CommonCode.SUCCESS, queryResult);
	}

}
