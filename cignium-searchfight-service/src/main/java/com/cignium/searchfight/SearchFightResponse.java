package com.cignium.searchfight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.cignium.searchfight.util.CollectionsUtil;
import com.cignium.searchfight.util.ConstantsUtil;

public class SearchFightResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7948091217787940637L;
	
	private List<SearchEngineResult> results = new ArrayList<>();
	
	public void addResult(SearchEngine engine, String q, Future<Long> count) {
		results.add(new SearchEngineResult(engine, q, count));
	}

	public List<SearchEngineResult> getResults() {
		return results;
	}

	@SuppressWarnings({"unchecked"})
	public Map<String, List<SearchEngineResult>> calculateRanking() {
		Map<String, List<SearchEngineResult>> ranking = new HashMap<>();
		
		ConstantsUtil.searchEngines.forEach(e -> {
			List<SearchEngineResult> list = results.stream().filter(item -> item.getEngine().getName().equals(e.getName())).collect(Collectors.toList());
			List<SearchEngineResult> sortedList = (List<SearchEngineResult>)((ArrayList<SearchEngineResult>)list).clone();
			CollectionsUtil.sort(sortedList);
			
			ranking.put(e.getName(), list);
			ranking.put(e.getName() + "_sorted", sortedList);
			
			System.out.println(e.getName() + ": (first)=" + sortedList.get(0).toString() + " (last)=" + sortedList.get(sortedList.size() - 1).toString());
		});
		
		/*List<SearchEngineResult> googleResults = results.stream().filter(item -> item.getEngine().getName().equals("google")).collect(Collectors.toList());
		List<SearchEngineResult> bingResults = results.stream().filter(item -> item.getEngine().getName().equals("bing")).collect(Collectors.toList());
		List<SearchEngineResult> yahooResults = results.stream().filter(item -> item.getEngine().getName().equals("yahoo")).collect(Collectors.toList());
		
		List<SearchEngineResult> googleResultsClone = (List<SearchEngineResult>) ((ArrayList<SearchEngineResult>)googleResults).clone();
		List<SearchEngineResult> bingResultsClone = (List<SearchEngineResult>) ((ArrayList<SearchEngineResult>)bingResults).clone();
		List<SearchEngineResult> yahooResultsClone = (List<SearchEngineResult>) ((ArrayList<SearchEngineResult>)yahooResults).clone();
		
		CollectionsUtil.sort(googleResultsClone);
		CollectionsUtil.sort(bingResultsClone);
		CollectionsUtil.sort(yahooResultsClone);
		
		System.out.println("googleResults: (first)=" + googleResultsClone.get(0).toString() + " (last)=" + googleResultsClone.get(googleResultsClone.size() - 1).toString());
		System.out.println("bingResults: (first)=" + bingResultsClone.get(0).toString() + " (last)=" + bingResultsClone.get(bingResultsClone.size() - 1).toString());
		System.out.println("yahooResults: (first)=" + yahooResultsClone.get(0).toString() + " (last)=" + yahooResultsClone.get(yahooResultsClone.size() - 1).toString()); */
		
		return ranking;
	}

	public void refresh() {
		results.forEach(r -> r.refreshCount());
	}
	
}
