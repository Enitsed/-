package dao;

import java.util.List;

import dto.CommentDTO;
import dto.LikeDTO;
import dto.MovieDTO;


public interface MovieDAO {
	public List<MovieDTO> movieInfoProcess(int page);
	public List<CommentDTO> commentListProcess(int movie_num);
	public List<MovieDTO> moviedetailProcess(int movie_num);
	public String likeProcess(LikeDTO dto);

}
