package com.nova.nsar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nova.nsar.repository.jpa.CounterSubCounterDetailsJpaRepository;
import com.nova.nsar.repository.jpa.entity.CounterSubCounterDetailsEntity;
import com.nova.nsar.service.CounterSubCounterService;

@Service
public class CounterSubCounterServiceImpl implements CounterSubCounterService {
	
	@Autowired
	CounterSubCounterDetailsJpaRepository counterSubCounterRepo;

	@Override
	public List<CounterSubCounterDetailsEntity> findAllCounter() {
		return (List<CounterSubCounterDetailsEntity>) counterSubCounterRepo.findAll();
	}

	public List<CounterSubCounterDetailsEntity> findAllCounterNetworkName(String networkName) {

		return (List<CounterSubCounterDetailsEntity>) counterSubCounterRepo.findByNetworkName(networkName);
	}
}
