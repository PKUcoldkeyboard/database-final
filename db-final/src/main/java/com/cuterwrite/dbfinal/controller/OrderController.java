package com.cuterwrite.dbfinal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.entity.Order;
import com.cuterwrite.dbfinal.service.OrderService;

import cn.hutool.core.bean.BeanUtil;

/**  
 * @author Pang S.Z.
 * @create 2021-01-23 19:52:52 
 */
@RequestMapping("/order")
@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@GetMapping("/item")
	public ResponseResult list() {
		List<Order>list=orderService.list();
		Map<String, Object>map=new HashMap<>();
		map.put("list", list);
		return ResponseResult.ok().data(map);
	}
	@GetMapping("/item/query/{id}")
	public ResponseResult findOne(@PathVariable("id") Long id) {
		return ResponseResult.ok().data(BeanUtil.beanToMap(orderService.select(id)));
	}
	@DeleteMapping("/item/{id}")
	public ResponseResult deleteOne(@PathVariable("id") Long id) {
		orderService.delete(id);
		return ResponseResult.ok();
	}
	@PostMapping("/item")
	public ResponseResult addOne(@RequestBody Order order) {
		orderService.insert(order);
		return ResponseResult.ok();
	}
	@PostMapping("/item/{id}")
	public ResponseResult updateOne(@PathVariable("id") Long id,@RequestBody Order order) {
		orderService.update(id, order);
		return ResponseResult.ok();
	}
	@GetMapping("/item/sn/{orderId}")
	public ResponseResult findBySn(@PathVariable("orderId") String orderId) {
		//修改代码
		return ResponseResult.ok().data(BeanUtil.beanToMap(orderService.selectBySn(orderId)));
	}
}
