package com.cignium.searchfight.service;

import java.util.List;

import com.cignium.searchfight.SearchFightResponse;

public interface SearchFightService {

	/**
	 * 
	 * @param params
	 * @return
	 */
	SearchFightResponse executeSearchFight(List<String> params);

}
