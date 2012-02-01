package com.xmleditor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListUtils {
	
	public static <E> List<E> getListFromMap(Map<String,E> m){
		List<E> list = new ArrayList<E>();
		for(Object key : m.keySet()){
			list.add(m.get(key));
		}
		
		return list;
	}

}
