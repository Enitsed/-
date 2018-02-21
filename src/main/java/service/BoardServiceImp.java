package service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.BoardDAO;
import dto.BoardDTO;
import dto.MemDTO;
import dto.PageDTO;

public class BoardServiceImp implements BoardService {
	BoardDAO dao;

	public BoardServiceImp() {

	}

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}

	@Override
	public int countProcess(Integer board_category) {
		return dao.count(board_category);
	}

	@Override
	public List<BoardDTO> listProcess(HashMap<String, Integer> param) {
		return dao.list(param);
	}

	@Override
	public void insertProcess(BoardDTO dto) {
		dao.save(dto);
	}

	@Override
	public BoardDTO contentProcess(int num) {
		dao.readCount(num);
		return dao.content(num);
	}

	@Override
	public void reStepProcess(BoardDTO dto) {
		dao.reStepCount(dto);
		dto.setBoard_reply_step(dto.getBoard_reply_step() + 1);
		dto.setBoard_reply_level(dto.getBoard_reply_level() + 1);
		dao.save(dto);
	}

	@Override
	public BoardDTO updateSelectProcess(int num) {
		return dao.updateNum(num);
	}

	@Override
	public void updateProcess(BoardDTO dto, HttpServletRequest request) {
		dao.update(dto);
	}

	@Override
	public void deleteProcess(int num, HttpServletRequest request) {
		dao.delete(num);
	}

	@Override
	public List<BoardDTO> searchListProcess(String keyword) {
		return dao.searchList(keyword);
	}

	@Override
	public int searchCountProcess(String keyword) {
		return dao.searchCount(keyword);
	}

	@Override
	public List<BoardDTO> myboardProcess(HashMap<String, Integer> map) {
		return dao.myboard(map);
	}

	@Override
	public int myboardCountProcess(MemDTO userDTO) {
		return dao.myboardCount(userDTO);
	}

}
