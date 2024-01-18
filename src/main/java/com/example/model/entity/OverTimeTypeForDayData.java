package com.example.model.entity;


//Entity
public class OverTimeTypeForDayData {
	
	// 一般欄位
	protected Integer id;
	protected String name;

	
	public OverTimeTypeForDayData() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "OverTimeTypeForSalaryData [id=" + id + ", name=" + name + "]";
	}



	

}