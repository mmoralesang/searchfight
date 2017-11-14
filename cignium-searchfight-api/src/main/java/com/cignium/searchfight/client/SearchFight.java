package com.cignium.searchfight.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.cignium.searchfight.SearchEngineResult;
import com.cignium.searchfight.SearchFightResponse;
import com.cignium.searchfight.service.SearchFightService;
import com.cignium.searchfight.service.impl.SearchFightServiceImpl;
import com.cignium.searchfight.util.CollectionsUtil;
import com.cignium.searchfight.util.ConstantsUtil;

public class SearchFight {
	private SearchFightService service;
	
	public SearchFight() {
		service = new SearchFightServiceImpl();
	}


	public static void main(String[] args) {
		List<String> params = Arrays.asList(args);
		
		SearchFight app = new SearchFight();
		app.runSearchFight(params);
    }

	private void runSearchFight(List<String> params) {
		// Processing
		SearchFightResponse response = service.executeSearchFight(params);

		// Read results
		List<SearchEngineResult> results = response.getResults();

		// ranking
		printRanking(results, params);
	}


	@SuppressWarnings("unchecked")
	private void printRanking(List<SearchEngineResult> results, List<String> params) {
		// Print result by param
		params.forEach(p -> {
			StringBuilder strMessage = new StringBuilder(p);
			strMessage.append(": ");
			results.stream().filter(r -> r.getQ().equals(p)).forEach(i -> {
				strMessage.append(i.getEngine().getName());
				strMessage.append(": ");
				strMessage.append(i.getCount());
				strMessage.append(" ");
			});
			
			System.out.println(strMessage);
		});
		
		// Print result by engine
		ConstantsUtil.searchEngines.forEach(e -> {
			List<SearchEngineResult> list = results.stream().filter(item -> item.getEngine().getName().equals(e.getName())).collect(Collectors.toList());
			List<SearchEngineResult> sortedList = (List<SearchEngineResult>)((ArrayList<SearchEngineResult>)list).clone();
			CollectionsUtil.sort(sortedList);
			
			SearchEngineResult greater = sortedList.get(sortedList.size() - 1);
			
			StringBuilder sb = new StringBuilder(greater.getEngine().getName());
			sb.append(" ");
			sb.append("winner: ");
			sb.append(greater.getQ());
			System.out.println(sb);
		});
		
		// Print total winner
		CollectionsUtil.sort(results);
		SearchEngineResult total = results.get(results.size() - 1);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Total winner: ");
		sb.append(total.getQ());
		System.out.println(sb);
	}
	
}
