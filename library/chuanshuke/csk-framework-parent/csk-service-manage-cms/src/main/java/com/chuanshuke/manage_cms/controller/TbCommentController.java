package com.chuanshuke.manage_cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuanshuke.api.cms.comment_manage.CommentManageControllerApi;
import com.chuanshuke.framework.domain.cms.TbComment;
import com.chuanshuke.framework.domain.cms.response.CmsCommentResult;
import com.chuanshuke.framework.domain.portal.CommentInfo;
import com.chuanshuke.framework.model.response.QueryResponseResult;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.manage_cms.service.TbCommentService;

@RestController
@RequestMapping("/cms/comment")
public class TbCommentController implements CommentManageControllerApi {

	@Autowired
	private TbCommentService tbCommentService;

	@PostMapping("/add/{uid}/{bid}")
	@Override
	public CmsCommentResult add(@PathVariable Long uid, @PathVariable Long bid, @RequestBody TbComment tbComment) {
		return tbCommentService.add(uid, bid, tbComment);
	}

	@GetMapping("/get/{id}")
	@Override
	public TbComment findByBid(@PathVariable Long id) {
		return tbCommentService.getCommentById(id);
	}

	@PostMapping("/delete/{id}")
	@Override
	public ResponseResult delete(@PathVariable Long id) {
		return tbCommentService.delete(id);
	}

	@GetMapping("/list/{page}/{size}")
	@Override
	public QueryResponseResult<TbComment> list(@PathVariable("page")int page,
			@PathVariable("size")int size) {
		return tbCommentService.list(page, size);
	}

	@GetMapping("/list/{bid}")
	@Override
	public QueryResponseResult<CommentInfo> commentListInBookDetail(@PathVariable Long bid) {
		return tbCommentService.listByBid(bid);
	}

}
