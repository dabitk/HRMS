package com.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.demo.repository.UserRepository;

@Controller
@RequestMapping(value="/HR")
public class HRController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value="/list")
	public String list(Model model,@PageableDefault(sort = { "empno" }, direction = Direction.ASC, size = 10)Pageable pageable) {
		//System.out.println("BoardController list()");
		//boardService.updateBoardName();
		model.addAttribute("users", userRepository.findAll(pageable));
		//UserSpecs.search(filter)
		return "HR/HR-basic";
	}
	
	@RequestMapping(value="/view")
	public String view() {
		return "HR/HR-detail";
	}
}
