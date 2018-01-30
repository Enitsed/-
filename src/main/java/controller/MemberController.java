package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import api.MovieApi;
import dto.MemDTO;
import service.MemService;
import service.MovieService;

@Controller
public class MemberController {
	MemService service;
	MovieService movieservice;

	public MemberController() {

	}

	public void setMovieservice(MovieService movieservice) {
		this.movieservice = movieservice;
	}

	public void setService(MemService service) {
		this.service = service;
	}

	@RequestMapping(value = "/checkId", method = RequestMethod.POST)
	public @ResponseBody boolean checkId(MemDTO userDTO) {
		// 아이디 중복 확인 메서드
		return service.idCheckProcess(userDTO);
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String viewSignUp() {
		// 회원가입 페이지로 이동
		return "signUpForm";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ModelAndView signUp(MemDTO userDTO) {
		// 회원 가입 실행
		ModelAndView mav = new ModelAndView();
		try {
			// 회원가입 시도
			service.registerProcess(userDTO);
			mav.addObject("resultSignUp", true);
		} catch (Exception e) {
			// 회원가입 실패시
			mav.addObject("resultSignUp", false);
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
		MovieApi api = new MovieApi();
		if (service.idCheckProcess(userDTO)) {
			try {
				MemDTO foundUserDTO = service.loginProcess(userDTO);
				if (foundUserDTO != null) {
					mav.addObject("loginStatus", "로그인에 성공하였습니다.");
					session.setAttribute("userDTO", foundUserDTO);

				} else {
					mav.addObject("loginStatus", "비밀번호가 일치하지 않습니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				mav.addObject("loginStatus", "로그인에 실패하였습니다.");
			}
		} else {
			mav.addObject("loginStatus", "회원이 존재하지 않습니다.");
		}
		api.MovieNewsApi(mav);
		mav.addObject("movie", movieservice.movieInfoProcess(1));
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}

	@RequestMapping("/findId")
	public ModelAndView findIdPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("findIdForm");
		return mav;
	}

	@RequestMapping("/findPw")
	public ModelAndView findPwPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("findPwForm");
		return mav;
	}

	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	public ModelAndView findId(MemDTO userDTO) {
		ModelAndView mav = new ModelAndView();
		MemDTO user = service.findIdProcess(userDTO);
		mav.addObject("user", user);
		if (user == null) {
			mav.addObject("findIdStatus", "일치하는 회원이 없습니다.");
		}
		if (user != null) {
			try {
				mav.addObject("findIdStatus", "회원님의 아이디는 '" + user.getMem_id() + "' 입니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mav.setViewName("findIdForm");
		return mav;
	}

	@RequestMapping(value = "/findPw", method = RequestMethod.POST)
	public ModelAndView findPw(MemDTO userDTO) {
		ModelAndView mav = new ModelAndView();
		MemDTO user = service.findPwProcess(userDTO);
		mav.addObject("user", user);
		if (user != null) {
			try {
				mav.addObject("findPwStatus", "회원님의 비밀번호는 '" + user.getMem_pw() + "' 입니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			mav.addObject("findPwStatus", "일치하는 회원이 없습니다.");
		}
		mav.addObject("user", user);
		mav.setViewName("findPwForm");
		return mav;
	}

	@RequestMapping("/myPage")
	public ModelAndView myPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myPage");
		return mav;
	}

	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
	public ModelAndView update(MemDTO userDTO) {
		ModelAndView mav = new ModelAndView();
		service.updateProcess(userDTO);
		if (userDTO != null) {
			mav.addObject("updateInfoStatus", "회원정보를 수정하였습니다.");
		} else {
			mav.addObject("updateInfoStatus", "회원정보 수정에 실패하였습니다.");
		}
		mav.setViewName("myPage");
		return mav;
	}
	
	@RequestMapping(value = "/memInfo", method = RequestMethod.POST)
	public ModelAndView memInfo(MemDTO userDTO) {
		ModelAndView mav = new ModelAndView();
		List<MemDTO> aList = service.memInfo(userDTO);
		mav.addObject("memList",aList);
		mav.setViewName("memInfoList");
		return mav;
	}
}
