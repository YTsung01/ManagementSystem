package com.example.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class CheckIn {

	private Integer empId;
	private Integer checkInId;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkInTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkOutTime;

	public CheckIn() {

	}

	public CheckIn(Integer empId, Integer checkInId, Date checkInTime, Date checkOutTime) {

		this.empId = empId;
		this.checkInId = checkInId;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getCheckInId() {
		return checkInId;
	}

	public void setCheckInId(Integer checkInId) {
		this.checkInId = checkInId;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	@Override
	public String toString() {
		return "CheckIn [empId=" + empId + ", checkInId=" + checkInId + ", checkInTime=" + checkInTime
				+ ", checkOutTime=" + checkOutTime + "]";
	}

}