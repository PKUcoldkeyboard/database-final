package com.cuterwrite.dbfinal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuterwrite.dbfinal.common.ResponseResult;

/**  
 * @author Pang S.Z.
 * @create 2020-09-30 21:44:30 
 */
@RestController
public class TestController {
	
	@GetMapping("/test")
	public ResponseResult test() {
		Map<String, Object>map=new HashMap<>();
		map.put("text", "Hello programmer");
		return ResponseResult.ok().data(map);
	}
}
