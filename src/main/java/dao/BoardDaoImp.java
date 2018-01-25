package dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import dto.BoardDTO;
import dto.PageDTO;

public class BoardDaoImp implements BoardDAO {
	SqlSessionTemplate session;

	public BoardDaoImp() {
		// TODO Auto-generated constructor stub
	}

	public void setSession(SqlSessionTemplate session) {
		this.session = session;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardDTO> list(PageDTO pv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readCount(int num) {
		// TODO Auto-generated method stub

	}

	@Override
	public BoardDTO content(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reStepCount(BoardDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(BoardDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public BoardDTO updateNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(BoardDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getFile(int num) {
		// TODO Auto-generated method stub
		return null;
	}

}
