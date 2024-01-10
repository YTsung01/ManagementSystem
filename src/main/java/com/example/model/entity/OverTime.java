package com.example.model.entity;

import java.sql.Date;

public class OverTime {
	private Integer overTimeHour;            					//加班時數
	private Date overTimeDate;               					// 加班日期
	private String overTimeReason;           					// 加班事由
	private OverTimeTypeData overTimeType;             			// 加班類型
	private Integer overTimeTypeId;             				// 加班類型Id(給表單用)
	private OverTimeTypeForSalaryData overTimeTypeForSalary;    // 加班類型(平/假日 加班費不同)
	private Integer overTimeTypeForSalaryId;    				// 加班類型Id(給表單用)
	private Integer overTimeFormId;    				            // 加班申請單單號
	private Integer verifyState;           						// 加班審核結果 (通過:0 /不通過:1 )
	private String overTimeCheckReason;           				// 審核結果是否通過原因 
	
	public OverTime() {
		
	}
	
	public OverTime(Integer overTimeHour, Date overTimeDate, String overTimeReason, OverTimeTypeData overTimeType,
			Integer overTimeTypeId, OverTimeTypeForSalaryData overTimeTypeForSalary, Integer overTimeTypeForSalaryId,
			Integer overTimeFormId, Integer verifyState, String overTimeCheckReason) {
		this.overTimeHour = overTimeHour;
		this.overTimeDate = overTimeDate;
		this.overTimeReason = overTimeReason;
		this.overTimeType = overTimeType;
		this.overTimeTypeId = overTimeTypeId;
		this.overTimeTypeForSalary = overTimeTypeForSalary;
		this.overTimeTypeForSalaryId = overTimeTypeForSalaryId;
		this.overTimeFormId = overTimeFormId;
		this.verifyState = verifyState;
		this.overTimeCheckReason = overTimeCheckReason;
	}

	public Integer getOverTimeHour() {
		return overTimeHour;
	}

	public void setOverTimeHour(Integer overTimeHour) {
		this.overTimeHour = overTimeHour;
	}

	public Date getOverTimeDate() {
		return overTimeDate;
	}

	public void setOverTimeDate(Date overTimeDate) {
		this.overTimeDate = overTimeDate;
	}

	public String getOverTimeReason() {
		return overTimeReason;
	}

	public void setOverTimeReason(String overTimeReason) {
		this.overTimeReason = overTimeReason;
	}

	public OverTimeTypeData getOverTimeType() {
		return overTimeType;
	}

	public void setOverTimeType(OverTimeTypeData overTimeType) {
		this.overTimeType = overTimeType;
	}

	public Integer getOverTimeTypeId() {
		return overTimeTypeId;
	}

	public void setOverTimeTypeId(Integer overTimeTypeId) {
		this.overTimeTypeId = overTimeTypeId;
	}

	public OverTimeTypeForSalaryData getOverTimeTypeForSalary() {
		return overTimeTypeForSalary;
	}

	public void setOverTimeTypeForSalary(OverTimeTypeForSalaryData overTimeTypeForSalary) {
		this.overTimeTypeForSalary = overTimeTypeForSalary;
	}

	public Integer getOverTimeTypeForSalaryId() {
		return overTimeTypeForSalaryId;
	}

	public void setOverTimeTypeForSalaryId(Integer overTimeTypeForSalaryId) {
		this.overTimeTypeForSalaryId = overTimeTypeForSalaryId;
	}

	public Integer getOverTimeFormId() {
		return overTimeFormId;
	}

	public void setOverTimeFormId(Integer overTimeFormId) {
		this.overTimeFormId = overTimeFormId;
	}

	public Integer getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(Integer verifyState) {
		this.verifyState = verifyState;
	}

	public String getOverTimeCheckReason() {
		return overTimeCheckReason;
	}

	public void setOverTimeCheckReason(String overTimeCheckReason) {
		this.overTimeCheckReason = overTimeCheckReason;
	}

	@Override
	public String toString() {
		return "OverTime [overTimeHour=" + overTimeHour + ", overTimeDate=" + overTimeDate + ", overTimeReason="
				+ overTimeReason + ", overTimeType=" + overTimeType + ", overTimeTypeId=" + overTimeTypeId
				+ ", overTimeTypeForSalary=" + overTimeTypeForSalary + ", overTimeTypeForSalaryId="
				+ overTimeTypeForSalaryId + ", overTimeFormId=" + overTimeFormId + ", verifyState=" + verifyState
				+ ", overTimeCheckReason=" + overTimeCheckReason + "]";
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	

	
	

}
