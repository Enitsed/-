package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.MemDTO;
import service.MemServiceImp;

// http://localhost:8090/finalproject/main
@Controller
public class HelloController {
	private MemServiceImp service;
	
	public HelloController() {
		
	}
	
	public void setService(MemServiceImp service) {
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
	
	@RequestMapping("/join")
	public ModelAndView join(MemDTO dto) {
		ModelAndView mav = new ModelAndView();
		service.register(dto);
		mav.setViewName("main");
		return mav;
	}
}
