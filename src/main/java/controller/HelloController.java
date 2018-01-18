package controller;




import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.MemDAO;
import dto.MemDTO;
import service.MemService;

// http://localhost:8090/finalproject/main
@Controller
public class HelloController {
	public HelloController() {
		
	}
	MemService service;
	MemDAO dao;
	public void setDao(MemDAO dao) {
		this.dao = dao;
	}
	public void setService(MemService service) {
		this.service = service;
	}
	@RequestMapping("/main")
	public String mainPage() {
		return "index";
	}
	
	
	@RequestMapping("/signup")
	public String signUp() {
		return "signUpForm";
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		return "loginForm";
	}
	
	@RequestMapping("/loginsuccess")
	public ModelAndView loginsuccess(String kid, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		System.out.println(kid);
		session.setAttribute("kid",kid);
		mav.setViewName("redirect:/main");
		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("kid");
		return "redirect:/main";
	}
	
	@RequestMapping("/check")
	public String logincheck(MemDTO dto, HttpSession session) {
		
		System.out.println(dto.getMem_id());
		System.out.println(dto.getMem_pw());
		boolean result = service.findProcess(dto);
		if(result) {
			System.out.println(dto.getMem_id());
			session.setAttribute("kid",dto.getMem_id());
		}
		return "redirect:/main";
	}
}
