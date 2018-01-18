package service;

import dto.MemDTO;

public interface MemService {

	public boolean find(MemDTO dto);
	public void register(MemDTO dto);
}
