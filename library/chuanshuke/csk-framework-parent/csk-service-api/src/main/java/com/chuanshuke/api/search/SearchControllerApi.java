package com.chuanshuke.api.search;

import com.chuanshuke.framework.domain.search.SearchResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "solr站内搜索api")
public interface SearchControllerApi {
	
	@ApiOperation("搜索图书")
	@ApiImplicitParam(name = "keywords", value = "关键字", dataType = "String", paramType = "query")
	public SearchResult search(String keywords);
}
