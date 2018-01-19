package api;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Parser;

import org.springframework.web.servlet.ModelAndView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import dto.MovieDTO;
import dto.MovieNewsDTO;

import java.io.BufferedReader;
import java.io.IOException;

//http://localhost:8090/mymovie
public class MovieApi {
	public void MovieNewsApi(ModelAndView mav){
		String clientId = "Tg0i0_m2zuXFQkFIV1_v";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "9MrgSD3oMF";// 애플리케이션 클라이언트 시크릿값";
		List<MovieNewsDTO> list = new ArrayList<MovieNewsDTO>();
		try {
			String text = URLEncoder.encode("영화", "UTF-8");
			// String apiURL = "https://openapi.naver.com/v1/search/movie.xml?query="+ text;
			// // json 결과
			String apiURL = "https://openapi.naver.com/v1/search/news.xml?display=5&query=" + text; // xml 결과
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			
			br.close();
            System.out.println(response.toString());

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser(); // 연결하는거 담고
			parser.setInput(new StringReader(response.toString()));
			int eventType = parser.getEventType();
			MovieNewsDTO dto = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
					case XmlPullParser.END_DOCUMENT:// 문서의 끝
						break;
					case XmlPullParser.START_DOCUMENT:
						break;
					case XmlPullParser.START_TAG: { // 무조건 시작하면 만남
						String tag = parser.getName();
						if(tag.equals("description")) {
							System.out.println("1");
							if(dto != null)
								dto.setDescription(parser.nextText());
						}else if(tag.equals("title")) {
							System.out.println("2");
							if(dto != null)	
								dto.setTitle(parser.nextText());
						}else if(tag.equals("item")) {
							System.out.println("3");
							dto = new MovieNewsDTO();
						}else if(tag.equals("originallink")) {
							System.out.println("4");
							if(dto != null)
								dto.setOriginallink(parser.nextText());
						}
						break;
					}
					case XmlPullParser.END_TAG: {
						String tag2 = parser.getName();
						if(tag2.equals("item")) {
							System.out.println("6");
							list.add(dto);
							dto = null;
						}
						break;
					}
				}
				eventType = parser.next();
			}
			mav.addObject("list", list);
		} catch (Exception e) {
			System.out.println("오류");
		}
	}
}
