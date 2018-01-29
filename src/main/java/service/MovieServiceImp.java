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
		//dao.insertCommentProcees(dto);
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
	@Override
	public void likeplusProcess(LikeDTO dto) {
		dao.likeplusProcess(dto);
	}
	@Override
	public void likeminusProcess(LikeDTO dto) {
		dao.likeminusProcess(dto);
	}
	@Override
	public void likeinsertProcess(LikeDTO dto) {
		dao.likeinsertProcess(dto);
	}
	@Override
	public void likedeleteProcess(LikeDTO dto) {
		dao.likedeleteProcess(dto);
		
	}
	@Override
	public void insertCommentProcess(CommentDTO dto) {
		dao.insertCommentProcees(dto);
	}

}
