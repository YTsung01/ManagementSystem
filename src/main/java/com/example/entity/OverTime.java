package com.example.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OverTime {
	
	String formId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	Date startTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	Date endTime;
	
	Integer applyHour;
	Integer overtimeType;	//1:加班 2:補休
	Integer dayOrHoilday; 	//1:平日 2:假日
	String reason;
	Integer verifyState; 	//0:未通過 1:通過 2:審核中
	String checkReason;
	
	EmpBook empBook;
	
	public OverTime() {}

	public OverTime(String formId, Date startTime, Date endTime, Integer applyHour, Integer overtimeType,
			Integer dayOrHoilday, String reason, Integer verifyState, String checkReason, EmpBook empBook) {
	
		this.formId = formId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.applyHour = applyHour;
		this.overtimeType = overtimeType;
		this.dayOrHoilday = dayOrHoilday;
		this.reason = reason;
		this.verifyState = verifyState;
		this.checkReason = checkReason;
		this.empBook = empBook;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getApplyHour() {
		return applyHour;
	}

	public void setApplyHour(Integer applyHour) {
		this.applyHour = applyHour;
	}

	public Integer getOvertimeType() {
		return overtimeType;
	}

	public void setOvertimeType(Integer overtimeType) {
		this.overtimeType = overtimeType;
	}

	public Integer getDayOrHoilday() {
		return dayOrHoilday;
	}

	public void setDayOrHoilday(Integer dayOrHoilday) {
		this.dayOrHoilday = dayOrHoilday;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(Integer verifyState) {
		this.verifyState = verifyState;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}
	
	public EmpBook getEmpBook() {
		return empBook;
	}

	public void setEmpBook(EmpBook empBook) {
		this.empBook = empBook;
	}

	@Override
	public String toString() {
		return "OverTime [formId=" + formId + ", startTime=" + startTime + ", endTime=" + endTime + ", applyHour="
				+ applyHour + ", overtimeType=" + overtimeType + ", dayOrHoilday=" + dayOrHoilday + ", reason=" + reason
				+ ", verifyState=" + verifyState + ", checkReason=" + checkReason + "]";
	}


}


