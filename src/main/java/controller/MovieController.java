package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "/searchResult", method = RequestMethod.POST)
	public ModelAndView movieSearchResult(@RequestParam(defaultValue = "") String keyword) {
		ModelAndView mav = new ModelAndView();
		List<MovieDTO> searchlist = movieservice.movieListProcess(keyword);
		int searchcount = movieservice.searchCountProcess(keyword);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchlist", searchlist);
		map.put("keyword", keyword);
		map.put("searchcount", searchcount);
		mav.addObject("map", map);
		mav.setViewName("search_result");
		System.out.println("1");
		return mav;
	}
}
