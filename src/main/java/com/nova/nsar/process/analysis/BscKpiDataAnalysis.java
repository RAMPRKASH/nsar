package com.nova.nsar.process.analysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.nova.nsar.common.utils.NsarUtils;
import com.nova.nsar.repository.jpa.entity.KpiFormulaDetailsEntity;
import com.nova.nsar.service.impl.KpiFormulaServiceImpl;

public class BscKpiDataAnalysis {

	ScriptEngineManager manager = new ScriptEngineManager();
	ScriptEngine engine = null;

	InputStream input = null;
	
	public String create2gCtrTxttoCsv(Map<String,List<String>> counterMap, String outputLog){
		/**************************************************************************
		 * Reading from the buffer log and return the comma separated values String --STARTS
		 **************************************************************************/
		List<String> kpiList = new ArrayList<String>();
		Scanner scanner = new Scanner(outputLog);
		StringBuilder sb = new StringBuilder("");
		String line;
		String line1;
		String[] str;
		String year;
		String fullDate = null;
		String dateStr;
		String[] cellCtr;
		String time = null;
		String cellId = null;
		try {
			for(String key: counterMap.keySet()){
				kpiList = counterMap.get(key);
				scanner = new Scanner(outputLog);
				if(scanner.hasNextLine()){
					line = scanner.nextLine();
				}
				else{
					break;
				}
				while(!(line.contains(key)) && !(line.contains("stmpts"))){
					if(scanner.hasNextLine()){
						line = scanner.nextLine();
					}
				}
				while(scanner.hasNextLine()){
					line = scanner.nextLine();
					if(line.contains("===")){
						line = scanner.nextLine();
						str = line.split(" ");
						year = str[5];
						time = str[3];
						str = line.split("\\+");
						dateStr = str[0];
						fullDate = dateStr + " " + year;
						break;
					}
				}
				line = scanner.nextLine();
				line = scanner.nextLine();
				if(line.contains(key)){
					cellCtr = line.split(":");
					cellId = cellCtr[1].trim().replaceAll("'", "");
				}
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					if(line.contains("No xml files to parse")){
						System.out.println("No data fetched for the provided time interval.");
						break;
					}
					while (scanner.hasNextLine()) {
						line = scanner.nextLine();
						if(line.startsWith("Number")){
							line = scanner.nextLine();
							while(!line.contains("---") && !line.contains("===")){
								String[] kpiName = line.split("\\s{1,}") ;
								if(kpiList.contains(kpiName[3])){
									line = line.trim();
									line1 = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("E MMM dd, HH:mm z yyyy").parse(fullDate))
											+","+time+","+cellId+","+kpiName[3]+","+kpiName[2];
									sb.append(line1);
									sb.append("\n");
								}
								line = scanner.nextLine();
							}
							break;
						}
					}		 
					if(line!=null && !line.contains("stmpts")){
						if(line.contains("---")){
							line = scanner.nextLine();
							if(line.split(" ").length>5){
								str = line.split(" ");
								year = str[5];
								time = str[3];
								str = line.split("\\+");
								dateStr = str[0];
								fullDate = dateStr + " " + year;
							}
							line = scanner.nextLine();
							line = scanner.nextLine();
							if(line.contains(key)){
								cellCtr = line.split(":");
								cellId = cellCtr[1].trim().replaceAll("'", "");
							}
						}
					}
				}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try{
					//bw.flush();
					//bw.close();
					scanner.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		/***********************************************************************
		 * Reading from the buffer log and return the comma separated values String --ENDS
		 ***********************************************************************/
		return sb.toString();
	}
	
	
	public TreeMap<Date, HashMap<String, Double>> get2gKpiOutput(String csvString,int ropSize, KpiFormulaServiceImpl kpiFormulaServiceImpl) {

		Scanner scanner = null;
		
		TreeMap<Date, HashMap<String, Double>> counterMap = new TreeMap<Date, HashMap<String, Double>>();

		try {
			scanner = new Scanner(csvString);

			String line = null;
			TreeMap<Date, HashMap<String, Long>> kpiMap = new TreeMap<Date, HashMap<String, Long>>();
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				String[] lines = line.split(",");
				String dateStr = lines[0] + " " + lines[1] + ":00";
				Date date = NsarUtils.formatToDate(dateStr, "yyyy-MM-dd HH:mm"); 
				if (kpiMap.containsKey(date)) {
					
					HashMap<String, Long> sumTemp = kpiMap.get(date);
					
					if (sumTemp.containsKey(lines[3])) {
						Long sum = sumTemp.get(lines[3]) + Long.parseLong(lines[4]);
						sumTemp.put(lines[3], sum);
					} else {
						sumTemp.put(lines[3], Long.parseLong(lines[4]));
					}
				} else {
					HashMap<String, Long> temp = new HashMap<String, Long>();
					temp.put(lines[3], Long.parseLong(lines[4]));
					kpiMap.put(date, temp);
				}
			}

			HashMap<String, String> formulaMap = get2gKpiFormula(kpiFormulaServiceImpl);

			for (Date key : kpiMap.keySet()) {
				
				engine = manager.getEngineByName("nashorn");

				for (String kpiName : kpiMap.get(key).keySet()) {
					// System.out.println("Kpi Name : " + kpiName + " | Value : "+ kpiMap.get(key).get(kpiName));
					engine.put(kpiName, kpiMap.get(key).get(kpiName));
				}

				HashMap<String, Double> temp = new HashMap<String, Double>();
				for (String kpiName : formulaMap.keySet()) {
					try {
						Double val = (Double) engine.eval(formulaMap
								.get(kpiName));
						temp.put(kpiName, Math.round((val) * 10.0) / 10.0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				counterMap.put(key, temp);
			}
			System.out.println("counterMap::::::::"+counterMap);
			
			/*HashMap<Date ,HashMap<String,Double>> finalSDMap = calculateSDMean(commandOutputPath, ropSize);
			//HashMap<Date, Double> countMap = new HashMap<Date, Double>();
			for(Date date:finalSDMap.keySet()){
				HashMap<String, Double> outerMap = finalSDMap.get(date);
				double count = 0;
				double totalValue = 0;
				for(String cell:outerMap.keySet()){
					totalValue += outerMap.get(cell);
					count++;
				}
				double averageValue = BigDecimal.valueOf((double)totalValue/(double)count).setScale(2, RoundingMode.HALF_UP).doubleValue();
				HashMap<String, Double> temp = counterMap.get(date);
				temp.put("SD Mean Holding Time",averageValue);
				counterMap.put(date, temp);
			}*/
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try{
				scanner.close();
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}

		return counterMap;
	}
	
	//	Fetch the formula from DB
	
	//	Update 1:Created Table-->kpi_formula_details in DB and also inserted values
	//	Update 2:Fetched the values from Db. Need to test this
	//	Update 3:Tested and working fine
	
	public HashMap<String, String> get2gKpiFormula(KpiFormulaServiceImpl kpiFormulaServiceImpl) {

		HashMap<String, String> formulaMap = new HashMap<String, String>();
		
		List<KpiFormulaDetailsEntity> kpiFormulas = kpiFormulaServiceImpl.findKpiFormulaByNetworkName("BSC");

		for(KpiFormulaDetailsEntity kpiFormula: kpiFormulas){
			formulaMap.put(kpiFormula.getKpiName(), kpiFormula.getKpiFormula());
		}
		
		return formulaMap;
	}
	
	//	TODO - Fetch the data not from file instead from the buffer log
	// 	Only for testing purposes - Till no live data is fetched
	
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
