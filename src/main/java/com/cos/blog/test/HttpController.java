package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data) 해주는게 컨트롤러
@RestController
public class HttpController {

	private static final String TAG="HttpController : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+"getter: "+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter: "+m.getUsername());
		return "lombok test 완료";
	}
	
	//인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다
	//http://localhost:8080/http/get > select
	@GetMapping("/http/get")
	public String getTest(Member m) { //MessageConverter가 함
		
		return "get 요청: "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
		
	}
	
	//http://localhost:8080/http/post > insert
	@PostMapping("/http/post") // application/json
	public String postTest(@RequestBody Member m) { // json에 대한 변환은 스프링부트의 MessageConverter가 함
		return "post 요청: "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//http://localhost:8080/http/put > update
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//http://localhost:8080/http/delete > delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}












