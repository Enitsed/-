package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import api.BoxOffice;
import api.MovieNewsApi;
import dto.CommentDTO;
import dto.LikeDTO;
import dto.MemDTO;
import dto.MovieDTO;

import dto.MoreCommentDTO;
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
		MovieNewsApi api = new MovieNewsApi();
		BoxOffice api2 = new BoxOffice();
		List<String> list = api2.boxOffice();
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		List<MovieDTO> boxOfficeMovieList = new ArrayList<MovieDTO>();

		for (String i : list) {
			System.out.println(i);
			MovieDTO dto = movieservice.BoxOfficeInsert(i);
			if (dto != null)
				movieList.add(dto);
			else
				System.out.println("영화 없음");
		}

		for (MovieDTO i : movieList) {
			if (i != null) {
				movieservice.BoxOfficeActorInsert(i);
				movieservice.BoxOfficeDirectorInsert(i);
				movieservice.BoxOfficeCategoryInsert(i);
			}
		}

		for (String i : list) {
			try {
				MovieDTO dto = movieservice.boxOffice(i);
				if (dto.getMovie_kor_title() != null)
					boxOfficeMovieList.add(dto);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

		mav.addObject("movie", boxOfficeMovieList);
		mav.addObject("commentMovie", movieservice.maxCommentMovie());

		api.MovieNewsAPI(mav);
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/searchResult")
	public String searchResult() {
		return "search_result";
	}

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
		return movieservice.commentListProcess(dto.getMovie_num());
	}

	@RequestMapping(value = "deletecomment", method = RequestMethod.GET)
	public @ResponseBody List<CommentDTO> deleteComment(int comment_num, int movie_num) {
		movieservice.deleteCommentProcess(comment_num);
		return movieservice.commentListProcess(movie_num);
	}

	@RequestMapping(value = "morecomment", method = RequestMethod.GET)
	public @ResponseBody List<CommentDTO> morecomment(MoreCommentDTO dto) {
		return movieservice.moreCommentProcess(dto);
	}

}
