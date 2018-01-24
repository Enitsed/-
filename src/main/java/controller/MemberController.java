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

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String viewSignUp() {
		// 회원가입 페이지로 이동
		return "signUpForm";
	}

	@RequestMapping(value = "/checkId", method = RequestMethod.POST)
	public @ResponseBody boolean checkId(MemDTO userDTO, HttpServletRequest request) {
		// 아이디 중복 확인 메서드
		System.out.println(service.idCheckProcess(userDTO));
		return service.idCheckProcess(userDTO);
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ModelAndView signUp(MemDTO userDTO, HttpServletRequest request) {
		// 회원 가입 실행
		ModelAndView mav = new ModelAndView();
		try {
			// 회원가입 시도
			service.registerProcess(userDTO);
			request.setAttribute("resultSignUp", true);
		} catch (Exception e) {
			// 회원가입 실패시
			request.setAttribute("resultSignUp", false);
		}
		/*
		 * System.out.println("num =" + userDTO.getMem_num() + " id= " +
		 * userDTO.getMem_id() + " pw=" + userDTO.getMem_pw() + " name=" +
		 * userDTO.getMem_name() + " email=" + userDTO.getMem_email() + " address=" +
		 * userDTO.getMem_address() + " sex=" + userDTO.getMem_sex());
		 */

		mav.setViewName("signUpForm");
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(MemDTO userDTO, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (service.idCheckProcess(userDTO)) {
			try {
				MemDTO foundUserDTO = service.loginProcess(userDTO);
				if (foundUserDTO != null) {
					session.setAttribute("userDTO", foundUserDTO);
					session.setAttribute("loginStatus", "로그인에 성공하였습니다.");
				} else {
					session.setAttribute("loginStatus", "비밀번호가 일치하지 않습니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute("loginStatus", "로그인에 실패하였습니다.");
			}
		} else {
			session.setAttribute("loginStatus", "회원이 존재하지 않습니다.");
		}
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}

}
