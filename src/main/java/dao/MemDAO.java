package dao;

import dto.MemDTO;

public interface MemDAO {

	public void register(MemDTO dto);

	public String find(MemDTO dto);
}
