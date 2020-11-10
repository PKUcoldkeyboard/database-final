package com.cuterwrite.dbfinal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**  
 * @author Pang S.Z.
 * @create 2020-09-30 21:44:30 
 */
@RestController
public class TestController {
	
	@GetMapping("/test")
	public String test() {
		return "Hello JenkinsMan!";
	}
}
