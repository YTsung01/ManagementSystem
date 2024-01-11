package com.example.model.entity;

import java.sql.Date;

public class OverTime {
	private Integer overTimeFormId;    				            // 加班申請單單號
	private Integer empId;        								//員工代號
	private String empName;       								// 員工名稱
	private String empDepartment; 								// 部門
	private Integer empDeptno;    								// 部門代號
	private String empJob;        								// 職位
	
	private Date overTimeDate;               					// 加班日期
	private Integer overTimeHour;            					// 加班時數
	private OverTimeTypeData overTimeType;             			// 加班類型(加班/補修)
	private Integer overTimeTypeId;             				// 加班類型Id(給表單用)
	private OverTimeTypeForSalaryData overTimeTypeForDay;       // 加班類型(平/假日 加班費不同)
	private Integer overTimeTypeForDayId;    				    // 加班類型Id(給表單用)
	private String overTimeReason;           					// 加班事由
	private Integer verifyState;           						// 加班審核結果 (通過:1 /不通過:0 )
	private String overTimeCheckReason;           				// 審核結果是否通過原因 
	
	public OverTime() {
		
	}

	public OverTime(Integer overTimeFormId, Integer empId, String empName, String empDepartment, Integer empDeptno,
			String empJob, Date overTimeDate, Integer overTimeHour, OverTimeTypeData overTimeType,
			Integer overTimeTypeId, OverTimeTypeForSalaryData overTimeTypeForDay, Integer overTimeTypeForDayId,
			String overTimeReason, Integer verifyState, String overTimeCheckReason) {
		super();
		this.overTimeFormId = overTimeFormId;
		this.empId = empId;
		this.empName = empName;
		this.empDepartment = empDepartment;
		this.empDeptno = empDeptno;
		this.empJob = empJob;
		this.overTimeDate = overTimeDate;
		this.overTimeHour = overTimeHour;
		this.overTimeType = overTimeType;
		this.overTimeTypeId = overTimeTypeId;
		this.overTimeTypeForDay = overTimeTypeForDay;
		this.overTimeTypeForDayId = overTimeTypeForDayId;
		this.overTimeReason = overTimeReason;
		this.verifyState = verifyState;
		this.overTimeCheckReason = overTimeCheckReason;
	}

	public Integer getOverTimeFormId() {
		return overTimeFormId;
	}

	public void setOverTimeFormId(Integer overTimeFormId) {
		this.overTimeFormId = overTimeFormId;
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

	public Date getOverTimeDate() {
		return overTimeDate;
	}

	public void setOverTimeDate(Date overTimeDate) {
		this.overTimeDate = overTimeDate;
	}

	public Integer getOverTimeHour() {
		return overTimeHour;
	}

	public void setOverTimeHour(Integer overTimeHour) {
		this.overTimeHour = overTimeHour;
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

	public OverTimeTypeForSalaryData getOverTimeTypeForDay() {
		return overTimeTypeForDay;
	}

	public void setOverTimeTypeForDay(OverTimeTypeForSalaryData overTimeTypeForDay) {
		this.overTimeTypeForDay = overTimeTypeForDay;
	}

	public Integer getOverTimeTypeForDayId() {
		return overTimeTypeForDayId;
	}

	public void setOverTimeTypeForDayId(Integer overTimeTypeForDayId) {
		this.overTimeTypeForDayId = overTimeTypeForDayId;
	}

	public String getOverTimeReason() {
		return overTimeReason;
	}

	public void setOverTimeReason(String overTimeReason) {
		this.overTimeReason = overTimeReason;
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
		return "OverTime [overTimeFormId=" + overTimeFormId + ", empId=" + empId + ", empName=" + empName
				+ ", empDepartment=" + empDepartment + ", empDeptno=" + empDeptno + ", empJob=" + empJob
				+ ", overTimeDate=" + overTimeDate + ", overTimeHour=" + overTimeHour + ", overTimeType=" + overTimeType
				+ ", overTimeTypeId=" + overTimeTypeId + ", overTimeTypeForDay=" + overTimeTypeForDay
				+ ", overTimeTypeForDayId=" + overTimeTypeForDayId + ", overTimeReason=" + overTimeReason
				+ ", verifyState=" + verifyState + ", overTimeCheckReason=" + overTimeCheckReason + "]";
	}

	
	
	
	
	
	
	

}
