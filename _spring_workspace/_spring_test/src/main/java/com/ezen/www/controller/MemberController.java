package com.ezen.www.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
	@Inject
	private MemberService msv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		log.info("r>>> mvo >>> {} ", mvo);
		int isOk = msv.signUp(mvo);
		log.info(">>> signUp ?" + ((isOk>0) ? "Ok" :  "Fail"));
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
	@PostMapping("/login")
	public String login(MemberVO mvo, HttpServletRequest request, Model m) {
		log.info(">>>mvo >> {}", mvo);
		
		//mvo객체를 db에 일치하는지 체크 
		MemberVO loginMvo = msv.isUser(mvo);
		if(loginMvo != null) {
			HttpSession ses = request.getSession();
			ses.setAttribute("ses", loginMvo);
			ses.setMaxInactiveInterval(60*10);
		}else {
			m.addAttribute("msg_login","1");
		}
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model m) {
		//세션 객체 삭제 => 세션끊기
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("ses");
		msv.lastLogin(mvo.getId());
		request.getSession().removeAttribute("ses");
		request.getSession().invalidate();
		m.addAttribute("msg_logout","1");
		return "index";
	}
	
	@GetMapping("/modify")
	public void modify(Model m, @RequestParam("id") String id) {
			log.info(">>>>> id" + id);
			MemberVO mvo = msv.getUser(id);
			m.addAttribute("mvo", mvo);
			log.info(">>>>mvo >>>> {} ", mvo);
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, RedirectAttributes re) {
		log.info(">>>>mvo>>> {}", mvo);
		int isOk = msv.updateUser(mvo);
		log.info(">>>>update check>>> {}", isOk > 0 ? "success" : "fail");
		
		re.addFlashAttribute("msg_modify", "1");
		
		return "redirect:/member/logout";
		
	}
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, RedirectAttributes re) {
		MemberVO sesMvo = (MemberVO)request.getSession().getAttribute("ses");
		int isOk = msv.deleteUser(sesMvo.getId());
		re.addFlashAttribute("msg_delete", isOk);
		return "redirect:/member/logout";
	}
	
	
	
}
