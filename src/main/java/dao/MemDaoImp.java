package dao;

import java.util.HashMap;

import java.util.List;

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
		sqlSession.insert("mem.insertGrade",userDTO);
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

	@Override
	public List<MemDTO> memInfo(MemDTO userDTO) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mem.memInfoList",userDTO);
	}

	@Override
	public void memUpdate(MemDTO userDTO) {
		System.out.println("DaoImp : "+userDTO.getMem_num() +"/"+ userDTO.getMem_grade());
		sqlSession.update("mem.memUpdate",userDTO);
		
	}

	@Override
	public void profileUpdate(MemDTO userDTO) {
		sqlSession.update("mem.profileUpdate",userDTO);
	}

}
