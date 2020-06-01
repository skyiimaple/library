package com.chuanshuke.protal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.domain.cms.TbBookCat;
import com.chuanshuke.framework.domain.cms.TbComment;
import com.chuanshuke.framework.domain.cms.request.QueryBookRequest;
import com.chuanshuke.framework.domain.portal.BookInfo;
import com.chuanshuke.framework.domain.portal.CommentInfo;
import com.chuanshuke.framework.domain.portal.response.PortalCommentResult;
import com.chuanshuke.framework.domain.portal.response.PortalResult;
import com.chuanshuke.framework.model.response.CommonCode;
import com.chuanshuke.framework.model.response.QueryResponseResult;
import com.chuanshuke.protal.client.CmsClient;
import com.chuanshuke.protal.mapper.BookInfoMapper;

@Service
public class BookInfoService {
	
	@Autowired
	CmsClient cmsClient;
	
	@Autowired
	private BookInfoMapper bookInfoMapper;
	
	public QueryResponseResult<TbBook> listBook(int page, int size, QueryBookRequest queryBookRequest){
		
		return cmsClient.bookList(page, size, queryBookRequest);
		
	}
	
	public QueryResponseResult<TbBookCat> listCat(int page, int size){
		return cmsClient.catList(page, size);
	}
		
	public PortalResult getBookInfo(String name) {
		BookInfo bookInfo = bookInfoMapper.getBookInfoById(name).get(0);
		if(bookInfo == null) {
			return new PortalResult(CommonCode.FAIL, null);
		}
		return new PortalResult(CommonCode.SUCCESS, bookInfo);
	}
	
	public QueryResponseResult<CommentInfo> getCommentsByBid(Long bid) {
		QueryResponseResult<CommentInfo> commentListInBookDetail = cmsClient.commentListInBookDetail(bid);
		return commentListInBookDetail;
	}
	

}
