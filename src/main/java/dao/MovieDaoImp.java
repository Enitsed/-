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
	public List<MovieDTO> movieInfoProcess(int page) {
		int page2 = page * 3;
		return sqlSession.selectList("movie.list" , page2);
	}

	@Override
	public List<CommentDTO> commentListMethod(int movie_num) {
		return sqlSession.selectList("movie.comment",movie_num);
	}

}
