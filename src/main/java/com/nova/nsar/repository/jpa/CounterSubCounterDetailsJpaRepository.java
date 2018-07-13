package com.nova.nsar.repository.jpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nova.nsar.repository.jpa.entity.CounterSubCounterDetailsEntity;

public interface CounterSubCounterDetailsJpaRepository extends PagingAndSortingRepository<CounterSubCounterDetailsEntity, Long> {

	List<CounterSubCounterDetailsEntity> findByNetworkName(String networkName);

}