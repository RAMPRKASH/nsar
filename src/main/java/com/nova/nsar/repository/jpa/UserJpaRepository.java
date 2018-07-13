package com.nova.nsar.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nova.nsar.repository.jpa.entity.UserEntity;

public interface UserJpaRepository extends PagingAndSortingRepository<UserEntity, Long>{

}
