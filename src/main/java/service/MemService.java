package service;

import dto.MemDTO;

public interface MemService {

	public boolean findProcess(MemDTO dto);
	public void registerProcess(MemDTO dto);
}
