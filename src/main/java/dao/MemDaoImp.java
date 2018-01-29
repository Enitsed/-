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
	public void register(MemDTO userDTO) {
		sqlSession.insert("mem.insert", userDTO);
	}

	@Override
	public MemDTO login(MemDTO userDTO) {
		return sqlSession.selectOne("mem.login", userDTO);
	}

	@Override
	public Integer idCheck(MemDTO userDTO) {
		return sqlSession.selectOne("mem.isMemberExist", userDTO.getMem_id());
	}

	@Override
	public MemDTO findId(MemDTO userDTO) {
		return sqlSession.selectOne("mem.findId",userDTO);
	}
	
	@Override
	public MemDTO findPw(MemDTO userDTO) {
		return sqlSession.selectOne("mem.findPw",userDTO);
	}

	@Override
	public void updateInfo(MemDTO userDTO) {
		sqlSession.update("mem.updateInfo",userDTO);
	}

}
