package dao;

import java.util.List;

import dto.CategoryDTO;
import dto.CatgDTO;
import dto.MemDTO;
import dto.MyCommentDTO;
import dto.RatingDTO;
import dto.WishListDTO;

public interface MemDAO {

	public void register(MemDTO userDTO);

	public Integer idCheck(MemDTO userDTO);

	public MemDTO login(MemDTO userDTO);
	
	public MemDTO findId(MemDTO userDTO);
	
	public MemDTO findPw(MemDTO userDTO);
	
	public void updateInfo(MemDTO userDTO);
	
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
