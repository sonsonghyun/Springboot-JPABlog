package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 bin에 등록을 해준다 >> IOC를 해준다 >> 메모리에 대신 띄워준다

//서비스는 1.트랜잭션 관리 2.서비스 의미 때문에 
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);
	}
	
	@Transactional(readOnly=true) // select 할 때 트랜잭션 시작, 서비스 종료시 트랜잭션이 종료 (정합성 유지 가능)
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
	}
}




