package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter,Setter 둘 다 생성
@NoArgsConstructor // 빈 생성자
public class Member {
	private int id;  //final을 통해서 불변성 유지하려고
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
