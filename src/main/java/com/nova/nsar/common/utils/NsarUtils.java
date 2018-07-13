package com.nova.nsar.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

public class NsarUtils {
	
	public static Date formatToDate(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
		 return null;
		}
	}
	
	public static Date formatToDate(String date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
		 return null;
		}
	}
	
	public static String formatToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static HashMap<Date, String> dateToDay(TreeSet<Date> set) {
		HashMap<Date, String> map = new HashMap<Date, String>();
		System.out.println("First : " + set.first() + " Last : " + set.last());
		int val = set.first().compareTo(set.last());

		if (val < 1) {
			map.put(set.first(), StringConstants.YESTERDAY);
			map.put(set.last(), StringConstants.TODAY);
		}

		return map;
	}
	
	public static HashMap<String, String> kpiUnit(String network) {
		HashMap<String, String> network2G = new HashMap<String, String>();
		HashMap<String, String> network3G = new HashMap<String, String>();
		
		network2G.put("SD Drop", "%");
		network2G.put("TCH Drop", "%");
		network2G.put("HOSR", "%");
		network2G.put("SD Mean Holding Time", "Seconds");
		network2G.put("GPRS Availability", "%");
		network2G.put("TCH Erlang Traffic", "Erlang");
		network2G.put("EGPRS Throughput DL (DLBGEGDATA)", "MB");
		network2G.put("EGPRS Throughput UL (ULBGEGDATA)", "MB");
		network2G.put("TCH Assignment Success Rate", "MB");
		network2G.put("CLMAX for AMRFRMAXTRAFFIC post activity", "License");
		network2G.put("CLMAX for AMRHRMAXTRAFFIC post activity", "License");
		network2G.put("CLMAX for EGPRSBPCLIMIT post activity", "License");

		network3G.put("AvgSpeechErlang", "Erlang");
		network3G.put("PS Traffic", "GB");
		network3G.put("HsTputAvg", "Mbps");
		network3G.put("PSAccess", "%");
		network3G.put("PSCCSR", "%");
		network3G.put("PSDrop", "%");
		network3G.put("PSRabSucc", "%");
		network3G.put("RrcSuc", "%");
		network3G.put("SpchAccess", "%");
		network3G.put("SpchCCSR", "%");
		network3G.put("SpchDrop", "%");
		network3G.put("SpchRabSuc", "%");
		network3G.put("CSIuSigSuc", "%");
		network3G.put("PSIuSigSuc", "%");
		network3G.put("SpchRrcSucRate", "%");
		network3G.put("PSRrcSucRate", "%");
		
		if(network.equalsIgnoreCase("2G")){
			return network2G;
		}
		if(network.equalsIgnoreCase("3G")){
			return network3G;
		}else{
			return null;
		}
	}
	
}
