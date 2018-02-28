package service;

import java.util.List;

import dto.CommentDTO;
import dto.LikeDTO;
import dto.MoreCommentDTO;
import dto.MovieDTO;

public interface MovieService {
	public List<MovieDTO> movieInfoProcess(int page, int category);
	public List<CommentDTO> commentListProcess(int movie_num);
	public List<MovieDTO> moviedetailProcess(int movie_num);
	public String likeProcess(LikeDTO dto);
	
	public void likeplusProcess(LikeDTO dto);
	public void likeminusProcess(LikeDTO dto);
	
	public void likeinsertProcess(LikeDTO dto);
	public void likedeleteProcess(LikeDTO dto);
	
	public int mem_numProcees(LikeDTO dto);
	
	public void insertCommentProcess(CommentDTO dto);
	public void deleteCommentProcess(int comment_num);

	public List<MovieDTO> movieSearchListProcess(String keyword, int page);
	public int searchCountProcess(String keyword);
	public void addRating(int member_num, int movie_num, int rating);
	public MovieDTO BoxOfficeInsert(String name);
	public MovieDTO boxOffice(String name);
	public void BoxOfficeDirectorInsert(MovieDTO dto);
	public void BoxOfficeCategoryInsert(MovieDTO dto);
	public void BoxOfficeActorInsert(MovieDTO dto);
	public List<CommentDTO> moreCommentProcess(MoreCommentDTO dto);
	public List<MovieDTO> maxCommentMovie();

}
