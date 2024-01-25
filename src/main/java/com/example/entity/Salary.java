package com.example.entity;

import java.util.Date;

public class Salary {

	Integer id;
	
	Integer empId;

	Integer basicAmonut;
	
	Integer takeoffAmount;
	
	Integer overtimeAmount;
	
	Integer totalAmount;
	
	String salaryDate;
	
	Date createDate;
	
	public Salary() {
		
	}
	
	public Salary(Integer id, Integer empId, Integer basicAmonut, Integer takeoffAmount, Integer overtimeAmount,
			Integer totalAmount, String salaryDate, Date createDate) {
		this.id = id;
		this.empId = empId;
		this.basicAmonut = basicAmonut;
		this.takeoffAmount = takeoffAmount;
		this.overtimeAmount = overtimeAmount;
		this.totalAmount = totalAmount;
		this.salaryDate = salaryDate;
		this.createDate = createDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getBasicAmonut() {
		return basicAmonut;
	}

	public void setBasicAmonut(Integer basicAmonut) {
		this.basicAmonut = basicAmonut;
	}

	public Integer getTakeoffAmount() {
		return takeoffAmount;
	}

	public void setTakeoffAmount(Integer takeoffAmount) {
		this.takeoffAmount = takeoffAmount;
	}

	public Integer getOvertimeAmount() {
		return overtimeAmount;
	}

	public void setOvertimeAmount(Integer overtimeAmount) {
		this.overtimeAmount = overtimeAmount;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(String salaryDate) {
		this.salaryDate = salaryDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Salary [id=" + id + ", empId=" + empId + ", basicAmonut=" + basicAmonut + ", takeoffAmount="
				+ takeoffAmount + ", overtimeAmount=" + overtimeAmount + ", totalAmount=" + totalAmount
				+ ", salaryDate=" + salaryDate + ", createDate=" + createDate + "]";
	}
}
