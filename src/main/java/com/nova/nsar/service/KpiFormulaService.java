package com.nova.nsar.service;

import java.util.List;

import com.nova.nsar.repository.jpa.entity.KpiFormulaDetailsEntity;

public interface KpiFormulaService {

	List<KpiFormulaDetailsEntity> findAllkpiFormula(); 
}
