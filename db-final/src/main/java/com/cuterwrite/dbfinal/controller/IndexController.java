package com.cuterwrite.dbfinal.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.dao.UserDAO;
import com.cuterwrite.dbfinal.dto.CartParam;
import com.cuterwrite.dbfinal.dto.SearchBookParam;
import com.cuterwrite.dbfinal.entity.CartItem;
import com.cuterwrite.dbfinal.entity.EsBook;
import com.cuterwrite.dbfinal.entity.User;
import com.cuterwrite.dbfinal.service.BookSearchService;
import com.cuterwrite.dbfinal.service.CartService;

import cn.hutool.core.bean.BeanUtil;

/**  
 * 首页控制器
 * @author Pang S.Z.
 * @create 2021-01-22 17:08:46 
 */
@RequestMapping("/index")
@RestController
public class IndexController {
	@Autowired
	BookSearchService searchService;
	@Autowired
	CartService cartService;
	@Autowired
	UserDAO userDao;
	@PostMapping("/deleteAll")
	public ResponseResult deleteAll() {
		searchService.deleteAll();
		return ResponseResult.ok();
	}
	@PostMapping("/importAll")
	public ResponseResult importAll() {
		searchService.importAll();
		return ResponseResult.ok();
	}
	@PostMapping("/list")
	public ResponseResult getList(@RequestBody SearchBookParam param) {
		String keyword=param.getKeyword();
		String categoryName=param.getCategoryName();
		Integer pageNum=param.getPageNum();
		Integer pageSize=param.getPageSize();
		Integer sort=param.getSort();
		Page<EsBook>books=searchService.search(keyword, categoryName, pageNum, pageSize, sort);
		Map<String,Object> map=BeanUtil.beanToMap(books);
		map.put("total", books.getTotalElements());
		return ResponseResult.ok().data(map);
	}
	@PostMapping("/cart")
	public ResponseResult addCart(@RequestBody CartParam param) {
		CartItem item=new CartItem();
		String username=param.getUsername();
		User user = userDao.findByUsername(username);
		item.setBookId(param.getBookId());
		item.setUserId(user.getId().longValue());
		item.setPrice(param.getPrice());
		item.setQuantity(1);
		item.setCreateTime(new Date());
		item.setModifyTime(new Date());
		cartService.insert(item);
		return ResponseResult.ok();
	}
}
