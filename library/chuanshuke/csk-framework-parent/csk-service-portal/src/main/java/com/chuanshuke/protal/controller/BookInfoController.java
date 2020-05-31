package com.chuanshuke.protal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuanshuke.api.portal.PortalControllerApi;
import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.domain.cms.TbBookCat;
import com.chuanshuke.framework.domain.cms.request.QueryBookRequest;
import com.chuanshuke.framework.domain.portal.CommentInfo;
import com.chuanshuke.framework.domain.portal.response.PortalCommentResult;
import com.chuanshuke.framework.domain.portal.response.PortalResult;
import com.chuanshuke.framework.model.response.QueryResponseResult;
import com.chuanshuke.protal.service.BookInfoService;
import com.chuanshuke.protal.service.RecommendService;

@RestController
@RequestMapping("/portal")
public class BookInfoController implements PortalControllerApi {
	
	@Autowired
	private BookInfoService bookInfoService;
	
	@Autowired
	private RecommendService recommendService;

	@RequestMapping("/list/book/{page}/{size}")
	@Override
	public QueryResponseResult<TbBook> list(
			@PathVariable("page")int page, 
			@PathVariable("size")int size, 
			QueryBookRequest queryBookRequest) {
		return bookInfoService.listBook(page, size, queryBookRequest);
	}
	
	@GetMapping("/get/book/{id}")
	@Override
	public PortalResult getBookInfo(@PathVariable Long id) {
		return bookInfoService.getBookInfo(id);
	}

	@GetMapping("/list/cat/{page}/{size}")
	@Override
	public QueryResponseResult<TbBookCat> list(@PathVariable int page, @PathVariable int size) {
		return bookInfoService.listCat(page, size);
	}

	@GetMapping("/list/comment/{bid}")
	@Override
	public QueryResponseResult<CommentInfo> commentListByBookId(@PathVariable Long bid) {
		
		return bookInfoService.getCommentsByBid(bid);
	}

	@GetMapping("/recommend/{username}")
	@Override
	public QueryResponseResult<TbBook> recommendByUsername(@PathVariable String username) {
		return recommendService.genRecommendBook(username);
	}

	@Override
	@GetMapping("/recommend/hot")
	public QueryResponseResult<TbBook> recommendByHot() {
		
		return recommendService.recommendHot();
	}


}
