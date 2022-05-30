package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 bin에 등록을 해준다 >> IOC를 해준다 >> 메모리에 대신 띄워준다

//서비스는 1.트랜잭션 관리 2.서비스 의미 때문에 
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	

	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); // 1234원문
		String encPassword = encoder.encode(rawPassword); //해쉬화 된 것
		user.setPassword(encPassword);
		user.setRole(RoleType.User);
		userRepository.save(user);
	}
	
	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화를 시키고, 영속화 된 User 오브젝트를 수정하면 된다
		// SELECT를 해서 USER오브젝트를 DB로 부터 가져오는 이유는 영속화 하려고
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줘서 
		User persistance=userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
			});
		String rawPassword=user.getPassword();
		String encPassword=encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		

		
		// 회원수정 함수 종료시=서비스가 종료=트랜잭션 종료=commit이 자동으로 된다
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹 되어 update문을 날려줌
		}
}










