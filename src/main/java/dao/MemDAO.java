package dao;

import java.util.List;

import dto.MemDTO;

public interface MemDAO {

	public void register(MemDTO userDTO);

	public Integer idCheck(MemDTO userDTO);

	public MemDTO login(MemDTO userDTO);
	
	public MemDTO findId(MemDTO userDTO);
	
	public MemDTO findPw(MemDTO userDTO);
	
	public void updateInfo(MemDTO userDTO);
	
	public List<MemDTO> memInfo(MemDTO userDTO);
}
