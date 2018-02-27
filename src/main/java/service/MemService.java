package service;

import java.util.List;

import dto.CategoryDTO;
import dto.CatgDTO;
import dto.MemDTO;
import dto.MyCommentDTO;
import dto.RatingDTO;
import dto.WishListDTO;

public interface MemService {

	// 중복검사
	public boolean idCheckProcess(MemDTO userDTO);

	// 회원가입 진행
	public void registerProcess(MemDTO userDTO);

	// 로그인 진행
	public MemDTO loginProcess(MemDTO userDTO);

	// 아이디찾기
	public MemDTO findIdProcess(MemDTO userDTO);

	// 비밀번호 찾기
	public MemDTO findPwProcess(MemDTO userDTO);

	public void updateProcess(MemDTO userDTO);

	public List<MemDTO> memInfo(MemDTO userDTO);

	public void memUpdate(MemDTO userDTO);

	public void profileUpdate(MemDTO userDTO);

	public MemDTO mList(int mem_num);
	
	public void commentProfileUpdate(MemDTO userDTO);
	
	public List<RatingDTO> profile_rating(MemDTO userDTO);
	
	public List<MyCommentDTO> mycomment(MemDTO userDTO);
	
	public List<WishListDTO> mylist(WishListDTO wish);
	
	public List<WishListDTO> wishlist(WishListDTO wish);
	
	public List<CatgDTO> findcategory(int movie_num);
	
	public int findoverlap(WishListDTO wish);
	
	public void insertwishlist(WishListDTO wish);
}

