package com.chuanshuke.framework.domain.portal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CommentInfo {
	
	private String nickName;
	private int score;
	private String content;
	

}
