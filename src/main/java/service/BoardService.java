package service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDTO;

public interface BoardService {
	public int countProcess(Integer board_category);

	public List<BoardDTO> listProcess(HashMap<String, Integer> param);

	public void insertProcess(BoardDTO dto);

	public BoardDTO contentProcess(int num);

	public void reStepProcess(BoardDTO dto);

	public void updateProcess(BoardDTO dto, HttpServletRequest request);

	public void deleteProcess(int num, HttpServletRequest request);

	public BoardDTO updateSelectProcess(int num);
	
	public List<BoardDTO> searchListProcess(String keyword);
	
	public int searchCountProcess(String keyword);
	
}
