package com.lgl.c;

import java.util.List;

import com.lgl.m.TimeCard;

public class TimeCardData {

	private static List<TimeCard> listData ;
 
	public static List<TimeCard> getListData() {
		return listData;
	}

	public static void setListData(List<TimeCard> listData) {
		TimeCardData.listData = listData;
	}
	
	
}
