package service;

import dto.MemDTO;

public interface MemService {

	// 중복검사
	public boolean idCheckProcess(MemDTO userDTO);

	// 회원가입 진행
	public void registerProcess(MemDTO userDTO);

	// 로그인 진행
	public MemDTO loginProcess(MemDTO userDTO);
	
	// 
}
