package com.cignium.searchfight.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cignium.searchfight.SearchEngineResult;
import com.cignium.searchfight.SearchFightResponse;
//import com.cignium.searchfight.config.AppConfig;
import com.cignium.searchfight.util.CollectionsUtil;
import com.cignium.searchfight.util.ConstantsUtil;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })*/
public class SearchFightServiceTest {
	//@Autowired
	SearchFightService service;

	@Test
	public void runGeneralTest() {
		String s = "647,000,000";

		System.out.println("s:::: " + Integer.parseInt(s.replace(",", "")));
	}

	@Test
	public void executeQueryTest() {
		// Params
		List<String> params = Arrays.asList(".net", "java", "java script", "C++");

		// Processing
		SearchFightResponse response = service.executeSearchFight(params);

		// Assessments
		List<SearchEngineResult> results = response.getResults();

		calculateRanking(results, params);

		/*
		 * Map<String, List<SearchEngineResult>> ranking = response.calculateRanking();
		 * ranking.keySet().forEach(key -> { if(!key.endsWith("_sorted")) {
		 * List<SearchEngineResult> result = ranking.get(key); result.forEach(r -> {
		 * System.out.println(r.getQ() + ": " + r.getEngine().getName() + ": " +
		 * r.getCount()); }); } });
		 */

	}

	private void calculateRanking(List<SearchEngineResult> results, List<String> params) {
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
	}

	@After
	public void finish() {
		System.gc();
	}

}
