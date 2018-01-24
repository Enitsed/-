package dao;

import dto.MemDTO;

public interface MemDAO {

	public void register(MemDTO userDTO);

	public Integer idCheck(MemDTO userDTO);

	public MemDTO login(MemDTO userDTO);
}
