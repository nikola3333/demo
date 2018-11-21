package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.isa.entity.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{

	@Query("select r from Restaurant r  order by r.name")
	public List<Restaurant> findAllSortedByN();

	@Query("select r from Restaurant r  order by r.typeOfRestaurant")
	public List<Restaurant> findAllSortedByT();
	
	@Query("select r from Restaurant r where (r.name like :condition or r.typeOfRestaurant like :condition) order by r.name")
	public List<Restaurant> findByConditionN(@Param("condition")String condition);
	
	@Query("select r from Restaurant r where (r.name like :condition or r.typeOfRestaurant like :condition) order by r.typeOfRestaurant")
	public List<Restaurant> findByConditionT(@Param("condition")String condition);
}
