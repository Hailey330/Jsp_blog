package com.cos.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
	
	public static String getContentYoutube(String content) {
		Document doc = Jsoup.parse(content);
		Elements aTags = doc.select("a");
		// https://youtu.be/1djtHemxECk
		// https://www.youtube.com/watch?v=4cHr6uwDP2s
		for (Element aTag : aTags) {
			String href = aTag.attr("href");
			String youtubeId = null;
			if(href != null && !aTag.attr("target").equals("_blank")) {
				if(href.contains("https://youtu.be")) {
					String[] hrefArr = href.split("/");
					youtubeId = hrefArr[3];
				} else if (href.contains("https://www.youtube.com")) {
					String[] hrefArr = href.split("=");
					youtubeId = hrefArr[1];
				}
				
				System.out.println("Jsoup 파싱 : YOUTUBE : " + youtubeId);
				String video = "<br/><iframe src='https://www.youtube.com/embed/"+youtubeId+"' width='450' height='400' frameborder='0' allowfullscreen></iframe>";
				System.out.println("video : " + video);
				aTag.after(video);
			}
		}
		return doc.toString();
	}
	
	public static String getContentPreview(String content) {
		Document doc = Jsoup.parse(content);
		Elements pTags = doc.select("p");
		
		for (Element pTag : pTags) {
			String text = pTag.text();
			if(text.length() > 0) {
				if(text.length() < 11) {
					return pTag.text();
				} else {
					return pTag.text().substring(0, 10) + "...";
				}
			}
		}
		return "내용 없음...";
	}
}
