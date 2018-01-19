package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import api.MovieApi;
import dto.MemDTO;
import dto.MovieDTO;
import service.MemService;
import service.MovieService;

// http://localhost:8090/finalproject/main
@Controller
public class HelloController {

	public HelloController() {

	}

	MemService service;
	MovieService movieservice;

	public void setMovieservice(MovieService movieservice) {
		this.movieservice = movieservice;
	}

	public void setService(MemService service) {
		this.service = service;
	}
	/*
	 * @RequestMapping("/main") public String mainPage() { return "index"; }
	 */

	@RequestMapping("/main")
	public ModelAndView mainPage() {
		ModelAndView mav = new ModelAndView();
		MovieApi api = new MovieApi();
		api.MovieNewsApi(mav);
		mav.addObject("movie", movieservice.movieInfoProcess(1));
		mav.setViewName("index");
		return mav;
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
		session.setAttribute("kid", kid);
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

		boolean result = service.findProcess(dto);
		if (result) {
			System.out.println(dto.getMem_id());
			session.setAttribute("kid", dto.getMem_id());
		}
		return "redirect:/main";
	}
	
	@RequestMapping("/addMovie.do")
	public @ResponseBody List<MovieDTO> addMovie(int page) {
		System.out.println("asdasdasdasd");
		System.out.println(page);
		List<MovieDTO> list = movieservice.movieInfoProcess(page);
		System.out.println(list.get(1).getMovie_num());
		return list;
	}
}
