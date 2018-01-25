package service;

import dao.MemDAO;
import dto.MemDTO;

public class MemServiceImp implements MemService {
	MemDAO dao;

	public MemServiceImp() {
		// TODO Auto-generated constructor stub
	}

	public void setDao(MemDAO dao) {
		this.dao = dao;
	}

	@Override
	public boolean idCheckProcess(MemDTO userDTO) {
		// dao.chkId의 결과값이 1이면 회원 아니면 비회원
		return (dao.idCheck(userDTO) != null) ? true : false;
	}

	@Override
	public void registerProcess(MemDTO userDTO) {
		// 회원가입 메서드
		dao.register(userDTO);
	}

	@Override
	public MemDTO loginProcess(MemDTO userDTO) {
		// 로그인 메서드
		return dao.login(userDTO);
	}

}
