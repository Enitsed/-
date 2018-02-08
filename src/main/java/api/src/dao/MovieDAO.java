package api.src.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import api.src.dto.CategoryDTO;
import api.src.dto.DirectorDTO;
import api.src.dto.ActorDTO;
import api.src.dto.MovieDTO;

public class MovieDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 싱글톤 패턴
	static MovieDAO dao = new MovieDAO();

	private MovieDAO() {

	}

	public static MovieDAO getInstance() {
		return dao;
	}

	private Connection init() throws ClassNotFoundException, SQLException {
		// 1. 드라이버 로딩
		Class.forName("oracle.jdbc.OracleDriver");

		// 2. 서버연결
		String url = "jdbc:oracle:thin://@127.0.0.1:1521:xe";
		String username = "hr";
		String password = "a1234";
		return DriverManager.getConnection(url, username, password);
	}// end init()

	private void exit() throws SQLException {
		if (rs != null)
			rs.close();

		if (stmt != null)
			stmt.close();

		if (pstmt != null)
			pstmt.close();

		if (conn != null)
			conn.close();

	}// end exit()

	public void insertMethod(List<MovieDTO> list) {
		try {
			conn = init();
			String sql = "INSERT INTO movie VALUES(movie_seq.nextval,?,?,?,?,?,?,?,?)"; // ?순서대로 인덱스 1부터 시작
			for (int i = 0; i < list.size(); i++) {
				pstmt = conn.prepareStatement(sql); // prepareStatement: statement값 가져올때
				pstmt.setString(1, list.get(i).getMovie_rating());
				pstmt.setString(2, list.get(i).getMovie_kor_title());// (?값,이름)
				pstmt.setString(3, list.get(i).getMovie_eng_title());
				Date date = new Date(list.get(i).getMovie_opening_date().getYear(),
						list.get(i).getMovie_opening_date().getMonth(), list.get(i).getMovie_opening_date().getDate());

				pstmt.setDate(4, date);
				pstmt.setString(5, list.get(i).getMovie_summary());
				pstmt.setString(6, list.get(i).getMovie_image());
				pstmt.setString(7, list.get(i).getMovie_url());
				pstmt.setString(8, list.get(i).getNation());

				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void countActor(List<MovieDTO> Movielist) {
		try {
			conn = init();
			stmt = conn.createStatement();
			int count = 0;
			String sql = "";
			for (int i = 0; i < Movielist.size(); i++) {
				List<ActorDTO> list = Movielist.get(i).getMovie_actor();
				for (int j = 0; j < list.size(); j++) {
					String name = list.get(j).getActor_name().replaceAll("\\p{Z}", "");
					if (!name.equals("")) {
						sql = "select count(*) from actor where actor_name = '" + name + "'";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							count = rs.getInt("count(*)");
							if (count <= 0) {
								String sql2 = "INSERT INTO actor VALUES(actor_seq.nextval,'" + name + "')";
								stmt.executeUpdate(sql2);
							}
							count = 0;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertMovieActorMethod(List<MovieDTO> Movielist) {
		try {
			conn = init();
			stmt = conn.createStatement();

			for (int i = 0; i < Movielist.size(); i++) {
				List<ActorDTO> list = Movielist.get(i).getMovie_actor();
				for (int j = 0; j < list.size(); j++) {
					String name = list.get(j).getActor_name().replaceAll("\\p{Z}", "");
					if (!name.equals("")) {
						String sql = "INSERT INTO movie_actor (select movie_num, actor_num from actor, movie where actor_name = '"
								+ name + "' AND movie_kor_title = '" + Movielist.get(i).getMovie_kor_title() + "')"; // ?순서대로
																														// 인덱스
																														// //
						stmt.executeUpdate(sql);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void countDirector(List<MovieDTO> Movielist) {
		try {
			conn = init();
			stmt = conn.createStatement();
			int count = 0;
			for (int i = 0; i < Movielist.size(); i++) {
				List<DirectorDTO> list = Movielist.get(i).getMovie_director();
				for (int j = 0; j < list.size(); j++) {
					String name = list.get(j).getDirector_name().replaceAll("\\p{Z}", "");
					if (!name.equals("")) {
						String sql = "select count(*) from director where director_name = '" + name + "'"; // ?순서대로

						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							count = rs.getInt("count(*)");
							if (count <= 0) {
								String sql2 = "INSERT INTO director VALUES(director_seq.nextval,'" + name + "')"; // ?순서대로
																													// 인덱스
								stmt.executeUpdate(sql2);
							}
							count = 0;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertMovieDirectorMethod(List<MovieDTO> Movielist) {
		try {
			conn = init();
			stmt = conn.createStatement();

			for (int i = 0; i < Movielist.size(); i++) {
				List<DirectorDTO> list = Movielist.get(i).getMovie_director();
				for (int j = 0; j < list.size(); j++) {
					String name = list.get(j).getDirector_name().replaceAll("\\p{Z}", "");
					if (!name.equals("")) {
						String sql = "INSERT INTO movie_director (select movie_num, director_num from director, movie where director_name = '"
								+ name + "' AND movie_kor_title = '" + Movielist.get(i).getMovie_kor_title() + "')";
						stmt.executeUpdate(sql);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertMovieCategoryMethod(List<MovieDTO> Movielist) {
		try {
			conn = init();
			stmt = conn.createStatement();

			for (int i = 0; i < Movielist.size(); i++) {
				List<CategoryDTO> list = Movielist.get(i).getCategory();
				for (int j = 0; j < list.size(); j++) {
					String name = list.get(j).getCategory_name().replaceAll("\\p{Z}", "");
					if (!name.equals("")) {

						String sql = "INSERT INTO movie_category (select category_num, movie_num from category, movie where category_name = '"
								+ name + "' AND movie_kor_title = '" + Movielist.get(i).getMovie_kor_title() + "')";

						stmt.executeUpdate(sql);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}// end class
