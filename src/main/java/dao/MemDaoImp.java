package dao;

import org.mybatis.spring.SqlSessionTemplate;

import dto.MemDTO;

public class MemDaoImp implements MemDAO {
	SqlSessionTemplate sqlSession;

	public MemDaoImp() {
		// TODO Auto-generated constructor stub
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void register(MemDTO dto) {
		sqlSession.insert("mem.insert", dto);
	}

	@Override
	public String find(MemDTO dto) {

		return sqlSession.selectOne("mem.find", dto);
	}

	@Override
	public String chkId(String mem_id) {
		return sqlSession.selectOne("mem.chk", mem_id);
	}

	@Override
	public String login(MemDTO dto) {
		return sqlSession.selectOne("mem.login", dto);

	}

}
