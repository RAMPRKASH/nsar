package com.nova.nsar.process.analysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RncKpiDataAnalysis {

	public Map<String, Map<String, Map<String, String>>> createCsvFromTxt(String outputLog, String rncName){
		/**************************************************************************
		 * Reading from the buffer log and writing the data in the csv file --STARTS
		 **************************************************************************/
		Scanner scanner = new Scanner(outputLog);
		List<String> kpiList = getKpiList();
		Date date = new Date();
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		String today =dateFormat1.format(date);
		Map<String, Map<String, Map<String, String>>> valueMap = new LinkedHashMap<>();
		Map<String, String> counterValueMap = new LinkedHashMap<>();
		Map<String, Map<String, String>> rncCounterMap = new LinkedHashMap<>(); 
		try{			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] str;
				boolean datePresent = false;
				if(line.contains("No xml files to parse")){
					System.out.println("No data fetched for the provided time interval.");
					break;
				}
				else if(line.contains("Report from")){
					str = line.split(" ");
					today = str[2]+"~"+str[3];
				}
				else if(line.contains("Node SW:")){
					while (scanner.hasNextLine()) {
						line = scanner.nextLine();
						if(line.startsWith("Object")){
							line = "Date " + line;
							line = line.replace("Object", "");
							break;
						}
					}
					rncCounterMap = new LinkedHashMap<>();
					counterValueMap = new LinkedHashMap<>();
					while(scanner.hasNextLine() && !(line.contains("pmr"))){
						line = scanner.nextLine();
						if(!(line.contains("pmxh"))){
							String[] lineValues = line.trim().split("\\s+");
							if(kpiList.contains(lineValues[0])){
								if(!datePresent){
									counterValueMap.put(lineValues[0], lineValues[1]);
								}
								else{
									counterValueMap.put(lineValues[1], lineValues[2]);
								}
							}
						}
					}
					rncCounterMap.put(rncName, counterValueMap);
					valueMap.put(today, rncCounterMap);
				}
			}
			//System.out.println(valueMap);
		}
		
		catch(Exception ex){
			ex.printStackTrace();
			//System.out.println(ex);
		}
		finally{
			try{
				scanner.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		/***********************************************************************
		 * Reading from the log file and writing the data in the csv file --ENDS
		 ***********************************************************************/
		return valueMap;
	}
		
	//Fetch KPI names to be analyzed and return the list of KPI
	
	public List<String> getKpiList(){
		List<String> kpiList = new ArrayList<String>();
		kpiList.add("AvgSpeechErlang");
		kpiList.add("PSAccess");
		kpiList.add("PSCCSR");
		kpiList.add("PSDrop");
		kpiList.add("PSRabSucc");
		kpiList.add("RrcSuc");
		kpiList.add("SpchAccess");
		kpiList.add("SpchCCSR");
		kpiList.add("SpchDrop");
		kpiList.add("SpchRabSuc");
		kpiList.add("CSIuSigSuc");
		kpiList.add("PSIuSigSuc");
		kpiList.add("SpchRrcSucRate");
		kpiList.add("PSRrcSucRate");
		kpiList.add("HsTputAvg");
		return kpiList;
	}
	
	public Map<String, Map<String, Map<String, String>>> createPsTrafficCsvFromTxt(String outputLog, String rncName){
		/**************************************************************************
		 * Reading from the log file and writing the data in the csv file --STARTS
		 **************************************************************************/
		Scanner scanner = new Scanner(outputLog);
		Date date = new Date();
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		String today =dateFormat1.format(date);
		Map<String, Map<String, Map<String, String>>> psTrafficMap = new LinkedHashMap<>();
	    Map<String, Map<String, String>> rncCounterMap = new LinkedHashMap<>();
	    Map<String, String> counterValueMap = new LinkedHashMap<>();
		try{		
			String line;
			String line1;
			String[] str;
			String[] lineSplit;
			List<String> valueList= new ArrayList<String>();
			BigInteger bi1, bi2, bi3;
			bi1 = new BigInteger("1024");
			bi2 = bi1.multiply(new BigInteger("1024"));
			bi3 = bi2.multiply(new BigInteger("8"));
			BigInteger totalValue = new BigInteger("0");
			String prevTimeValue = "00:00";
			String prevHour = prevTimeValue.split(":")[0];
			String currHour = "No Value";
			boolean datePresent = false;
			boolean pmxhContains = true;
			line = scanner.nextLine();
			while(scanner.hasNextLine() && !line.contains("pmxh")){
				line = scanner.nextLine();
			}
		    while (scanner.hasNextLine()) {
		    	line = scanner.nextLine();
		    	
		    	if(pmxhContains == false){
		    		while(scanner.hasNextLine() && !line.contains("pmxh")){
		    			line = scanner.nextLine();
		    		}
		    		if(scanner.hasNextLine()){
		    			line = scanner.nextLine();
		    			valueList = new ArrayList<String>();
		    			pmxhContains = true;
		    		}
		    	}
		    	if(line.contains("Report from")){
		    		str = line.split(" ");
		    		today = str[2]+"~"+str[3];
		    	}
		    	else if(line.contains("Node SW:")){
		    		while (scanner.hasNextLine()) {
		    			line = scanner.nextLine();
			    		if(line.startsWith("Time")){
			    			break;
			    		}
			    		else if(line.contains("Time") && line.startsWith("Date"))
			    		{
			    			datePresent = true;
			    			break;
			    		}
	    			}
		    		while (line != null)
		    		{
		    			line = scanner.nextLine();
		    			while (scanner.hasNextLine() && !(line.equalsIgnoreCase("")) && !(line.contains("0m>"))) {
		    				
		    				lineSplit = line.split("\\s{1,}");
		    				if(!datePresent){
		    					prevHour = lineSplit[0];
		    					if(currHour.equalsIgnoreCase("No Value")){
		    						currHour = prevHour;
		    					}
		    					if(!prevHour.equalsIgnoreCase(currHour)){
		    						valueList.add(new BigDecimal(totalValue.divide(bi3)).toString());
		    						totalValue = new BigInteger("0");
		    					}
		    					for(int i = 2;i<lineSplit.length; i++){
		    						totalValue = totalValue.add(new BigInteger(lineSplit[i]));
		    					}
		    				}
		    				else{
		    					prevHour = lineSplit[1];
		    					for(int i = 3;i<lineSplit.length; i++)
		    						totalValue = totalValue.add(new BigInteger(lineSplit[i]));
		    				}
		    				currHour = prevHour;
		    				line = scanner.nextLine();
		    			}
		    			if(null != line && !(line.contains("0m>"))){
		    				valueList.add(new BigDecimal(totalValue.divide(bi3)).toString());
		    				totalValue = new BigInteger("0");
		    			}
		    			pmxhContains = false;
		    			StringBuffer values = new StringBuffer("");
		    		    for(String value:valueList){
		    		    	values.append(value + ",");
		    		    }
		    		    valueList.clear();
		    		    currHour = "No Value";
		    		    line1 = today + "," + "PS Traffic" + "," + values;
		    		    String[] splitLine = line1.split(",");
		    		    
		    		    counterValueMap.put(splitLine[1], splitLine[2]);
		    		    rncCounterMap.put(rncName, counterValueMap);
		    		    psTrafficMap.put(splitLine[0], rncCounterMap);
		    			//line = scanner.nextLine();
		    			break;
		    		}
		    	}
		    }
		}
		catch(Exception ex){
			ex.printStackTrace();
			//System.out.println(ex);
		}
		finally{
			try{
				scanner.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		/***********************************************************************
		 * Reading from the log file and writing the data in the csv file --ENDS
		 ***********************************************************************/
		return psTrafficMap;
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
