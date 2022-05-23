package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable=false)
	private String title;
	
	@Lob // 대용량 데이터 시 사용
	private String content; // 섬머노트 라이브러리 사용 <html> 태그가 섞여서 디자인이 됨
	
	@ColumnDefault("0")
	private int count; // 조회수
	
	@ManyToOne(fetch=FetchType.EAGER) // Many = Board, User = One >> 연관관계
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없다 >> fk 사용
							  // 객체지향 프로젝트 (자바)는 오브젝트를 저장할 수 있다
	
	@OneToMany(mappedBy="board",fetch=FetchType.EAGER) //mappedBy가 적혀 있으면 연관관계의 주인이 아니다 (즉 fk가 아님) db에 컬럼을 만들지마세요
	// join column은 필요가 없음
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}







