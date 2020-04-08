package com.spring.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.spring.demo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>,JpaSpecificationExecutor<User>{
    Page<User> findAllByUsername(String username,Pageable pageable);
    Page<User> findAllByNameKor(String name_kor,Pageable pageable);
    Page<User> findAllByEmail(String email,Pageable pageable);
}
