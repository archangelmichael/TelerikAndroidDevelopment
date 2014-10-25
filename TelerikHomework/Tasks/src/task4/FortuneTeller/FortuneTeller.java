package task4.FortuneTeller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

public class FortuneTeller {
	private static String sign = "libra";
	private static String siteUrl = "http://widgets.fabulously40.com/horoscope.json?sign=";
	private static String rssFeed = "http://my.horoscope.com/astrology/daily-horoscopes-rss.html";
	private static BufferedReader reader = null;
	private static RSSFeedParser parser = null;
	
	public static void main(String[] args) {
        try {
            URL url = new URL(siteUrl + sign);
        	getHoroscope(url);
        	
        	parser = new RSSFeedParser(rssFeed);
            getHroscopeFromFeed(parser);
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void getHroscopeFromFeed(RSSFeedParser parser) {
		Feed feed = parser.readFeed();
        //System.out.println(feed);
		ArrayList<FeedMessage> entries = (ArrayList<FeedMessage>)feed.getMessages();
		int index = getSignValue(sign);
		FeedMessage message = entries.get(index);
		System.out.println("horoscope2: " + message.getDescription());
	}

	private static int getSignValue(String signValue) {
		switch (signValue) {
		case "aries":
			return (int)0;
		case "taurus":
			return (int)1;
		case "gemini":
			return (int)2;
		case "cancer":
			return (int)3;
		case "leo":
			return (int)4;
		case "virgo":
			return (int)5;
		case "libra":
			return (int)6;
		case "scorpio":
			return (int)7;
		case "sagittarius":
			return (int)8;
		case "capricorn":
			return (int)9;
		case "aquarius":
			return (int)10;
		case "pisces":
			return (int)11;
		default:
			return (int)0;
		}
	}

	private static void getHoroscope(URL url) {
		try {
            URLConnection conn = url.openConnection();
            
            // Get the response
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            Map info = parseJSON(sb.toString());
            System.out.println("sign: " + ((Map)info.get("horoscope")).get("sign"));
            System.out.println("horoscope1: " + ((Map)info.get("horoscope")).get("horoscope"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                	reader.close();
                } catch (IOException e) {
                }
            }
        }
	}
	
	private static Map parseJSON (String json) {
		JsonParserFactory factory=JsonParserFactory.getInstance();
		JSONParser parser=factory.newJsonParser();
		Map jsonMap=parser.parseJson(json);
		return jsonMap;
	}
	
}
