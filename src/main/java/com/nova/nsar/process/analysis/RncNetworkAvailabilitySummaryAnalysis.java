package com.nova.nsar.process.analysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RncNetworkAvailabilitySummaryAnalysis {

	public Map<String, Map<String, String>> parseString(String outputLog){
		Scanner scanner = new Scanner(outputLog);
		Map<String, Map<String, String>> cellSiteAvailableCount = new HashMap<>();
		int upCellCount = 0;
		int downCellCount = 0;
		int totalCellCount = 0;
		int upSiteCount = 0;
		int downSiteCount = 0;
		int totalSiteCount = 0;
		Map<String, String> cellSiteCountMap = new HashMap<>();
outer:	while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			while (!line.startsWith("Cell availability:")) {
				if(scanner.hasNextLine()){
					line = scanner.nextLine();
				}
				else
					break outer;
			}
			if(line.startsWith("Cell availability:")){
				cellSiteCountMap = new HashMap<>();
	    		String[] cellCountArr = line.split(":");
	    		String[] countArr = cellCountArr[1].trim().split("\\s+");
	    		upCellCount = Integer.parseInt(countArr[0]);
	    		totalCellCount = Integer.parseInt(countArr[2]);
	    		downCellCount = totalCellCount - upCellCount;
	    		
	    		cellSiteCountMap.put("Cell", upCellCount+"~"+downCellCount+"~"+totalCellCount);
	    	}
			line = scanner.nextLine();
	    	if(line.startsWith("Site availability:")){
	    		String[] siteCountArr = line.split(":");
	    		String[] countArr = siteCountArr[1].trim().split("\\s+");
	    		upSiteCount = Integer.parseInt(countArr[0]);
	    		totalSiteCount = Integer.parseInt(countArr[2]);
	    		downSiteCount = totalSiteCount - upSiteCount;
	    		
	    		cellSiteCountMap.put("Site", upSiteCount+"~"+downSiteCount+"~"+totalSiteCount);
	    		
	    		cellSiteAvailableCount.put("MDERNC01", cellSiteCountMap);
	    		
	    		break;
	    	}
		}
	    //System.out.println(cellSiteAvailableCount);
	    scanner.close();
	    return cellSiteAvailableCount;
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
}
