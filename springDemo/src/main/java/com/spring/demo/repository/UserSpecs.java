package com.spring.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.spring.demo.domain.User;

public class UserSpecs {
	public static Specification<User> search(Map<String,Object> filter){
		return (root,query,builder)->{
			//검색하고자 하는 조건들을 나열한다
			List<Predicate> predicates = new ArrayList();
			filter.forEach((key,value) ->{
				switch(key) {
					case "username":
						predicates.add(builder.equal(root.get("username").as(String.class), value));
						break;
					case "name_kor":
						predicates.add(builder.equal(root.get("name_kor").as(String.class), value));
						break;
					case "email":
						predicates.add(builder.equal(root.get("email").as(String.class), value));
						break;
				}
			});
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
