package com.cuterwrite.dbfinal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.entity.DiscountedBook;
import com.cuterwrite.dbfinal.service.BookDiscountService;


/**  
 * @author Pang S.Z.
 * @create 2021-01-23 17:12:02 
 */
@RequestMapping("/discount")
@RestController
public class DiscountController {
	@Autowired
	private BookDiscountService service;
	@GetMapping("/item")
	public ResponseResult list() {
		List<DiscountedBook>list=service.selectAll();
		Map<String, Object>map=new HashMap<>();
		map.put("list",list);
		return ResponseResult.ok().data(map);
	}
}
