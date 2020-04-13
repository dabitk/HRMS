package com.spring.demo.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.demo.domain.User;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.service.UserService;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/list")
	public String list(Model model,
			//@RequestParam(required = false) Map<String, Object> filter,
			@RequestParam(required = false) String selectCol,
			@RequestParam(required = false) String keyword,
			@PageableDefault(sort = { "empno" }, direction = Direction.ASC, size = 10)Pageable pageable) {
		//System.out.println("BoardController list()");
		//boardService.updateBoardName();
		Page<User>users=null;
		if(selectCol != null) {
			if(selectCol.equals("username") && !keyword.isEmpty()) {
				users=userRepository.findAllByUsername(keyword,pageable);
			}else if(selectCol.equals("nameKor") && !keyword.isEmpty()){
				users=userRepository.findAllByNameKor(keyword,pageable);
			}else if(selectCol.equals("email") && !keyword.isEmpty()) {
				System.out.println(keyword);
				users=userRepository.findAllByEmail(keyword,pageable);
			}else {
				users=userRepository.findAll(pageable);
			}
		}else {
			users = userRepository.findAll(pageable);
		}
		model.addAttribute("users", users);
		//Page<User> users = userRepository.findAll(UserSpecs.search(filter),pageable);
		//Page<User> users = userRepository.findAll(pageable);
		//model.addAttribute("users", users);
//		for(User u:users){
//			System.out.println(u.getUsername());
//		}
		//UserSpecs.search(filter)
		return "account/member";
	}
	
	@RequestMapping(value="/view")
	public String view(Model model,
			@RequestParam(required = true) long empno) {
		Optional<User> users = userRepository.findById(empno);
		//model.addAttribute("users", users);
		if(users.isPresent() ) {
            model.addAttribute("users", users.get());
		}
		return "account/selectone";
	}
	
	@RequestMapping(value="/register")
	public String register() {
		return "account/register";
	}
	
	@RequestMapping(value="/insert")
	public String insert(
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String passwd,
			@RequestParam(required = false) String nameKor,
			@RequestParam(required = false) Date birthday,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) boolean sex) {
		//@Transactional 위치는 서비스 아니면 컨트롤러?
		User user = new User();
		user.setUsername(username);
		user.setPasswd(passwd);
		user.setNameKor(nameKor);
		user.setBirthday(birthday);
		user.setEmail(email);
		user.setSex(sex);
		userService.addUser(user);
		System.out.println("다음 유저가 등록되었습니다 : "+ user.getUsername());
		return "redirect:/member/list";
	}
	
	@RequestMapping(value="/delete", method= {RequestMethod.POST})
	public String delete(@RequestParam("deleteUserId") Long[] deleteUserId, ModelMap modelMap) throws Exception{
		for(Long userId:deleteUserId) {
			System.out.println("사용자 삭제 = "+userId);
			userService.deleteUser(userId);
		}
		return "redirect:/member/list";
	}
	

}
