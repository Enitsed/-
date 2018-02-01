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

import dto.BoardDTO;
import dto.MovieDTO;
import dto.PageDTO;
import service.BoardService;
import service.MovieService;

@Controller
public class MovieController {
	MovieService movieservice;
	BoardService boardservice;
	private PageDTO pdto;
	private int currentPage;
	
	public void setBoardservice(BoardService boardservice) {
		this.boardservice = boardservice;
	}
	
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
	public ModelAndView movieSearchResult(@RequestParam(defaultValue = "") String keyword, PageDTO pv) {
		ModelAndView mav = new ModelAndView();
		List<MovieDTO> searchmov = movieservice.movieListProcess(keyword);
		List<BoardDTO> searchbod = boardservice.searchListProcess(keyword);
		int searchcount = movieservice.searchCountProcess(keyword);
		int totalRecord = boardservice.searchCountProcess(keyword);
		if (totalRecord >= 1) {
			if (pv.getCurrentPage() == 0) {
				currentPage = 1;
			} else {
				currentPage = pv.getCurrentPage();
			}
			pdto = new PageDTO(currentPage, totalRecord);
			mav.addObject("pv", pdto);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchbod", searchbod);
		map.put("searchmov", searchmov);
		map.put("keyword", keyword);
		map.put("searchcount", searchcount);
		map.put("boardcount", totalRecord);
		mav.addObject("map", map);
		mav.setViewName("search_result");
		
		return mav;	
	}
	
	@RequestMapping(value = "/addrating.do")
	public @ResponseBody String movieAddRating(int movie_num, int member_num, int rating) {
		movieservice.addRating(member_num, movie_num, rating);
		return "1";
	}
}
