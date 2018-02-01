package service;

import java.util.List;

import dao.MovieDAO;
import dao.MovieDaoImp;
import dto.CommentDTO;
import dto.LikeDTO;
import dto.MovieDTO;

public class MovieServiceImp implements MovieService{
	MovieDAO dao;
	
	public void setDao(MovieDAO dao) {
		this.dao = dao;
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
	
	public List<MovieDTO> movieListProcess(String keyword) {
		// TODO Auto-generated method stub
		return dao.movieListProcess(keyword);
	}
	@Override
	public int searchCountProcess(String keyword) {
		return dao.searchCountProcess(keyword);
	}
	@Override
	public void addRating(int member_num, int movie_num, int rating) {
		dao.addRating(member_num, movie_num, rating);
	}
	@Override
	public MovieDTO BoxOfficeInsert(String name) {
		return dao.BoxOfficeInsert(name);
	}
	@Override
	public MovieDTO boxOffice(String name){
		return dao.boxOffice(name);
	}
	@Override
	public void BoxOfficeDirectorInsert(MovieDTO dto) {
		dao.BoxOfficeDirectorInsert(dto);
	}
	@Override
	public void BoxOfficeCategoryInsert(MovieDTO dto) {
		dao.BoxOfficeCategoryInsert(dto);
	}
	@Override
	public void BoxOfficeActorInsert(MovieDTO dto) {
		dao.BoxOfficeActorInsert(dto);
	}
}
