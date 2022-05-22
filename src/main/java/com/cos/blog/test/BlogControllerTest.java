package com.cos.blog.test;


import org.springframework.web.bind.annotation.*;

// 스프링이 com.cos.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는건 아니고
// 특정 Annotation이 붙여있는 class 파일을 new해서 (IOC) 스프링 컨테이너에 관리해준다
@RestController 
public class BlogControllerTest {
	
	//http://localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello springboot</h1>";
	}
}
