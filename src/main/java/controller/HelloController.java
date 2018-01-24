package controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import api.MovieApi;
import dto.CommentDTO;
import service.MovieService;

// http://localhost:8090/finalproject/main
@Controller
public class HelloController {
	MovieService movieservice;

	public HelloController() {

	}

	public void setMovieservice(MovieService movieservice) {
		this.movieservice = movieservice;
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

	@RequestMapping("/searchResult")
	public String searchResult() {
		return "search_result";
	}

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
