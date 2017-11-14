package com.cignium.searchfight.util;

import java.io.IOException;
import java.util.concurrent.Callable;

import com.cignium.searchfight.SearchEngine;

public class ProcessUtil {
	
	private static Long processSearchEngine(SearchEngine engine, String q) {
		try {
			return HtmlUtil.processSearchEngine(engine, q);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	
	public static class CallableProcessSearchEngine implements Callable<Long> {
		private SearchEngine engine;
		private String q;
		
		public CallableProcessSearchEngine(SearchEngine engine, String q) {
			this.engine = engine;
			this.q = q;
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

		@Override
		public Long call() throws Exception {
			return processSearchEngine(engine, q);
		}

	}
}
