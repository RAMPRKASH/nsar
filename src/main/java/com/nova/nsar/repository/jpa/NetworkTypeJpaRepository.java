package com.nova.nsar.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nova.nsar.repository.jpa.entity.NetworkTypeEntity;

public interface NetworkTypeJpaRepository extends PagingAndSortingRepository<NetworkTypeEntity, Long> {

}
