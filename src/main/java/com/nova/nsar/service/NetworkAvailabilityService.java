package com.nova.nsar.service;

import java.util.List;

import com.nova.nsar.repository.jpa.entity.OssDetails;

public interface NetworkAvailabilityService {

	public void checkNetworkAvailability(List<OssDetails> ossDetails, String networkAvlblCommand, String commandOutputPath);
}