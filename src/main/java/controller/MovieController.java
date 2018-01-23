package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.MovieDTO;
import service.MovieService;

@Controller
public class MovieController {
	MovieService movieservice;

	public void setMovieservice(MovieService movieservice) {
		this.movieservice = movieservice;
	}

	@RequestMapping("/movieInfoList")
	public ModelAndView movieInfoList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("movie", movieservice.movieInfoProcess(1));
		mav.setViewName("movieInfoList");
		return mav;
	}
	
	@RequestMapping("/addMovie.do")
	public @ResponseBody List<MovieDTO> addMovie(int page) {
		List<MovieDTO> list = movieservice.movieInfoProcess(page);
		return list;
	}
}
