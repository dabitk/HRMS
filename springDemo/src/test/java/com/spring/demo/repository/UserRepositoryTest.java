package com.spring.demo.repository;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.demo.domain.User;

@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void create() {
		for(int i=100; i<173;i++) {
			User user = new User();
			//user.setEmpno();
			user.setUsername("kimchi"+i);
			user.setNameKor("kim"+i);
			user.setTel("010-4321-1234");
			user.setBirthday(Date.valueOf("1993-11-24"));
			user.setEmail("dabitk"+i+"@naver.com");
			user.setPasswd("1234");
			user.setCreateDate(Date.valueOf(LocalDate.now()));
			if(i<50)user.setSex(true);
			else user.setSex(false);
			
			User newUser = userRepository.save(user);
			System.out.println(newUser);			
		}
	}

}
