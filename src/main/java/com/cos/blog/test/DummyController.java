package com.cos.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyController {
	
	@Autowired // 의존성 주입 (DI)
	private UserRepository userRepository;
	
	// {id} 주소로 파라미터를 전달 받을 수 있습니다
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) { //return 타입이 optional 임
		// user/4를 찾으면 데이터베이스에서 못 찾아오게 되면 user가 null이 될 것이냐
		// 그러면 return시 null이 리턴이 되면 이게 프로그램에서 문제가 됨
		// Optional로 user 객체를 감싸서 가져오니깐 null인지 아닌지 판단해서 return 하라는 뜻
		
		User user =userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
			}
		});
			
		return user;
	}
	
	//http://localhost:8000/blog/dummy/join(요청)
	// http의 body에 username,password,email데이터를 가지고 요청
	@PostMapping("/dummy/join") 
	public String join(User user) { // key=value 형태의 데이터를 받음 (약속된 데이터)
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.User);
		userRepository.save(user);
		return "회원가입이 완료되었습니다";
	}
}






