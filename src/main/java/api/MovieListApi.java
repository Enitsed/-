package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import api.MovieDAO;
import dto.ActorDTO;
import dto.MovieDTO;
import dto.CategoryDTO;
import dto.DirectorDTO;

public class MovieListApi {

	public void insert() {
		List<MovieDTO> list = new ArrayList();
		int endCheck = 1;
		// for (int i = 0 ; i < 800 ; i += 100) {
		try {
			StringBuilder urlBuilder = new StringBuilder(
					"http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_xml.jsp"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
					+ "=3B5A8771DFD82ACE945DE21EFF5C23A5E655FADCDF33A75F362C2F26EC8BC6"); /* Service Key */
			urlBuilder.append(
					"&" + URLEncoder.encode("listCount", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /* 검색건수 */
			urlBuilder.append("&" + URLEncoder.encode("startCount", "UTF-8") + "="
					+ URLEncoder.encode("0", "UTF-8")); /* 페이지 번호 */
			urlBuilder.append("&" + URLEncoder.encode("use", "UTF-8") + "=" + URLEncoder.encode("극장용", "UTF-8"));
			urlBuilder.append(
					"&" + URLEncoder.encode("collection", "UTF-8") + "=" + URLEncoder.encode("kmdb_new", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("detail", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			System.out.println(sb.toString());
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser(); // 연결하는거 담고
			parser.setInput(new StringReader(sb.toString()));
			int eventType = parser.getEventType();
			MovieDTO movieDto = null;
			ActorDTO actorDto = null;
			CategoryDTO categoryDto = null;
			DirectorDTO directorDto = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.END_DOCUMENT:// 문서의 끝
					break;
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG: { // 무조건 시작하면 만남
					String tag = parser.getName();
					String tagName = null;
					if (tag.equals("title")) {
						if (movieDto != null) {
							tagName = parser.nextText();
							String name = tagName.replaceAll("\\p{Z}", "");

							if (name.equals("")) {
								movieDto.setMovie_kor_title("제목 없음");
							} else {
								movieDto.setMovie_kor_title(name);
							}
						}
					} else if (tag.equals("Row")) {
						movieDto = new MovieDTO();
						actorDto = new ActorDTO();
						categoryDto = new CategoryDTO();
						directorDto = new DirectorDTO();
					} else if (tag.equals("posters")) {
						if (movieDto != null) {
							tagName = parser.nextText();
							String name = tagName.replaceAll("\\p{Z}", "");
							if (name.equals("")) {
								movieDto.setMovie_image("이미지 없음");
							} else {
								movieDto.setMovie_image(name);
							}
						}
					} else if (tag.equals("directorNm")) {
						tagName = parser.nextText();
						if (directorDto != null) {
							if (tagName == null) {
							} else {
								directorDto.setDirector_name(tagName);
								movieDto.addMovie_director(directorDto);
								directorDto = new DirectorDTO();
							}
						}
					} else if (tag.equals("plot")) {
						tagName = parser.nextText();
						if (movieDto != null) {
							if (tagName == null) {
								movieDto.setMovie_summary("줄거리 정보 없음");
							} else {
								movieDto.setMovie_summary(tagName);
							}
						}
					} else if (tag.equals("ratingGrade")) {
						if (movieDto != null) {
							tagName = parser.nextText();
							String name = tagName.replaceAll("\\p{Z}", "");
							if (name.equals("")) {
								movieDto.setMovie_rating("등급 정보 없음");
							} else {
								movieDto.setMovie_rating(name);
							}
						}
					} else if (tag.equals("genre")) {
						if (categoryDto != null) {
							tagName = parser.nextText();
							String name = tagName.replaceAll("\\p{Z}", "");
							if (name.equals("")) {

							} else {
								String str[] = name.split(",");
								for (int j = 0; j < str.length; j++) {
									categoryDto.setCategory_name(str[j]);
									movieDto.addCategory(categoryDto);
									categoryDto = new CategoryDTO();
								}
							}
						}
					} else if (tag.equals("actorNm")) {
						tagName = parser.nextText();
						if (actorDto != null) {
							if (tagName == null) {

							} else {
								String str[] = tagName.split(",");
								for (int j = 0; j < str.length; j++) {
									actorDto.setActor_name(tagName);
									movieDto.addMovie_actor(actorDto);
									actorDto = new ActorDTO();
								}
							}
						}
					} else if (tag.equals("titleEng")) {
						if (movieDto != null) {
							tagName = parser.nextText();
							String name = tagName.replaceAll("\\p{Z}", "");
							if (name.equals("")) {
								movieDto.setMovie_eng_title("영어 제목 없음");
							} else {
								movieDto.setMovie_eng_title(name);
							}
						}
					} else if (tag.equals("vodUrl")) {
						tagName = parser.nextText();
						String name = tagName.replaceAll("\\p{Z}", "");

						if (movieDto != null) {
							if (name.equals("")) {
								movieDto.setMovie_url("정보 없음");
							} else {
								movieDto.setMovie_url(name);
							}
						}
					} else if (tag.equals("nation")) {
						tagName = parser.nextText();
						String name = tagName.replaceAll("\\p{Z}", "");
						if (movieDto != null) {
							if (name.equals("")) {
								movieDto.setNation("정보 없음");
							} else {
								movieDto.setNation(tagName);
							}
						}
					} else if (tag.equals("repRlsDate")) {
						tagName = parser.nextText();
						tagName = tagName.replaceAll("\\p{Z}", "");
						if (movieDto != null) {
							if (tagName.equals("")) {

								movieDto.setMovie_opening_date(null);
							} else {
								SimpleDateFormat toFormat = new SimpleDateFormat("yyyyMMdd");
								Date fromDate = toFormat.parse(tagName);
								movieDto.setMovie_opening_date(fromDate);
							}
						}
					} else if (tag.equals("Result")) {
						endCheck = Integer.parseInt(parser.getAttributeValue(2));
					}

					break;
				}
				case XmlPullParser.END_TAG: {
					String tag2 = parser.getName();
					if (tag2.equals("Row")) {
						list.add(movieDto);
						movieDto = null;
						actorDto = null;
						categoryDto = null;
						directorDto = null;
					}
					break;
				}
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		MovieDAO dao = MovieDAO.getInstance();
		dao.insertMethod(list);
		dao.countActor(list);
		dao.insertMovieActorMethod(list);
		dao.countDirector(list);
		dao.insertMovieDirectorMethod(list);
		dao.insertMovieCategoryMethod(list);
		// }

	}
}
