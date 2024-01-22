package com.example.entity;

import java.util.Date;

public class Form {
	
	String formId; 		//表單號碼
	
	Integer type;  		//表單類型 1:takeoff(請假) 2:overtime(加班)'
	
	Integer applier;	//申請人員工編號
	
	Date applyDate;		//申請日期
	
	public Form() {
		
	}
	

	public Form(String formId, Integer type, Integer applier, Date applyDate) {
		
		this.formId = formId;
		this.type = type;
		this.applier = applier;
		this.applyDate = applyDate;
	}


	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getApplier() {
		return applier;
	}

	public void setApplier(Integer applier) {
		this.applier = applier;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}


	@Override
	public String toString() {
		return "Form [formId=" + formId + ", type=" + type + ", applier=" + applier + ", applyDate=" + applyDate + "]";
	}
	
	

}
