package com.chuanshuke.framework.domain.cms.ext;

import java.util.List;

import com.chuanshuke.framework.domain.cms.TbBookCat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TbBookCatNode extends TbBookCat {
	
	List<TbBookCat> children;

}
