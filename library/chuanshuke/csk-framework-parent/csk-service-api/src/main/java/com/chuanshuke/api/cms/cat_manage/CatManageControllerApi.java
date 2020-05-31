package com.chuanshuke.api.cms.cat_manage;

import com.chuanshuke.framework.domain.cms.TbBookCat;
import com.chuanshuke.framework.domain.cms.ext.TbBookCatNode;
import com.chuanshuke.framework.domain.cms.response.CmsBookCatResult;
import com.chuanshuke.framework.model.response.QueryResponseResult;
import com.chuanshuke.framework.model.response.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "分类管理接口", description = "cms分类管理接口，提供图书分类的增、删、查、改")
public interface CatManageControllerApi {
	//分类查询
	@ApiOperation("分页查询页面列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name="page", value="页码", required = true, paramType = "path", dataType = "int"),
		@ApiImplicitParam(name="size", value="每页记录数", required = true, paramType = "path", dataType = "int")
	})
	public QueryResponseResult<TbBookCat> findList(int page, int size);
	
	//新增分类
	@ApiOperation("新增分类")
	public CmsBookCatResult add(TbBookCat tbBookCat);
	
	//根据分类ID查询分类信息
	@ApiOperation("根据分类ID查询分类信息")
	public TbBookCat findById(Long id);
	
	//修改分类信息
	@ApiOperation("修改分类")
	public CmsBookCatResult edit(Long id, TbBookCat tbBookCat);
	
	//只可以删除叶子节点分类
	@ApiOperation("删除分类")
	public ResponseResult delete(Long id);
	
	//选择分类查询
	@ApiOperation("选择分类查询")
	public TbBookCatNode findTbBookCatList();
}
