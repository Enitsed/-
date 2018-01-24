package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.MemDTO;
import service.MemService;

@Controller
public class MemberController {
	MemService service;

	public MemberController() {

	}

	public void setService(MemService service) {
		this.service = service;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String viewSignUp() {
		// 회원가입 페이지로 이동
		return "signUpForm";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signUp(MemDTO dto, HttpServletRequest req) {
		// 회원 가입 실행 페이지
		ModelAndView mav = new ModelAndView();
		HttpSession session = req.getSession();
		service.registerProcess(dto);
		System.out.println("num =" + dto.getMem_num() + " id= " + dto.getMem_id() + " pw=" + dto.getMem_pw() + " name="
				+ dto.getMem_name() + " email=" + dto.getMem_email() + " address=" + dto.getMem_address() + " sex="
				+ dto.getMem_sex());
		mav.setViewName("redirect:/main");
		return mav;
	}

	@RequestMapping(value = "/chkId", method = RequestMethod.POST)
	public @ResponseBody int chkId(String mem_id, HttpServletRequest request) {
		boolean rs = service.chkIdProcess(mem_id);
		if (rs)
			return 1;
		else
			return 2;
	}

	@RequestMapping("/login")
	public ModelAndView login(MemDTO dto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		boolean rs = service.findProcess(dto);
		if (rs) {
			String name = service.login(dto);
			session.setAttribute("kid", name);
			mav.setViewName("redirect:/main");
		}
		return mav;
	}

	@RequestMapping("/loginsuccess")
	public ModelAndView loginsuccess(String kid, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.setAttribute("kid", kid);
		mav.setViewName("redirect:/main");
		return mav;
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}

	@RequestMapping("/check")
	public String logincheck(MemDTO dto, HttpSession session) {

		boolean result = service.findProcess(dto);
		if (result) {
			// System.out.println(dto.getMem_id());
			session.setAttribute("kid", dto.getMem_id());
		}
		return "redirect:/main";
	}

}
