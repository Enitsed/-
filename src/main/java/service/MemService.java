package service;

import java.util.List;

import dto.MemDTO;

public interface MemService {

	// 중복검사
	public boolean idCheckProcess(MemDTO userDTO);

	// 회원가입 진행
	public void registerProcess(MemDTO userDTO);

	// 로그인 진행
	public MemDTO loginProcess(MemDTO userDTO);

	// 아이디찾기
	public MemDTO findIdProcess(MemDTO userDTO);

	// 비밀번호 찾기
	public MemDTO findPwProcess(MemDTO userDTO);

	public void updateProcess(MemDTO userDTO);

	public List<MemDTO> memInfo(MemDTO userDTO);

	public void memUpdate(MemDTO userDTO);

	public void profileUpdate(MemDTO userDTO);

	public MemDTO mList(int mem_num);
	
	public void commentProfileUpdate(MemDTO userDTO);
}
