package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;

import dto.BoardDTO;

public class BoardDaoImp implements BoardDAO {
	SqlSessionTemplate sqlSession;

	public BoardDaoImp() {

	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int count(Integer board_category) {
		return sqlSession.selectOne("board.count", board_category);
	}

	@Override
	public List<BoardDTO> list(HashMap<String, Integer> param) {
		return sqlSession.selectList("board.list", param);
	}

	@Override
	public void readCount(int num) {
		sqlSession.update("board.readCount", num);
	}

	@Override
	public BoardDTO content(int num) {
		return sqlSession.selectOne("board.content", num);
	}

	@Override
	public void reStepCount(BoardDTO dto) {
		sqlSession.update("board.reStepCount", dto);
	}

	@Override
	public void save(BoardDTO dto) {
		sqlSession.insert("board.save", dto);
	}

	@Override
	public void update(BoardDTO dto) {
		sqlSession.update("board.update", dto);
	}

	@Override
	public void delete(int num) {
		sqlSession.delete("board.delete", num);
	}

	@Override
	public BoardDTO updateNum(int num) {
		return sqlSession.selectOne("board.content", num);
	}

	@Override
	public List<BoardDTO> searchList(String keyword) {

		return sqlSession.selectList("board.searchList", keyword);
	}

	@Override
	public int searchCount(String keyword) {
		return sqlSession.selectOne("board.searchCount", keyword);
	}

}
