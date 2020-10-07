package com.cuterwrite.dbfinal.util;

import java.util.List;

import com.github.pagehelper.PageInfo;

import lombok.Data;

/**
 * 分页工具类
 * @author Pang S.Z.
 * @create 2020-10-04 00:12:46
 */
@Data
public class Page<T> {
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalPages;
	private Long total;
	private List<T>list;
	
	/*
	 * 将pagehelper分页后的list转为分页信息
	 */
    public static <T> Page<T> restPage(List<T> list) {
        Page<T> result = new Page<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalPages(pageInfo.getPages());
        result.setPageNumber(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }
    /**
     * 将SpringData分页后的list转为分页信息
     */
    public static <T> Page<T> restPage(org.springframework.data.domain.Page<T> pageInfo) {
        Page<T> result = new Page<T>();
        result.setTotalPages(pageInfo.getTotalPages());
        result.setPageNumber(pageInfo.getNumber());
        result.setPageSize(pageInfo.getSize());
        result.setTotal(pageInfo.getTotalElements());
        result.setList(pageInfo.getContent());
        return result;
    }
}
