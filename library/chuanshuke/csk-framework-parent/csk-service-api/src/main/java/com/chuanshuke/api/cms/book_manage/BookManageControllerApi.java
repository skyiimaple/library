package com.chuanshuke.api.cms.book_manage;

import org.springframework.web.multipart.MultipartFile;

import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.domain.cms.TbBookDesc;
import com.chuanshuke.framework.domain.cms.request.QueryBookRequest;
import com.chuanshuke.framework.domain.cms.response.CmsBookResult;
import com.chuanshuke.framework.domain.cms.response.CmsImageResult;
import com.chuanshuke.framework.model.response.QueryResponseResult;
import com.chuanshuke.framework.model.response.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "图书管理接口", description = "cms图书管理接口，提供图书的增、删、查、改")
public interface BookManageControllerApi {
	//分类查询
	@ApiOperation("分页查询页面列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name="page", value="页码", required = true, paramType = "path", dataType = "int"),
		@ApiImplicitParam(name="size", value="每页记录数", required = true, paramType = "path", dataType = "int"),
	})
	public QueryResponseResult<TbBook> findList(int page, int size, QueryBookRequest queryBookRequest);
	
	//新增分类
	@ApiOperation("新增图书")
	public CmsBookResult add(TbBook tbBook);
	
	//根据分类ID查询分类信息
	@ApiOperation("根据ID查询图书信息")
	public TbBook findById(Long id);
	
	//修改分类信息
	@ApiOperation("修改图书")
	public CmsBookResult edit(Long id, TbBook tbBook);
	
	//删除图书
	@ApiOperation("删除图书")
	public ResponseResult delete(Long id);
	
	//根据图书id获得描述信息
	@ApiOperation("获取图书介绍")
	public TbBookDesc findDescByBookId(Long id);
	
	//添加介绍：目录、作者简介、内容简介
	@ApiOperation("添加详细内容")
	public ResponseResult addDesc(Long id, TbBookDesc bookDesc);
	
	//修改描述信息
	@ApiOperation("修改描述信息")
	public ResponseResult editBookDesc(Long id, TbBookDesc tbBookDesc);
	
	//上传封面图片
	@ApiOperation("上传图片封面")
	public CmsImageResult uploadPic(MultipartFile multipartFile);
	
}
