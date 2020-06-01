package com.chuanshuke.protal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.chuanshuke.framework.client.CskServiceList;
import com.chuanshuke.framework.domain.cms.TbBook;
import com.chuanshuke.framework.domain.cms.TbBookCat;
import com.chuanshuke.framework.domain.cms.TbComment;
import com.chuanshuke.framework.domain.cms.request.QueryBookRequest;
import com.chuanshuke.framework.domain.portal.CommentInfo;
import com.chuanshuke.framework.model.response.QueryResponseResult;

@FeignClient(value = CskServiceList.CSK_SERVICE_MANAGE_CMS)
public interface CmsClient {
	
	@GetMapping("/cms/book/list/{page}/{size}")
	public QueryResponseResult<TbBook> bookList(@PathVariable int page, @PathVariable int size, QueryBookRequest queryBookRequest);

	@GetMapping("/cms/cat/list/{page}/{size}")
	public QueryResponseResult<TbBookCat> catList(@PathVariable@RequestParam(defaultValue = "0") int page, @PathVariable@RequestParam(defaultValue = "10")int size);
	
	@GetMapping("/cms/comment/list/{bid}")
	public QueryResponseResult<CommentInfo> commentListInBookDetail(@PathVariable Long bid);
}
