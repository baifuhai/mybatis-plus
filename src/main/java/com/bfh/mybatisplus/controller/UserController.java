package com.bfh.mybatisplus.controller;

import com.bfh.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("test")
	public String test() {
		return "ok";
	}

}
