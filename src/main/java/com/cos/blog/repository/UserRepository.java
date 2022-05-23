package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO
// CRUD가 이 함수로 다 가능
// 자동으로 bean 등록이 된다
// @Repository > 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{ //User 테이블의 Pk는 integer이다

}