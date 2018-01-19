package dao;

import java.util.List;

import dto.CommentDTO;
import dto.MovieDTO;


public interface MovieDAO {
	public List<MovieDTO> movieInfoProcess();
	public List<CommentDTO> commentListMethod(int movie_num);

}
