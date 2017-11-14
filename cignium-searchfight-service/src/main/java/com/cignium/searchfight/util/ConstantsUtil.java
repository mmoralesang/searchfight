package com.cignium.searchfight.util;

import java.util.ArrayList;
import java.util.List;

import com.cignium.searchfight.SearchEngine;

public class ConstantsUtil {
	public static final List<SearchEngine> searchEngines = new ArrayList<>();
	
	static {
		SearchEngine google = new SearchEngine();
		google.setName("google");
		google.setEndPoint("https://www.google.com");
		google.setQueryEndPoint("https://www.google.com/search?q=");
		searchEngines.add(google);
		
		SearchEngine bing = new SearchEngine();
		bing.setName("bing");
		bing.setEndPoint("https://www.bing.com");
		bing.setQueryEndPoint("https://www.bing.com/search?q=");
		searchEngines.add(bing);
		
		SearchEngine yahoo = new SearchEngine();
		yahoo.setName("yahoo");
		yahoo.setEndPoint("https://search.yahoo.com");
		yahoo.setQueryEndPoint("https://search.yahoo.com/search?p=");
		searchEngines.add(yahoo);
	}

}
