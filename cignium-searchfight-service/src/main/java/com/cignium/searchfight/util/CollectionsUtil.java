package com.cignium.searchfight.util;

import java.util.Collections;
import java.util.List;

import com.cignium.searchfight.SearchEngineResult;

public class CollectionsUtil {

	public static void sort(List<SearchEngineResult> list) {
		Collections.sort(list, (e1, e2) -> ((Long)(e1.getCount() - e2.getCount())).intValue());
	}

}
