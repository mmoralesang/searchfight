package com.cignium.searchfight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class SearchFightResponse implements Serializable {

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

	public void refresh() {
		results.forEach(r -> r.refreshCount());
	}

}
