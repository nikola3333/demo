package com.isa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.isa.entity.CapoDiTuttiCapi;

@Repository
public interface CapoRepository extends PagingAndSortingRepository<CapoDiTuttiCapi,Long>{

	public CapoDiTuttiCapi findByEmail(String email);
}
