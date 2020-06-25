package com.pmrendszer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Skills")
public class Skill {
	@GeneratedValue
	@Column(columnDefinition = "serial")
	@Id
	private int id;
	private String name;

	public Skill() {
		;
	}

	public Skill(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}