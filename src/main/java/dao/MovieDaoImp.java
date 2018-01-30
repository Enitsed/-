package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import dto.ActorDTO;
import dto.CategoryDTO;
import dto.CommentDTO;
import dto.DirectorDTO;
import dto.LikeDTO;
import dto.MovieDTO;

public class MovieDaoImp implements MovieDAO {
	SqlSessionTemplate sqlSession;

	public MovieDaoImp() {

	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<MovieDTO> movieInfoProcess(int page) {
		int end = page * 8;
		int start = end - 8;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		List<MovieDTO> list = sqlSession.selectList("movie.list", map);
		for (int i = 0; i < list.size(); i++) {
			List<DirectorDTO> directorList = sqlSession.selectList("movie.director", list.get(i).getMovie_num());
			list.get(i).setMovie_director(directorList);
			List<ActorDTO> actorList = sqlSession.selectList("movie.actor", list.get(i).getMovie_num());
			list.get(i).setMovie_actor(actorList);
			List<CategoryDTO> categoryList = sqlSession.selectList("movie.category", list.get(i).getMovie_num());
			list.get(i).setCategory(categoryList);
		}
		return list;
	}

	@Override
	public List<CommentDTO> commentListProcess(int movie_num) {
		return sqlSession.selectList("movie.comment", movie_num);
	}

	@Override
	public List<MovieDTO> moviedetailProcess(int movie_num) {
		return sqlSession.selectList("movie.info", movie_num);
	}

	@Override
	public String likeProcess(LikeDTO dto) {
		return sqlSession.selectOne("movie.like",dto);
	}

	@Override
	public void likeplusProcess(LikeDTO dto) {
		sqlSession.update("movie.likeplus",dto);
	}

	@Override
	public void likeminusProcess(LikeDTO dto) {
		sqlSession.update("movie.likeminus",dto);
	}

	@Override
	public void likeinsertProcess(LikeDTO dto) {
		sqlSession.insert("movie.likeinsert",dto);
	}

	@Override
	public void likedeleteProcess(LikeDTO dto) {
		sqlSession.delete("movie.likedelete",dto);
		
	}

	@Override
	public void insertCommentProcees(CommentDTO dto) {
		sqlSession.insert("movie.replyinsert",dto);
		
	}

	@Override
	public List<MovieDTO> movieListProcess(String keyword) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("movie.searchList",keyword);
	}

	@Override
	public int searchCountProcess(String keyword) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("movie.searchCount",keyword);
	}

	@Override
	public void deleteCommentProcess(int comment_num) {
		 sqlSession.delete("movie.commentdelete",comment_num);
	}


	public void addRating(int member_num, int movie_num, int rating) {
		 Map<String, Integer> map = new HashMap();
		 map.put("member_num", member_num);
		 map.put("movie_num", movie_num);
		 map.put("rating", rating);

		int count = sqlSession.selectOne("movie.searchRating",map);
		if(count > 0) {
			sqlSession.selectOne("movie.updateRating",map);
		}else {
			sqlSession.selectOne("movie.pluseRating",map);
		}
	}
}
