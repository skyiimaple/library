package com.chuanshuke.api.cms.comment_manage;

import com.chuanshuke.framework.domain.cms.TbComment;
import com.chuanshuke.framework.domain.cms.response.CmsCommentResult;
import com.chuanshuke.framework.domain.portal.CommentInfo;
import com.chuanshuke.framework.model.response.QueryResponseResult;
import com.chuanshuke.framework.model.response.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "图书评论接口", description = "cms图书评论管理接口，提供图书评论的增、删、查")
public interface CommentManageControllerApi {
	
	//评论列表->cms
	@ApiOperation("评论列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "页码", paramType = "path", dataType = "int"),
		@ApiImplicitParam(name = "size", value = "数量", paramType = "path", dataType = "int")
	})
	public QueryResponseResult<TbComment> list(int page, int size);
	
	//新增分类
	@ApiOperation("新增图书评论")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "uid", value = "用户ID", paramType = "path", dataType = "Long"),
		@ApiImplicitParam(name = "bid", value = "图书ID", paramType = "path", dataType = "Long"),
	})
	public CmsCommentResult add(Long uid, Long bid, TbComment tbComment);
	
	@ApiOperation("查询评论信息")
	//根据分类ID查询分类信息
	public TbComment findByBid(Long id);
	
	//删除评论
	@ApiOperation("删除图书评论")
	public ResponseResult delete(Long id);
	
	@ApiOperation("详情页加载评论")
	@ApiImplicitParam(name = "bid", value = "图书ID", paramType = "path", dataType = "Long")
	public QueryResponseResult<CommentInfo> commentListInBookDetail(Long bid);
	
	
	
		
}
