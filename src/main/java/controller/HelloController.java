package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;
import api.MovieApi;
import dto.CommentDTO;
import dto.MemDTO;
import dto.MovieDTO;
import service.MemService;
import service.MovieService;

// http://localhost:8090/finalproject/main
@Controller
public class HelloController {
	MemService service;
	MovieService movieservice;

	public HelloController() {

	}

	public void setMovieservice(MovieService movieservice) {
		this.movieservice = movieservice;
	}

	public void setService(MemService service) {
		this.service = service;
	}

	@RequestMapping("/main")
	public ModelAndView mainPage() {
		ModelAndView mav = new ModelAndView();
		MovieApi api = new MovieApi();
		api.MovieNewsApi(mav);
		mav.addObject("movie", movieservice.movieInfoProcess(1));
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/free")
	public String board() {
		return "freeboard";
	}

	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "board_write";
	}

	@RequestMapping("/boardDetail")
	public String boardDetail() {
		return "board_detail";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signUp(MemDTO dto) {
		ModelAndView mav = new ModelAndView();
		service.registerProcess(dto);
		System.out.println("num =" + dto.getMem_num() + " id= " + dto.getMem_id() + " pw=" + dto.getMem_pw() + " name="
				+ dto.getMem_name() + " email=" + dto.getMem_email() + " address=" + dto.getMem_address() + " sex="
				+ dto.getMem_sex());
		mav.setViewName("redirect:/main");
		return mav;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView viewSignUp(MemDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("signUpForm");
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
	public String loginPage() {
		return "loginForm";
	}

	@RequestMapping("/loginsuccess")
	public ModelAndView loginsuccess(String kid, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// System.out.println(kid);
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

	@RequestMapping("/addMovie.do")
	public @ResponseBody List<MovieDTO> addMovie(int page) {
		// System.out.println("asdasdasdasd");
		// System.out.println(page);
		List<MovieDTO> list = movieservice.movieInfoProcess(page);
		// System.out.println(list.get(1).getMovie_num());
		return list;
	}

	/*
	 * @RequestMapping("/info") public ModelAndView movieDetailInfo(int movie_num) {
	 * ModelAndView mav = new ModelAndView();
	 * mav.addObject("info",movieservice.moviedetailProcess(movie_num));
	 * mav.addObject("comment",movieservice.commentListProcess(movie_num));
	 * //mav.setViewName("movieInfo"); return mav; }
	 */
	// http://localhost:8090/finalproject/main

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> movieDetailInfo(int movie_num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("info", movieservice.moviedetailProcess(movie_num));
		map.put("comment", movieservice.commentListProcess(movie_num));
		List<CommentDTO> aList = movieservice.commentListProcess(movie_num);
		System.out.println(aList.size());
		return map;

	}
}
