package com.bjss.william.employees.model;

public enum GenderEnum {
	M(0),
	F(1);
	
	private int gender;

	private GenderEnum(int gender) {
		this.gender = gender;
	}
	
	public int getGender() {
		return this.gender;
	}
}
