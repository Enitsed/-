package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import api.MovieApi;
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
	public ModelAndView mainPage() {
		ModelAndView mav = new ModelAndView();
		MovieApi api = new MovieApi();
		api.MovieNewsApi(mav);
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/signup")
	public String signUp() {
		return "signUpForm";
	}
	
<<<<<<< HEAD
	@RequestMapping("/join")
=======
	@RequestMapping("/free")
	public String board() {
		return "freeboard";
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
>>>>>>> 323ca1d6316bda744762d1df9e6c97dc0fec575f
	public ModelAndView join(MemDTO dto) {
		ModelAndView mav = new ModelAndView();
		service.registerProcess(dto);
		mav.setViewName("main");
		return mav;
	}
}
