package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class BoxOffice {

	@Async
	public List<String> boxOffice() {
		List<String> list = new ArrayList<String>();
		try {
			Calendar cal = new GregorianCalendar();
			cal.add(Calendar.DATE, -1);
			String year = Integer.toString(cal.get(Calendar.YEAR));
			String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
			String date = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
			if (Integer.parseInt(month) > 0 && Integer.parseInt(month) < 10)
				month = "0" + month;

			if (Integer.parseInt(date) > 0 && Integer.parseInt(date) < 10)
				date = "0" + date;

			StringBuilder urlBuilder = new StringBuilder(
					"http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml");
			urlBuilder.append(
					"?" + URLEncoder.encode("key", "UTF-8") + "=2d4fd092a8bf189f85641f902b861596"); /* Service Key */
			urlBuilder.append("&" + URLEncoder.encode("targetDt", "UTF-8") + "="
					+ URLEncoder.encode(year + month + date, "UTF-8")); /* 검색건수 */
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
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
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser(); // 연결하는거 담고
			parser.setInput(new StringReader(sb.toString()));
			int eventType = parser.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.END_DOCUMENT:// 문서의 끝
					break;
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG: { // 무조건 시작하면 만남
					String tag = parser.getName();
					String tagName = null;
					if (tag.equals("movieNm")) {
						tagName = parser.nextText();
						String name = tagName.replaceAll("\\p{Z}", "");
						list.add(name);
						break;
					}
				}
				case XmlPullParser.END_TAG: {

				}
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
