package com.cignium.searchfight.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cignium.searchfight.SearchEngine;

/**
 * 
 * @author Marcos
 *
 */
public class HtmlUtil {
	private static final String SEARCH_ENGINE_GOOGLE = "google";
	private static final String SEARCH_ENGINE_BING = "bing";
	private static final String SEARCH_ENGINE_YAHOO = "yahoo";

	public static String readBodyContent(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		Elements body = doc.select("body");
		return body.text();
	}

	public static Long processSearchEngine(SearchEngine engine, String q) throws IOException {
		String url = new StringBuilder(engine.getQueryEndPoint()).append(q).toString();
		
		Document doc = Jsoup.connect(url).get();
		
		String resultStr = null;
		Long numResult = null;
		
		switch (engine.getName()) {
		case SEARCH_ENGINE_GOOGLE:
			resultStr = doc.getElementById("resultStats").toString();
			numResult = getGoogleResultCount(resultStr);
			break;
		case SEARCH_ENGINE_BING:
			resultStr = doc.getElementsByClass("sb_count").get(0).text();
			numResult = getBingResultCount(resultStr);
			break;
		case SEARCH_ENGINE_YAHOO:
			resultStr = doc.getElementsByClass("compPagination").get(0).getElementsByTag("span").get(0).text();
			numResult = getYahooResultCount(resultStr);
			break;
		default:
			throw new RuntimeException("search engine \"" + engine.getName() + "\" is not implemented yet!");
		}
		
		if(resultStr == null) {
			throw new RuntimeException("Result not found!");
		}
		
		return numResult;
	}

	private static Long getYahooResultCount(String resultStr) {
		return Long.parseLong(resultStr.split(" ")[0].replace(",", ""));
	}

	private static Long getBingResultCount(String resultStr) {
		return Long.parseLong(resultStr.split(" ")[0].replace(".", ""));
	}

	private static Long getGoogleResultCount(String resultStr) {
		String s = resultStr.split("<div id=\"resultStats\">")[1].split("<")[0].replaceAll("[^\\d]", "");
		
		return Long.parseLong(s);
		//return Integer.parseInt(resultStr.split("<div id=\"resultStats\">")[1].split("<")[0].replaceAll("[^\\d]", ""));
	}

}
