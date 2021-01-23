package com.cuterwrite.dbfinal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.entity.CartItem;
import com.cuterwrite.dbfinal.service.CartService;
import com.cuterwrite.dbfinal.util.Page;

import cn.hutool.core.bean.BeanUtil;

/**  
 * 购物车页
 * @author Pang S.Z.
 * @create 2021-01-23 13:33:42 
 */
@RequestMapping("/cart")
@RestController
public class CartController {
	@Autowired
	private CartService cartService;
	
	/**
	 * 查询购物车列表
	 */
	@GetMapping("/item")
	public ResponseResult findItemList(@RequestParam Integer pageNum,
									   @RequestParam Integer pageSize) {
		Page<CartItem>cartList=cartService.list(pageNum, pageSize);
		Map<String,Object>map=BeanUtil.beanToMap(cartList);
		return ResponseResult.ok().data(map);
	}
	/**
	 * 根据id查询购物车
	 */
	@GetMapping("/item/query/{id}")
	public ResponseResult findItemOne(@PathVariable("id") Long id) {
		CartItem cartItem=cartService.select(id);
		Map<String, Object>map=BeanUtil.beanToMap(cartItem);
		return ResponseResult.ok().data(map);
	}
	/**
	 * 删除一项购物车
	 */
	@DeleteMapping("/item/{id}")
	public ResponseResult deleteCart(@PathVariable("id") Long id) {
		cartService.delete(id);
		return ResponseResult.ok();
	}
	/**
	 * 更新购物车数量
	 */
	@PostMapping("/item/{id}")
	public ResponseResult updateCart(@PathVariable("id") Long id, @RequestBody CartItem cartItem) {
		cartService.update(id, cartItem);
		return ResponseResult.ok();
	}
}
