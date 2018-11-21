package com.isa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.isa.entity.Foodstuffs;

public interface FoodStuffsRepository extends PagingAndSortingRepository<Foodstuffs, Long> {

}
