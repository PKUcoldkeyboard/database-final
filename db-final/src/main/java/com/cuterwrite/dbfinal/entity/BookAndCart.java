package com.cuterwrite.dbfinal.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**  
 * 图书与购物车连接查询
 * @author Pang S.Z.
 * @create 2021-01-23 15:20:01 
 */
@Data
public class BookAndCart {
	private Long id;
	private Long userId;
	private Long bookId;
	private String name;
	private String picture;
	private BigDecimal price;
	private Integer quantity;
	private Date createTime;
}
