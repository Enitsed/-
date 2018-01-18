package dao;

import org.mybatis.spring.SqlSessionTemplate;

import dto.MemDTO;


public class MemDaoImp implements MemDAO{

	SqlSessionTemplate sqlSession;
	
	
	public MemDaoImp() {
		
	}
	
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
