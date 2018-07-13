package com.nova.nsar.repository.jpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nova.nsar.repository.jpa.entity.KpiFormulaDetailsEntity;

public interface KpiFormulaDetailsJpaRepository extends PagingAndSortingRepository<KpiFormulaDetailsEntity, Long> {

	List<KpiFormulaDetailsEntity> findByNetworkName(String networkName);

}