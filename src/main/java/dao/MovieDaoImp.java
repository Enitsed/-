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
		
		// 이 아래 부분이 에러 주범인데 쿼리문 변경 할 예정. DTO 수정 필요함. 해볼 사람은 해봐.
		// 원인은 멀티 스레드 환경에서 한 스레드가 디비 커넥션 이용하여 조회후 연결을 끊는데 그 뒤에 
		// 디비에 접근하는 스레드 들의 디비 커넥션 값이 널이라 널익셉션 뜨는것.
		// 여기서는 앞의 dto 받아오는 부분에서 디비커넥션이 끊어져 이 후 부터 실행이 안됌.
		// 해결방법은 커넥션 풀링 라이브러리를 이용해서 해결하거나 쿼리문을 하나로 만들기. 아니면 프로세스 늘리기.
		// 코드의 최적화를 위해 마지막 방법은 비추.
		// 참고링크 : https://stackoverflow.com/questions/14720938/ora-12519-tnsno-appropriate-service-handler-found-while-inserting-into-oracle
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
