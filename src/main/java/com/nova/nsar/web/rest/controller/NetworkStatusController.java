package com.nova.nsar.web.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nova.nsar.repository.jpa.entity.RncStatusDetailEntity;
import com.nova.nsar.repository.jpa.entity.RncStatusSummaryEntity;
import com.nova.nsar.service.impl.RncNetworkAvailabilityServiceImpl;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/network/")

public class NetworkStatusController {
	static ArrayList list =  new ArrayList();
	@Autowired
	RncNetworkAvailabilityServiceImpl rncNetworkAvailabilityServiceImpl;

	@GetMapping("/status/summary")
	public @ResponseBody List<RncStatusSummaryEntity> getStatus() {
		System.out.println("NetworkStatusController| 3G | network status");
		return rncNetworkAvailabilityServiceImpl.fetchAllSummary();
	}

	@GetMapping("/status/details/")
	public @ResponseBody List<RncStatusDetailEntity> getStatusDetails() {
		System.out.println("NetworkStatusController| 3G | network status");
		
		/*List<RncStatusDetailEntity> li = new ArrayList<RncStatusDetailEntity>();
		 Iterable<RncStatusDetailEntity> details = rncNetworkAvailabilityServiceImpl.fetchAllDetails();
		 for(RncStatusDetailEntity entity : details){
			 li.add(entity);
		 }*/
		return rncNetworkAvailabilityServiceImpl.fetchAllDetails();
	}
	
}