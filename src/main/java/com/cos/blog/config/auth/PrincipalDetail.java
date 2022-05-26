package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다 PrincipalDetail이 저장된다
public class PrincipalDetail implements UserDetails{
	private User user; // 컴포지션 >> 객체를 품고 있는 것 



	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴한다 (true : 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있는지 아닌지를 리턴한다 (true 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호가 만료되지 않았는지를 리턴한다 (true 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	// 계정 활성화가 되어있는지 아닌지를 리턴한다
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정의 권한을 리턴한다 >> 어떤 권한을 가졌는지
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(new GrantedAuthority() {
			// 스픠링에서 ROLE을 받을 때 "ROLE_" 를 꼭 넣어줘야 한다
			@Override
			public String getAuthority() {
				return "ROLE_"+user.getRole(); //ROLE_USER
			}
		});
		
		return collectors;
	}
	
}





