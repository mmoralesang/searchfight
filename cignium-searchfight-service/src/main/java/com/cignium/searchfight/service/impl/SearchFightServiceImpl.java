/**
 * 
 */
package com.cignium.searchfight.service.impl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.cignium.searchfight.SearchFightResponse;
import com.cignium.searchfight.service.SearchFightService;
import com.cignium.searchfight.util.ConstantsUtil;
import com.cignium.searchfight.util.ProcessUtil;

/**
 * @author Marcos
 *
 */
//@Service
public class SearchFightServiceImpl implements SearchFightService {
	
	/*
	 * (non-Javadoc)
	 * @see com.cignium.searchfight.service.SearchFightService#executeSearchFight(java.util.List)
	 */
	@Override
	public SearchFightResponse executeSearchFight(List<String> params) {
		SearchFightResponse response = new SearchFightResponse();
		
		ExecutorService taskExecutor = Executors.newFixedThreadPool(ConstantsUtil.searchEngines.size()); 
		ConstantsUtil.searchEngines.forEach(engine -> {
			params.forEach(q -> {
				Callable<Long> task = new ProcessUtil.CallableProcessSearchEngine(engine, q);
				Future<Long> result = taskExecutor.submit(task);

				response.addResult(engine, q, result);
			});
		});
		
		taskExecutor.shutdown();
		while (!taskExecutor.isTerminated()) {}
		
		response.refresh();
		//response.calculateRanking();
		
		return response;
	}

}
