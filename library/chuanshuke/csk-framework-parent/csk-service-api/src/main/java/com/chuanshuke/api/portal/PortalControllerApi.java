package com.chuanshuke.api.portal;

import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.domain.cms.TbBookCat;
import com.chuanshuke.framework.domain.cms.request.QueryBookRequest;
import com.chuanshuke.framework.domain.portal.CommentInfo;
import com.chuanshuke.framework.domain.portal.response.PortalCommentResult;
import com.chuanshuke.framework.domain.portal.response.PortalResult;
import com.chuanshuke.framework.model.response.QueryResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "首页门户API")
public interface PortalControllerApi {
	
	@ApiOperation(value = "根据条件分页加载图书信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
		@ApiImplicitParam(name = "size", value = "页记录数", required = true, paramType = "path", dataType = "int"),
		@ApiImplicitParam(name = "queryBookRequest", value = "查询条件")
	})
	public QueryResponseResult<TbBook> list(int page, int size, QueryBookRequest queryBookRequest);
	
	@ApiOperation(value = "加载分类列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
		@ApiImplicitParam(name = "size", value = "页记录数", required = true, paramType = "path", dataType = "int"),
	})
	public QueryResponseResult<TbBookCat> list(int page, int size);
	
	@ApiOperation(value = "获取图书详细信息（包含内容简介）")
	@ApiImplicitParam(name = "id", value = "图书ID", required = true, paramType = "path", dataType = "long")
	public PortalResult getBookInfo(Long id);
	
	@ApiOperation(value = "图书详情页，评论列表")
	@ApiImplicitParam(name = "bid", value = "图书ID", required = true, paramType = "path", dataType = "long")
	public QueryResponseResult<CommentInfo> commentListByBookId(Long bid);
	
	@ApiOperation(value = "个性化推荐图书")
	@ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "path", dataType = "String")
	public QueryResponseResult<TbBook> recommendByUsername(String username);
	
	@ApiOperation(value = "未登录热门推荐")
	public QueryResponseResult<TbBook> recommendByHot();
}
