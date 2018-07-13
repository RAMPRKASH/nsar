package com.nova.nsar.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nova.nsar.process.analysis.RncKpiDataAnalysis;
import com.nova.nsar.repository.jpa.RncKpiDetailsJpaRepository;
import com.nova.nsar.repository.jpa.entity.RncKpiDetailsEntity;
import com.nova.nsar.service.KPIService;
import com.nova.nsar.service.dto.RncKpiDetails;
import com.nova.nsar.service.mapping.KpiServiceMapper;

@Service
public class KPIServiceImpl implements KPIService {

	@Autowired
	RncKpiDetailsJpaRepository rncKpiDetailsJpaRepo;
	
	@Autowired
	KpiServiceMapper kpiServiceMapper;

	public void detailSaveAll(List<RncKpiDetailsEntity> entities) {
		rncKpiDetailsJpaRepo.saveAll(entities);
	}

	public void detailSave(RncKpiDetailsEntity entity) {
		rncKpiDetailsJpaRepo.save(entity);
	}

	public void detailDeleteAll(List<RncKpiDetailsEntity> entities) {
		rncKpiDetailsJpaRepo.deleteAll(entities);
	}

	public void detailDelete(RncKpiDetailsEntity entity) {
		rncKpiDetailsJpaRepo.delete(entity);
	}

	public List<RncKpiDetailsEntity> fetchAll() {
		return (List<RncKpiDetailsEntity>) rncKpiDetailsJpaRepo.findAll();
	}
	
	
	public List<RncKpiDetails> fetchAllDetails(){
		return kpiServiceMapper.mapEntitiesToDtos(fetchAll());
	}

	// @PostConstruct
	public void checkRncKpiValues() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd~HH:mm");

		RncKpiDataAnalysis rncKpiDataAnalysisObj = new RncKpiDataAnalysis();
		String outputLog = rncKpiDataAnalysisObj.getStringFromFile("C:\\NSAR\\data\\MDERNC01Kpi.txt");
		Map<String, Map<String, Map<String, String>>> kpiMap = rncKpiDataAnalysisObj.createCsvFromTxt(outputLog,
				"MDERNC01");
		Map<String, Map<String, Map<String, String>>> psTrafficMap = rncKpiDataAnalysisObj
				.createPsTrafficCsvFromTxt(outputLog, "MDERNC01");

		List<RncKpiDetailsEntity> rncKpiDetailsEntities = new ArrayList<>();
		for (String dateTime : kpiMap.keySet()) {

			Map<String, Map<String, String>> rncKpiMap = kpiMap.get(dateTime);

			for (String rncName : rncKpiMap.keySet()) {

				Map<String, String> kpiValueMap = rncKpiMap.get(rncName);

				for (String kpiName : kpiValueMap.keySet()) {

					RncKpiDetailsEntity rncKpiDetailsObj = new RncKpiDetailsEntity();

					rncKpiDetailsObj.setCounterValue(Float.parseFloat(kpiValueMap.get(kpiName)));
					rncKpiDetailsObj.setCounterName(kpiName);
					rncKpiDetailsObj.setRncName(rncName);
					try {
						rncKpiDetailsObj.setCycleTime(sdf.parse(dateTime));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					rncKpiDetailsEntities.add(rncKpiDetailsObj);
				}
			}
		}
		for (String dateTime : psTrafficMap.keySet()) {
			Map<String, Map<String, String>> rncKpiMap = psTrafficMap.get(dateTime);
			for (String rncName : rncKpiMap.keySet()) {
				Map<String, String> kpiValueMap = rncKpiMap.get(rncName);
				for (String kpiName : kpiValueMap.keySet()) {

					RncKpiDetailsEntity rncKpiDetailsObj = new RncKpiDetailsEntity();

					rncKpiDetailsObj.setCounterValue(Float.parseFloat(kpiValueMap.get(kpiName)));
					rncKpiDetailsObj.setCounterName(kpiName);
					rncKpiDetailsObj.setRncName(rncName);
					try {
						rncKpiDetailsObj.setCycleTime(sdf.parse(dateTime));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					rncKpiDetailsEntities.add(rncKpiDetailsObj);
				}
			}
		}
		detailSaveAll(rncKpiDetailsEntities);
	}
}
