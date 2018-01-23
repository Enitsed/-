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
		String clientId = "Tg0i0_m2zuXFQkFIV1_v";// �븷�뵆由ъ��씠�뀡 �겢�씪�씠�뼵�듃 �븘�씠�뵒媛�";
		String clientSecret = "9MrgSD3oMF";// �븷�뵆由ъ��씠�뀡 �겢�씪�씠�뼵�듃 �떆�겕由욧컪";
		List<MovieNewsDTO> list = new ArrayList<MovieNewsDTO>();
		try {
			String text = URLEncoder.encode("영화", "UTF-8");
			// String apiURL = "https://openapi.naver.com/v1/search/movie.xml?query="+ text;
			// // json 寃곌낵
			String apiURL = "https://openapi.naver.com/v1/search/news.xml?display=5&query=" + text; // xml 寃곌낵
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // �젙�긽 �샇異�
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // �뿉�윭 諛쒖깮
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
			XmlPullParser parser = factory.newPullParser(); // �뿰寃고븯�뒗嫄� �떞怨�
			parser.setInput(new StringReader(response.toString()));
			int eventType = parser.getEventType();
			MovieNewsDTO dto = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
					case XmlPullParser.END_DOCUMENT:// 臾몄꽌�쓽 �걹
						break;
					case XmlPullParser.START_DOCUMENT:
						break;
					case XmlPullParser.START_TAG: { // 臾댁“嫄� �떆�옉�븯硫� 留뚮궓
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
			System.out.println("�삤瑜�");
		}
	}
}
