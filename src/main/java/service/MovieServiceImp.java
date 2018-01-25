package service;

import java.util.List;

import dao.MovieDAO;
import dto.CommentDTO;
import dto.LikeDTO;
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
	public List<CommentDTO> commentListProcess(int movie_num) {
		return dao.commentListProcess(movie_num);
	}
	@Override
	public List<MovieDTO> moviedetailProcess(int movie_num) {
		return dao.moviedetailProcess(movie_num);
	}
	@Override
	public String likeProcess(LikeDTO dto) {
		return dao.likeProcess(dto);
	}

}
