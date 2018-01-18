package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dto.MemDTO;
import service.MemService;

// http://localhost:8090/finalproject/main
@Controller
public class HelloController {
	private MemService service;
	
	public HelloController() {
		
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
	
	@RequestMapping("/free")
	public String board() {
		return "freeboard";
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public ModelAndView join(MemDTO dto) {
		ModelAndView mav = new ModelAndView();
		service.registerProcess(dto);
		System.out.println("num ="+dto.getMem_num()+ " id= "+dto.getMem_id()+" pw="+dto.getMem_pw()+" name="+dto.getMem_name()+" email="+dto.getMem_email()+" address="+dto.getMem_address()+" sex="+dto.getMem_sex());
		mav.setViewName("redirect:/main");
		return mav;
	}
}
