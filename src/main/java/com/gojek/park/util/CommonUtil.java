package com.gojek.park.util;

public class CommonUtil {
	
	public static Integer getInteger(String input) {
		try{
			return Integer.parseInt(input);
		} catch (Exception e) {
			return null;
		}
	}

}
