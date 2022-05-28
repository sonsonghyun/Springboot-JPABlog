package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// Rest여서 data를 리턴해주는 controller
@RestController
public class DummyController {
	
	@Autowired // 의존성 주입 (DI)
	private UserRepository userRepository;
	
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해준다
	// <form>태그 데이터가 아닌 json데이터로 받아서
	// password,email
	@Transactional // 함수 종료시 자동 commit
	@PutMapping("/dummy/user/{id}") 
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) { //json데이터 요청 > java object가 받아줌
		// 이때 필요한 annotation이 RequestBody
		System.out.println("id:"+id);
		System.out.println("password:"+requestUser.getPassword());
		System.out.println("email:"+requestUser.getEmail());
		
		//람다식 () 안에 함수 들어가는거 
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		
		// 더티체킹 
		return null;
	}
	
	// http://localhost:8000/blog/dummy/user
	// 유저 객체 전부 반환
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건의 데이터를 리턴
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUsers=userRepository.findAll(pageable);
		
		List<User> users =pagingUsers.getContent();
		return pagingUsers;
	}
	
	
	
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
				return new IllegalArgumentException("해당 사용자가 없습니다");
			}
		});
		// 요청은 웹 브라우져 
		// user 객체=자바오브젝트 
		// 변환 > json (Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동 
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에 던져준다
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






