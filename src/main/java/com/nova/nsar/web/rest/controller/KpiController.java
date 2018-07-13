package com.nova.nsar.web.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nova.nsar.repository.jpa.entity.RncKpiDetailsEntity;
import com.nova.nsar.repository.jpa.entity.RncStatusDetailEntity;
import com.nova.nsar.service.dto.RncKpiDetails;
import com.nova.nsar.service.impl.KPIServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/kpi")
public class KpiController {

	@Autowired
	KPIServiceImpl kPIServiceImpl;

	@GetMapping("/rnc")
	public @ResponseBody List<RncKpiDetails> getRncKPIValue() {
		System.out.println("KpiController| 3G | network status");
		List<RncKpiDetails> values = kPIServiceImpl.fetchAllDetails();
		
		for(RncKpiDetails value : values){
			System.out.println(value.getCycleTime());
		}
		
		return kPIServiceImpl.fetchAllDetails();
	}

	@GetMapping("/bsc")
	public @ResponseBody List<RncStatusDetailEntity> getBscKPIValue() {
		System.out.println("KpiController| 2G | network status");

		return null;
	}

	@GetMapping("/lte")
	public @ResponseBody List<RncStatusDetailEntity> getLteKPIValue() {
		System.out.println("KpiController| 4G | network status");
		return null;
	}

}
