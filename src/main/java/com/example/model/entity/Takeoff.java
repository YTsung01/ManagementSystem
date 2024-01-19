package com.example.model.entity;


import java.sql.Timestamp;

//formId, startTime, endTime, applyHour, overtimeType, dayOrHoilday, reason, verifyState, checkReason
import java.util.Date;

public class Takeoff {
	private String formId;    				            // 加班申請單單號
	private Date startTime;               				// 加班開始的時間
	private Date endTime;               				// 加班結束的時間
	private Integer applyHour;            				// 加班時數
	private Integer overtimeType;             			// 加班類型 1:加班 2:補休
	private Integer dayOrHoilday;    				    // 加班類型Id(1:平日 2:假日)
	private String reason;           					// 加班事由
	private Integer verifyState;           				// 加班審核結果 (通過:1 /不通過:0 )
	private String checkReason;           				// 審核結果是否通過原因 
	
	private Employee employee;            				//員工物件(關聯欄位)
	
	public Takeoff() {
		
	}

	public Takeoff(String formId, Date startTime, Date endTime, Integer applyHour, Integer overtimeType,
			Integer dayOrHoilday, String reason, Integer verifyState, String checkReason, Employee employee) {
		super();
		this.formId = formId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.applyHour = applyHour;
		this.overtimeType = overtimeType;
		this.dayOrHoilday = dayOrHoilday;
		this.reason = reason;
		this.verifyState = verifyState;
		this.checkReason = checkReason;
		this.employee = employee;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "OverTime [formId=" + formId + ", startTime=" + startTime + ", endTime=" + endTime + ", applyHour="
				+ applyHour + ", overtimeType=" + overtimeType + ", dayOrHoilday=" + dayOrHoilday + ", reason=" + reason
				+ ", verifyState=" + verifyState + ", checkReason=" + checkReason + ", employee=" + employee + "]";
	}

	

}