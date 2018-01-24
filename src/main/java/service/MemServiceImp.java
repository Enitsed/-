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
	public boolean findProcess(MemDTO dto) {
		String id = dao.find(dto);
		return (id == null) ? false : true;
	}

	@Override
	public void registerProcess(MemDTO dto) {
		dao.register(dto);
	}

	@Override
	public boolean chkIdProcess(String mem_id) {
		String cid = dao.chkId(mem_id);
		return (cid == null) ? false : true;
	}

	@Override
	public String login(MemDTO dto) {
		return dao.login(dto);
	}

}
