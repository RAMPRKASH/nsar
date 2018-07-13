package com.nova.nsar.service.mapping;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.nova.nsar.repository.jpa.entity.RncKpiDetailsEntity;
import com.nova.nsar.service.dto.RncKpiDetails;


/**
 * Mapping between entity beans and display beans.
 */
@Component
public class KpiServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public KpiServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}


	public List<RncKpiDetails> mapEntitiesToDtos(List<RncKpiDetailsEntity> rncKpiDetailsEntity) {
		if(rncKpiDetailsEntity == null) {
			return null;
		}

		
		List<RncKpiDetails> alarm = map(rncKpiDetailsEntity, RncKpiDetails.class);

		return alarm;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
}
