package com.cuterwrite.dbfinal.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author Pang S.Z.
 * @create 2021-01-23 01:43:52
 */
@Data
public class CartParam {
    private String username;
    private Long bookId;
    private BigDecimal price;
}
