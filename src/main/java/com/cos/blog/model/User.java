package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더패턴
//ORM > java(or 다른언어) object를 table로 mapping 해주는 것
@Entity // User 클래스가 각각 모델을 읽어서 자동으로 MySQL에 테이블이 생성됨
//@DynamicInsert  // insert시 null인 필드를 제외시켜준다
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 넘버링 전략 : 해당 프로젝트에서 연결된 DB의 넘버링 전략을 따라감 
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length=30)
	private String username; // 아이디
	
	@Column(nullable = false, length=100) // 123456>> 해쉬(비밀번호 암호화를 위해 넉넉하게)
	private String password; // 
	
	@Column(nullable = false, length=50) 
	private String email;
	
	//@ColumnDefault("user") 
	//DB는 RoleType이 없어서 아래 annotation 사용
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다 >> 데이터의 도메인을 만들어 줄 수 있음 ex) admin,user,manager
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
}





