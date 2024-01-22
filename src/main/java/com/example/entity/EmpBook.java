package com.example.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EmpBook {

	Integer empId;
	
	String empName;
	
	String empPassword;
	
	String empSex;
	
	String empDepartment;
	
	Integer empDeptno;
	
	String empJob;
	
	Integer levelId;
	
	Date hireDate;
	
	Integer salary;
	
	Integer overTimeLeftHour;
	
	Integer overTimeTotalHour;
	
	Integer takeoffTotalHours;
	
	String empAcator;
	
	public EmpBook() {
		
	}

	public EmpBook(Integer empId, String empName, String empPassword, String empSex, String empDepartment,
			Integer empDeptno, String empJob, Integer levelId, Date hireDate, Integer salary, Integer overTimeLeftHour,
			Integer overTimeTotalHour, Integer takeoffTotalHours, String empAcator) {
		this.empId = empId;
		this.empName = empName;
		this.empPassword = empPassword;
		this.empSex = empSex;
		this.empDepartment = empDepartment;
		this.empDeptno = empDeptno;
		this.empJob = empJob;
		this.levelId = levelId;
		this.hireDate = hireDate;
		this.salary = salary;
		this.overTimeLeftHour = overTimeLeftHour;
		this.overTimeTotalHour = overTimeTotalHour;
		this.takeoffTotalHours = takeoffTotalHours;
		this.empAcator = empAcator;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public String getEmpDepartment() {
		return empDepartment;
	}

	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}

	public Integer getEmpDeptno() {
		return empDeptno;
	}

	public void setEmpDeptno(Integer empDeptno) {
		this.empDeptno = empDeptno;
	}

	public String getEmpJob() {
		return empJob;
	}

	public void setEmpJob(String empJob) {
		this.empJob = empJob;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getOverTimeLeftHour() {
		return overTimeLeftHour;
	}

	public void setOverTimeLeftHour(Integer overTimeLeftHour) {
		this.overTimeLeftHour = overTimeLeftHour;
	}

	public Integer getOverTimeTotalHour() {
		return overTimeTotalHour;
	}

	public void setOverTimeTotalHour(Integer overTimeTotalHour) {
		this.overTimeTotalHour = overTimeTotalHour;
	}

	public Integer getTakeoffTotalHours() {
		return takeoffTotalHours;
	}

	public void setTakeoffTotalHours(Integer takeoffTotalHours) {
		this.takeoffTotalHours = takeoffTotalHours;
	}

	public String getEmpAcator() {
		return empAcator;
	}

	public void setEmpAcator(String empAcator) {
		this.empAcator = empAcator;
	}

	@Override
	public String toString() {
		return "EmpBook [empId=" + empId + ", empName=" + empName + ", empPassword=" + empPassword + ", empSex="
				+ empSex + ", empDepartment=" + empDepartment + ", empDeptno=" + empDeptno + ", empJob=" + empJob
				+ ", levelId=" + levelId + ", hireDate=" + hireDate + ", salary=" + salary + ", overTimeLeftHour="
				+ overTimeLeftHour + ", overTimeTotalHour=" + overTimeTotalHour + ", takeoffTotalHours="
				+ takeoffTotalHours + ", empAcator=" + empAcator + "]";
	}
}
