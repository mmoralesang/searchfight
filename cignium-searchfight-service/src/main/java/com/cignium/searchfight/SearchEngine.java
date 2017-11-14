package com.cignium.searchfight;

import java.io.Serializable;

public class SearchEngine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7749356040128496255L;

	private String name;
	private String endPoint;
	private String queryEndPoint;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getQueryEndPoint() {
		return queryEndPoint;
	}

	public void setQueryEndPoint(String queryEndPoint) {
		this.queryEndPoint = queryEndPoint;
	}

	public void search(String q) {
		// TODO Auto-generated method stub
	}

}
