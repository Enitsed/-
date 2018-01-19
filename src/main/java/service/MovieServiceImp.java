package service;

import java.util.List;

import dao.MovieDAO;
import dto.CommentDTO;
import dto.MovieDTO;

public class MovieServiceImp implements MovieService{
	MovieDAO dao;
	public void setDao(MovieDAO dao) {
		this.dao = dao;
	}
	public MovieServiceImp() {
		
	}
	@Override
	public List<MovieDTO> movieInfoProcess(int page) {
		return dao.movieInfoProcess(page);
	}
	@Override
	public List<CommentDTO> commentListMethod(int movie_num) {
		return dao.commentListMethod(movie_num);
	}

}
