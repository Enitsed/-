package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import api.MovieApi;
import dto.ActorDTO;
import dto.CategoryDTO;
import dto.CommentDTO;
import dto.DirectorDTO;
import dto.LikeDTO;
import dto.MoreCommentDTO;
import dto.MovieDTO;

public class MovieDaoImp implements MovieDAO {
	SqlSessionTemplate sqlSession;

	public MovieDaoImp() {

	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<MovieDTO> movieInfoProcess(int page) {
		int end = page * 8;
		int start = end - 8;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		List<MovieDTO> list = sqlSession.selectList("movie.list", map);
		for (int i = 0; i < list.size(); i++) {
			List<DirectorDTO> directorList = sqlSession.selectList("movie.director", list.get(i).getMovie_num());
			list.get(i).setMovie_director(directorList);
			List<ActorDTO> actorList = sqlSession.selectList("movie.actor", list.get(i).getMovie_num());
			list.get(i).setMovie_actor(actorList);
			List<CategoryDTO> categoryList = sqlSession.selectList("movie.category", list.get(i).getMovie_num());
			list.get(i).setCategory(categoryList);
		}
		return list;
	}

	@Override
	public List<CommentDTO> commentListProcess(int movie_num) {
		return sqlSession.selectList("movie.comment", movie_num);
	}

	@Override
	public List<MovieDTO> moviedetailProcess(int movie_num) {
		return sqlSession.selectList("movie.info", movie_num);
	}

	@Override
	public String likeProcess(LikeDTO dto) {
		return sqlSession.selectOne("movie.like", dto);
	}

	@Override
	public void likeplusProcess(LikeDTO dto) {
		sqlSession.update("movie.likeplus", dto);
	}

	@Override
	public void likeminusProcess(LikeDTO dto) {
		sqlSession.update("movie.likeminus", dto);
	}

	@Override
	public void likeinsertProcess(LikeDTO dto) {
		sqlSession.insert("movie.likeinsert", dto);
	}

	@Override
	public void likedeleteProcess(LikeDTO dto) {
		sqlSession.delete("movie.likedelete", dto);

	}

	@Override
	public void insertCommentProcees(CommentDTO dto) {
		sqlSession.insert("movie.replyinsert", dto);

	}

	@Override
	public List<MovieDTO> movieListProcess(String keyword) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("movie.searchList", keyword);
	}

	@Override
	public int searchCountProcess(String keyword) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("movie.searchCount", keyword);
	}

	@Override
	public void deleteCommentProcess(int comment_num) {
		sqlSession.delete("movie.commentdelete", comment_num);
	}

	public void addRating(int member_num, int movie_num, int rating) {
		Map<String, Integer> map = new HashMap();
		map.put("member_num", member_num);
		map.put("movie_num", movie_num);
		map.put("rating", rating);

		int count = sqlSession.selectOne("movie.searchRating", map);
		if (count > 0) {
			sqlSession.selectOne("movie.updateRating", map);
		} else {
			sqlSession.selectOne("movie.pluseRating", map);
		}
	}

	@Override
	public MovieDTO BoxOfficeInsert(String name) {
		int count = sqlSession.selectOne("movie.searchMovie", name);
		MovieDTO dto = null;
		if (count <= 0) {
			MovieApi api = new MovieApi();
			dto = api.insertMovie(name);

			if (dto == null)
				return null;
			else {
				System.out.println("asd : " + dto.getMovie_kor_title());
				sqlSession.insert("movie.insertMovie", dto);
				System.out.println("asd : " + dto.getMovie_kor_title());
			}
		}
		return dto;
	}

	@Override
	public void BoxOfficeActorInsert(MovieDTO dto) {
		for (int i = 0; i < dto.getMovie_actor().size(); i++) {
			if (dto.getMovie_actor().get(i).getActor_name() != null) {
				sqlSession.insert("movie.insertActor", dto.getMovie_actor().get(i).getActor_name());
				HashMap<String, Object> actor = new HashMap<String, Object>();
				actor.put("movie_actor", dto.getMovie_actor().get(i).getActor_name());
				actor.put("movie_kor_title", dto.getMovie_kor_title());
				sqlSession.insert("movie.insertMovieActor", actor);
			}
		}
	}

	@Override
	public void BoxOfficeDirectorInsert(MovieDTO dto) {
		for (int i = 0; i < dto.getMovie_director().size(); i++) {
			if (dto.getMovie_director().get(i).getDirector_name() != null) {
				sqlSession.insert("movie.insertDirector", dto.getMovie_director().get(i).getDirector_name());
				HashMap<String, Object> director = new HashMap<String, Object>();
				director.put("movie_director", dto.getMovie_director().get(i).getDirector_name());
				director.put("movie_kor_title", dto.getMovie_kor_title());
				sqlSession.insert("movie.insertMovieDirector", director);
			}
		}
	}

	@Override
	public void BoxOfficeCategoryInsert(MovieDTO dto) {
		for (int i = 0; i < dto.getCategory().size(); i++) {
			HashMap<String, Object> category = new HashMap<String, Object>();
			category.put("movie_category", dto.getCategory().get(i).getCategory_name());
			category.put("movie_kor_title", dto.getMovie_kor_title());
			sqlSession.insert("movie.insertMovieCategory", category);

		}
	}

	@Override
	public MovieDTO boxOffice(String name) {
		MovieDTO dto = sqlSession.selectOne("movie.boxoffice", name);

		if (dto == null)
			return null;

		try {
			List<DirectorDTO> directorList = sqlSession.selectList("movie.director", dto.getMovie_num());
			dto.setMovie_director(directorList);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		try {
			List<ActorDTO> actorList = sqlSession.selectList("movie.actor", dto.getMovie_num());
			dto.setMovie_actor(actorList);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		try {
			List<CategoryDTO> categoryList = sqlSession.selectList("movie.category", dto.getMovie_num());
			dto.setCategory(categoryList);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public List<CommentDTO> morecommentListProcess(MoreCommentDTO dto) {
		return sqlSession.selectList("movie.morecomment", dto);
	}

	@Override
	public List<MovieDTO> maxCommentMovie() {
		List<MovieDTO> list = sqlSession.selectList("movie.commentMaxMovie");
		for (int i = 0; i < list.size(); i++) {
			List<DirectorDTO> directorList = sqlSession.selectList("movie.director", list.get(i).getMovie_num());
			list.get(i).setMovie_director(directorList);
			List<ActorDTO> actorList = sqlSession.selectList("movie.actor", list.get(i).getMovie_num());
			list.get(i).setMovie_actor(actorList);
			List<CategoryDTO> categoryList = sqlSession.selectList("movie.category", list.get(i).getMovie_num());
			list.get(i).setCategory(categoryList);
		}
		return list;
	}
}
