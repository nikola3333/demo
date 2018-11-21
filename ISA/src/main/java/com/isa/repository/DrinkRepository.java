package com.isa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.isa.entity.Drink;

public interface DrinkRepository extends PagingAndSortingRepository<Drink, Long> {

}
