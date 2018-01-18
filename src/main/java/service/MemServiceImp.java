package service;

import dao.MemDAO;
import dto.MemDTO;

public class MemServiceImp implements MemService{
	
	MemDAO dao;
	public void setDao(MemDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean findProcess(MemDTO dto) {
		String id = dao.find(dto);
		return (id==null) ? false:true;
	}

	@Override
	public void registerProcess(MemDTO dto) {
		dao.register(dto);
	}

}
