package service;

import dto.MemDTO;

public interface MemService {

	public boolean findProcess(MemDTO dto);

	public void registerProcess(MemDTO dto);

	public boolean chkIdProcess(String mem_id);

	public String login(MemDTO dto);
}
