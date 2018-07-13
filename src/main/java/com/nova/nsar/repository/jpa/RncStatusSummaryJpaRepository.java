package com.nova.nsar.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nova.nsar.repository.jpa.entity.RncStatusSummaryEntity;

public interface RncStatusSummaryJpaRepository extends PagingAndSortingRepository<RncStatusSummaryEntity, Long> {

}
