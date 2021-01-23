package com.cuterwrite.dbfinal.entity;

import java.math.BigDecimal;

import lombok.Data;

/**  
 * @author Pang S.Z.
 * @create 2021-01-23 16:54:42 
 */
@Data
public class DiscountedBook {
	private Long id;
	private Long bookId;
	private BigDecimal price;
	private BigDecimal newPrice;
	private String author;
	private String name;
	private String press;
	private String picture;
}
