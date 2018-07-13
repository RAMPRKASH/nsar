package com.nova.nsar.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nova.nsar.common.utils.NsarUtils;
import com.nova.nsar.process.analysis.BscKpiDataAnalysis;
import com.nova.nsar.repository.jpa.BscKpiDetailsJpaRepository;
import com.nova.nsar.repository.jpa.entity.BscKpiDetailsEntity;
import com.nova.nsar.repository.jpa.entity.CounterSubCounterDetailsEntity;
import com.nova.nsar.service.KPIService;

@Service
public class BscKpiServiceImpl implements KPIService {
	
	@Autowired
	BscKpiDetailsJpaRepository bscKpiDetailsJpaRepo;
	
	@Autowired
	CounterSubCounterServiceImpl counterServiceImpl;
	
	@Autowired
	KpiFormulaServiceImpl kpiFormulaServiceImpl;
	
	public void detailSaveAll(List<BscKpiDetailsEntity> entities) {
		bscKpiDetailsJpaRepo.saveAll(entities);
	}

	public void detailSave(BscKpiDetailsEntity entity) {
		bscKpiDetailsJpaRepo.save(entity);
	}
	
	public void detailDeleteAll(List<BscKpiDetailsEntity> entities) {
		bscKpiDetailsJpaRepo.deleteAll(entities);
	}
	
	public void detailDelete(BscKpiDetailsEntity entity) {
		bscKpiDetailsJpaRepo.delete(entity);
	}

	@PostConstruct
	public void checkBscKpiValues(){
		
		BscKpiDataAnalysis bscKpiDataAnalysisObj = new BscKpiDataAnalysis();
		Map<String, List<String>> counterMap = getCounterMapData();
		String outputLog = bscKpiDataAnalysisObj.getStringFromFile("E:\\Ericson\\NSAR\\data\\MDEBS01_10.21.6.71_Telenor.txt");
		String csvString = bscKpiDataAnalysisObj.create2gCtrTxttoCsv(counterMap, outputLog);
		Map<Date, HashMap<String, Double>> kpiMap = bscKpiDataAnalysisObj.get2gKpiOutput(csvString, 15, kpiFormulaServiceImpl);
		
		List<BscKpiDetailsEntity> bscKpiDetailsEntities = new ArrayList<>();
		for(Date dateTime:kpiMap.keySet()){

			HashMap<String, Double> kpiValueMap = kpiMap.get(dateTime);
			
			for(String kpiName:kpiValueMap.keySet()){
				
				BscKpiDetailsEntity bscKpiDetailsObj = new BscKpiDetailsEntity();
				
				bscKpiDetailsObj.setKpiValue(kpiValueMap.get(kpiName));
				bscKpiDetailsObj.setKpiName(kpiName);
				bscKpiDetailsObj.setBscName("MDEBS01");
				bscKpiDetailsObj.setCycleTime(NsarUtils.formatToDate(dateTime, "yyyy-MM-dd~HH:mm"));
				bscKpiDetailsEntities.add(bscKpiDetailsObj);
			}
		}
		
		detailSaveAll(bscKpiDetailsEntities);
	}
	
	public Map<String, List<String>> getCounterMapData(){
		
		Map<String, List<String>> counterMap = new HashMap<>();
		
		List<CounterSubCounterDetailsEntity> counterSubCounterList = counterServiceImpl.findAllCounterNetworkName("BSC");
		
		for(CounterSubCounterDetailsEntity counterSubCounter:counterSubCounterList){
			counterMap.put(counterSubCounter.getCounterName(), Arrays.asList(counterSubCounter.getSubCounterNames().split(",")));
		}
		return counterMap;
	}
}
