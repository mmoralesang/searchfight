package com.cignium.searchfight;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SearchEngineResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7576851531029133341L;

	private SearchEngine engine;
	private String q;
	private Future<Long> future;
	private Long count;

	public SearchEngineResult(SearchEngine engine, String q, Future<Long> future) {
		this.engine = engine;
		this.q = q;
		this.future = future;
	}

	public SearchEngine getEngine() {
		return engine;
	}

	public void setEngine(SearchEngine engine) {
		this.engine = engine;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public void refreshCount() {
		try {
			this.count = future.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return "[engine=" + engine + ", q=" + q + ", count=" + count + "]";
	}

}
