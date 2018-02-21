package dao;

import java.util.HashMap;
import java.util.List;

import dto.BoardDTO;
import dto.MemDTO;
import dto.PageDTO;

public interface BoardDAO {
	public int count(Integer board_category);

	public List<BoardDTO> list(HashMap<String, Integer> param);

	public void readCount(int num);

	public BoardDTO content(int num);

	public void reStepCount(BoardDTO dto);

	public void save(BoardDTO dto);

	public void update(BoardDTO dto);

	public void delete(int num);

	public BoardDTO updateNum(int num);
	
	public List<BoardDTO> searchList(String keyword);
	
	public int searchCount(String keyword);
	
	public int myboardCount(MemDTO userdto);
	public List<BoardDTO> myboard(HashMap<String, Integer> map);

}
