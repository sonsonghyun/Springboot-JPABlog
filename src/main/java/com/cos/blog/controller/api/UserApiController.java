package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) { //username,password,email
		System.out.println("UserApiController : save호출됨");
		// 실제로 DB에 insert를 하고 아래에서 리턴하면 됨 
		user.setRole(RoleType.User);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 json으로 변환해서 리턴
	}
	
	
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
		System.out.println("UserApiController : login호출됨");
		User principal=userService.로그인(user);  //principal (접근 주체)
		
		if(principal != null) {
			session.setAttribute("principal",principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
}










