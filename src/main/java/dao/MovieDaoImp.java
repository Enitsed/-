package dao;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;

import dto.CommentDTO;
import dto.MovieDTO;

public class MovieDaoImp implements MovieDAO{
	SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public MovieDaoImp() {
		
	}
	
	@Override
	public List<MovieDTO> movieInfoProcess() {
		return sqlSession.selectList("movie.list");
	}

	@Override
	public List<CommentDTO> commentListMethod(int movie_num) {
		return sqlSession.selectList("movie.comment",movie_num);
	}

}
