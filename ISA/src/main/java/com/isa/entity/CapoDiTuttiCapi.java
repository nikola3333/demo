package com.isa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "BOSS")
public class CapoDiTuttiCapi extends User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOSS_ID")
	private Long id;
}
