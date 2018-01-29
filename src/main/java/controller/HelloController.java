package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import api.MovieApi;
import dto.CommentDTO;
import dto.LikeDTO;
import dto.MemDTO;
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

	/*
	 * @RequestMapping(value = "info", method = RequestMethod.GET)
	 * public @ResponseBody HashMap<String, Object> movieDetailInfo(int movie_num) {
	 * HashMap<String, Object> map = new HashMap<String, Object>(); map.put("info",
	 * movieservice.moviedetailProcess(movie_num)); map.put("comment",
	 * movieservice.commentListProcess(movie_num)); List<CommentDTO> aList =
	 * movieservice.commentListProcess(movie_num); System.out.println(aList.size());
	 * return map; }
	 */
	@RequestMapping(value = "info", method = RequestMethod.GET)
	public @ResponseBody List<CommentDTO> movieDetailInfo(int movie_num) {
		// List<CommentDTO> aList = movieservice.commentListProcess(movie_num);

		return movieservice.commentListProcess(movie_num);
	}

	@RequestMapping(value = "like", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> like(LikeDTO dto, HttpSession session, MemDTO dto2) {
		HashMap<String, String> map = new HashMap<String, String>();
		System.out.println("아이디:" + dto.getMem_id());
		System.out.println("댓글번호:" + dto.getComment_num());
		System.out.println("로그인한아이디:" + dto2.getMem_id());
		System.out.println(movieservice.likeProcess(dto));
		if (movieservice.likeProcess(dto) != null) {// 좋아요를 전에 눌럿엇다면
			// 코멘트테이블에서 -1해준다. map에 널값 보낸다.
			movieservice.likeminusProcess(dto);
			movieservice.likedeleteProcess(dto);

		} else {// 좋아요를 누른적이 없다면
				// 코멘트테이블에서 +1해준다. map에 값을 보낸다.
			movieservice.likeplusProcess(dto);
			movieservice.likeinsertProcess(dto);
			map.put("like", movieservice.likeProcess(dto));
		}

		return map;
	}

	@RequestMapping(value = "insertcomment", method = RequestMethod.GET)
	public @ResponseBody List<CommentDTO> insertcomment(CommentDTO dto) {
		movieservice.insertCommentProcess(dto);
		System.out.println("영화번호:" + dto.getMovie_num());

		return movieservice.commentListProcess(dto.getMovie_num());
	}

}
