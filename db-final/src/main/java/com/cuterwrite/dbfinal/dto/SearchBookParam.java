package com.cuterwrite.dbfinal.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**  
 * @author Pang S.Z.
 * @create 2021-01-22 17:18:32 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchBookParam {
	private String keyword;
	private String categoryName;
	private Integer pageNum;
	private Integer pageSize;
	private Integer sort;
}
