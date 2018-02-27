package dao;

import java.util.List;



import org.mybatis.spring.SqlSessionTemplate;

import dto.CategoryDTO;
import dto.CatgDTO;
import dto.MemDTO;
import dto.MyCommentDTO;
import dto.RatingDTO;
import dto.WishListDTO;

public class MemDaoImp implements MemDAO {
	SqlSessionTemplate sqlSession;

	public MemDaoImp() {
		// TODO Auto-generated constructor stub
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void register(MemDTO userDTO) {
		sqlSession.insert("mem.insert", userDTO);
		sqlSession.insert("mem.insertGrade",userDTO);
	}

	@Override
	public MemDTO login(MemDTO userDTO) {
		return sqlSession.selectOne("mem.login", userDTO);
	}

	@Override
	public Integer idCheck(MemDTO userDTO) {
		return sqlSession.selectOne("mem.isMemberExist", userDTO.getMem_id());
	}

	@Override
	public MemDTO findId(MemDTO userDTO) {
		return sqlSession.selectOne("mem.findId",userDTO);
	}
	
	@Override
	public MemDTO findPw(MemDTO userDTO) {
		return sqlSession.selectOne("mem.findPw",userDTO);
	}

	@Override
	public void updateInfo(MemDTO userDTO) {
		sqlSession.update("mem.updateInfo",userDTO);
	}

	@Override
	public List<MemDTO> memInfo(MemDTO userDTO) {
		return sqlSession.selectList("mem.memInfoList",userDTO);
	}

	@Override
	public void memUpdate(MemDTO userDTO) {
		sqlSession.update("mem.memUpdate",userDTO);
		
	}

	@Override
	public void profileUpdate(MemDTO userDTO) {
		sqlSession.update("mem.profileUpdate",userDTO);
	}
	public MemDTO mList(int mem_num) {
		return sqlSession.selectOne("mem.mList",mem_num);
	}

	@Override
	public void commentProfileUpdate(MemDTO userDTO) {
		sqlSession.update("mem.movie_comment_profile_update",userDTO);
	}

	@Override
	public List<RatingDTO> profile_rating(MemDTO userDTO) {
		return sqlSession.selectList("mem.profile_rating",userDTO);
	}

	@Override
	public List<MyCommentDTO> mycomment(MemDTO userDTO) {
		return sqlSession.selectList("movie.mycomment_list",userDTO);
	}

	@Override
	public List<WishListDTO> wishlist(WishListDTO wish) {
		return sqlSession.selectList("mem.wishlist",wish);
	}

	@Override
	public List<WishListDTO> mylist(WishListDTO wish) {
		return sqlSession.selectList("mem.mylist", wish);
	}

	@Override
	public List<CatgDTO> findcategory(int movie_num) {
		System.out.println("영화번호:"+movie_num);
		return sqlSession.selectList("mem.findcategory",movie_num);
	}

	@Override
	public void insertwishlist(WishListDTO wish) {
		sqlSession.insert("mem.insertwishlist",wish);
	}

	@Override
	public int findoverlap(WishListDTO wish) {
		return sqlSession.selectOne("mem.findoverlap",wish);
	}

	

}
