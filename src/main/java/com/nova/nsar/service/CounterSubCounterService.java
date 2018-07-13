package com.nova.nsar.service;

import java.util.List;

import com.nova.nsar.repository.jpa.entity.CounterSubCounterDetailsEntity;

public interface CounterSubCounterService {

	List<CounterSubCounterDetailsEntity> findAllCounter();
}
