package com.nova.nsar.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nova.nsar.repository.jpa.entity.HardwareTypeEntity;

public interface HardwareTypeJpaRepository extends PagingAndSortingRepository<HardwareTypeEntity, Long> {

}
