package com.nova.nsar.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nova.nsar.common.utils.NsarUtils;
import com.nova.nsar.process.analysis.RncNetworkAvailabilityAnalysis;
import com.nova.nsar.process.analysis.RncNetworkAvailabilitySummaryAnalysis;
import com.nova.nsar.repository.jpa.RncStatusDetailJpaRepository;
import com.nova.nsar.repository.jpa.RncStatusSummaryJpaRepository;
import com.nova.nsar.repository.jpa.entity.OssDetails;
import com.nova.nsar.repository.jpa.entity.RncStatusDetailEntity;
import com.nova.nsar.repository.jpa.entity.RncStatusSummaryEntity;
import com.nova.nsar.service.NetworkAvailabilityService;

@Service
public class RncNetworkAvailabilityServiceImpl implements NetworkAvailabilityService {

	@Autowired
	RncStatusDetailJpaRepository rncStatusDetailJpaRepo;

	@Autowired
	RncStatusSummaryJpaRepository rncStatusSummaryJpaRepo;

	public void detailSaveAll(List<RncStatusDetailEntity> entities) {
		rncStatusDetailJpaRepo.saveAll(entities);
	}

	public void detailSave(RncStatusDetailEntity entity) {
		rncStatusDetailJpaRepo.save(entity);
	}

	public void detailDeleteAll(List<RncStatusDetailEntity> entities) {
		rncStatusDetailJpaRepo.deleteAll(entities);
	}

	public void detailDelete(RncStatusDetailEntity entity) {
		rncStatusDetailJpaRepo.delete(entity);
	}

	public void summarySaveAll(List<RncStatusSummaryEntity> entities) {
		rncStatusSummaryJpaRepo.saveAll(entities);
	}

	public void summarySave(RncStatusSummaryEntity entity) {
		rncStatusSummaryJpaRepo.save(entity);
	}

	public void summaryDeleteAll(List<RncStatusSummaryEntity> entities) {
		rncStatusSummaryJpaRepo.deleteAll(entities);
	}

	public void summaryDelete(RncStatusSummaryEntity entity) {
		rncStatusSummaryJpaRepo.delete(entity);
	}

	@PostConstruct
	public void checkNetworkAvailability() {

		System.out.println(
				"*****************************Running Network Availability**********************************************");

		RncNetworkAvailabilityAnalysis rncNetworkAvailabilityAnalysisObj = new RncNetworkAvailabilityAnalysis();
		String outputLog = rncNetworkAvailabilityAnalysisObj.getStringFromFile("E:\\Ericson\\NSAR\\data\\MDERNC01.txt");
		Map<String, Map<String, Map<String, String>>> rncSiteMap = rncNetworkAvailabilityAnalysisObj
				.parseString(outputLog);

		List<RncStatusDetailEntity> rncStatusDetailEntityList = new ArrayList<>();
		for (String rncName : rncSiteMap.keySet()) {
			Map<String, Map<String, String>> siteCellMap = rncSiteMap.get(rncName);
			for (String siteName : siteCellMap.keySet()) {
				Map<String, String> cellSectorMap = siteCellMap.get(siteName);
				for (String cellName : cellSectorMap.keySet()) {
					String sectorValues = cellSectorMap.get(cellName);

					RncStatusDetailEntity rncObj = new RncStatusDetailEntity();

					rncObj.setSectorStatus(sectorValues);
					rncObj.setCellName(cellName);
					rncObj.setSiteName(siteName);
					rncObj.setRncName(rncName);
					rncObj.setStatus(checkCellStatusFromSectorStatus(sectorValues));
					rncObj.setCycleTime(NsarUtils.formatToDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
					rncStatusDetailEntityList.add(rncObj);
				}
			}
		}

		detailSaveAll(rncStatusDetailEntityList);

		// Saving summary data

		// ExecutorService executor = Executors.newFixedThreadPool(30);
		RncNetworkAvailabilitySummaryAnalysis rncNetworkAvailabilitySummAnalysisObj = new RncNetworkAvailabilitySummaryAnalysis();
		outputLog = rncNetworkAvailabilitySummAnalysisObj.getStringFromFile("E:\\Ericson\\NSAR\\data\\MDERNC01.txt");
		Map<String, Map<String, String>> rncSiteCellSummaryMap = rncNetworkAvailabilitySummAnalysisObj
				.parseString(outputLog);

		List<RncStatusSummaryEntity> rncStatusSummaryEntityList = new ArrayList<>();
		for (String rncName : rncSiteCellSummaryMap.keySet()) {
			Map<String, String> siteCellMap = rncSiteCellSummaryMap.get(rncName);
			RncStatusSummaryEntity rncObj = new RncStatusSummaryEntity();
			for (String networkComponentName : siteCellMap.keySet()) {
				String networkComponentCount = siteCellMap.get(networkComponentName);
				String[] countArr = networkComponentCount.split("~");

				if (networkComponentName.equalsIgnoreCase("Cell")) {
					rncObj.setUpCellCount(Integer.parseInt(countArr[0]));
					rncObj.setDownCellCount(Integer.parseInt(countArr[1]));
					rncObj.setTotalCellCount(Integer.parseInt(countArr[2]));
				} else if (networkComponentName.equalsIgnoreCase("Site")) {
					rncObj.setUpSiteCount(Integer.parseInt(countArr[0]));
					rncObj.setDownSiteCount(Integer.parseInt(countArr[1]));
					rncObj.setTotalSiteCount(Integer.parseInt(countArr[2]));
				}
			}
			rncObj.setRncName(rncName);
			rncStatusSummaryEntityList.add(rncObj);
		}

		summarySaveAll(rncStatusSummaryEntityList);

	}

	private int checkCellStatusFromSectorStatus(String sectorStatus) {
		int returnStatus = 1;
		if (sectorStatus.contains("1")) {
			returnStatus = 1;
		}
		if (sectorStatus.contains("0")) {
			returnStatus = 0;
		}
		if ((sectorStatus.contains("0") && sectorStatus.contains("1")) || sectorStatus.contains("2")) {
			returnStatus = 2;
		}
		return returnStatus;
	}

	@Override
	public void checkNetworkAvailability(List<OssDetails> ossDetails, String networkAvlblCommand,
			String commandOutputPath) {
		// TODO Auto-generated method stub

	}

	public List<RncStatusSummaryEntity> fetchAllSummary() {
		return (List<RncStatusSummaryEntity>) rncStatusSummaryJpaRepo.findAll();
	}

	public List<RncStatusDetailEntity> fetchAllDetails() {
		return (List<RncStatusDetailEntity>) rncStatusDetailJpaRepo.findAll();
	}

}
