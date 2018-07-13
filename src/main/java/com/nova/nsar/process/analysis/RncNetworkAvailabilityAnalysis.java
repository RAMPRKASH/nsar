package com.nova.nsar.process.analysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class RncNetworkAvailabilityAnalysis {

	public Map<String, Map<String, Map<String, String>>> parseString(String outputLog) {
		Scanner scanner = new Scanner(outputLog);
		Map<String, Map<String, Map<String, String>>> rncSiteMap = new LinkedHashMap<>();
		Map<String, Map<String, String>> siteCellMap = new LinkedHashMap<>();
		Map<String, String> cellSectorMap = new LinkedHashMap<>();
outer:	while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			while (!line.startsWith("------------")) {
				if(scanner.hasNextLine()){
					line = scanner.nextLine();
				}
				else
					break outer;
			}
			if (line.startsWith("------------")) {
				line = scanner.nextLine();
				line = scanner.nextLine();
				line = scanner.nextLine();
			}
			while(!line.startsWith("------------") && !line.equalsIgnoreCase("")){
	    		cellSectorMap = new LinkedHashMap<>();
	    		String[] lineValues = line.split("\\s+");
	    		if(lineValues.length>5){
	    			String[] cellNameArr = lineValues[2].split("-");
	    			//System.out.println(line);
	    			if(cellNameArr.length>3){
	    				String sectorNames = cellNameArr[3];
	    				String[] sectorArr = sectorNames.split("/");
	    				StringBuffer sectorValues = new StringBuffer("");
	    				int i = 3;
	    				for(String sector:sectorArr){
	    					sectorValues.append(sector+"-"+checkSectorUpDownStatus(lineValues[i++])+",");
	    				}
	    				
	    				cellSectorMap.put(cellNameArr[0]+"-"+cellNameArr[1]+"-"+cellNameArr[2], sectorValues.toString().substring(0,sectorValues.length()-1));
	    				siteCellMap.put(lineValues[1], cellSectorMap);
	    			}
	    		}
	    		line = scanner.nextLine();
	    	}
	    }
	    rncSiteMap.put("MDERNC01", siteCellMap);
	    //System.out.println(rncSiteMap);
	    scanner.close();
	    return rncSiteMap;
	}

	public String getStringFromFile(String fileName) {

		BufferedReader br = null;
		String line;
		StringBuilder sb = new StringBuilder("");
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return sb.toString();
	}
	
	private String checkSectorUpDownStatus(String sectorValue) {
		if(sectorValue.equalsIgnoreCase("000000")){
			return "0";
		}
		else if(sectorValue.equalsIgnoreCase("111111")){
			return "1";
		}
		else{
			return "2";
		}
	}
}
