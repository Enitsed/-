package dao;

import org.mybatis.spring.SqlSessionTemplate;

import dto.MemDTO;


public class MemDaoImp implements MemDAO{
	public MemDaoImp() {
		// TODO Auto-generated constructor stub
	}

	SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void register(MemDTO dto) {
		sqlSession.insert("mem.insert",dto);	
	}
	
	@Override
	public String find(MemDTO dto) {
		
		return sqlSession.selectOne("mem.find",dto);
	}

}
