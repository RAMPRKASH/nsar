package com.nova.nsar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nova.nsar.repository.jpa.KpiFormulaDetailsJpaRepository;
import com.nova.nsar.repository.jpa.entity.KpiFormulaDetailsEntity;
import com.nova.nsar.service.KpiFormulaService;

@Service
public class KpiFormulaServiceImpl implements KpiFormulaService{

	@Autowired
	KpiFormulaDetailsJpaRepository kpiFormulaDetailsJpaRepo;
	
	@Override
	public List<KpiFormulaDetailsEntity> findAllkpiFormula() {
		
		return (List<KpiFormulaDetailsEntity>) kpiFormulaDetailsJpaRepo.findAll();
	}
	
	public List<KpiFormulaDetailsEntity> findKpiFormulaByNetworkName(String networkName) {
		
		return (List<KpiFormulaDetailsEntity>) kpiFormulaDetailsJpaRepo.findByNetworkName(networkName);
	}
	
}
