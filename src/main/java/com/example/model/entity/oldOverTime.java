package com.example.model.entity;


import java.sql.Timestamp;
import java.util.Date;

public class oldOverTime {
	private String overTimeFormId;    				            // 加班申請單單號
	private Date overTimeDate;               				// 加班申請日期
	private Integer empId;        								//員工代號
	private String empName;       								// 員工名稱
	private String empDepartment; 								// 部門
	private Integer empDeptno;    								// 部門代號
	private String empJob;        								// 職位
	
	private Date overTimeStart;               					// 加班開始的時間
	private Date overTimeEnd;               					// 加班結束的時間
	private Integer overTimeHour;            					// 加班時數
	private Integer overTimeLeftHour;            				// 加班剩餘時數
	private oldOverTimeTypeData overTimeType;             			// 加班類型(加班/補修)
	private Integer overTimeTypeId;             				// 加班類型Id(給表單用)
	private oldOverTimeTypeForDayData overTimeTypeForDay;       // 加班類型(平/假日 加班費不同)
	private Integer overTimeTypeForDayId;    				    // 加班類型Id(給表單用)
	private String overTimeReason;           					// 加班事由
	private Integer verifyState;           						// 加班審核結果 (通過:1 /不通過:0 )
	private String overTimeCheckReason;           				// 審核結果是否通過原因 
	
	private oldEmployee employee;            						//員工物件(關聯欄位)
	
	public oldOverTime() {
		
	}

	public oldOverTime(String overTimeFormId, Timestamp overTimeDate, Integer empId, String empName, String empDepartment,
			Integer empDeptno, String empJob, Date overTimeStart, Date overTimeEnd, Integer overTimeHour,
			Integer overTimeLeftHour, oldOverTimeTypeData overTimeType, Integer overTimeTypeId,
			oldOverTimeTypeForDayData overTimeTypeForDay, Integer overTimeTypeForDayId, String overTimeReason,
			Integer verifyState, String overTimeCheckReason, oldEmployee employee) {
		this.overTimeFormId = overTimeFormId;
		this.overTimeDate = overTimeDate;
		this.empId = empId;
		this.empName = empName;
		this.empDepartment = empDepartment;
		this.empDeptno = empDeptno;
		this.empJob = empJob;
		this.overTimeStart = overTimeStart;
		this.overTimeEnd = overTimeEnd;
		this.overTimeHour = overTimeHour;
		this.overTimeLeftHour = overTimeLeftHour;
		this.overTimeType = overTimeType;
		this.overTimeTypeId = overTimeTypeId;
		this.overTimeTypeForDay = overTimeTypeForDay;
		this.overTimeTypeForDayId = overTimeTypeForDayId;
		this.overTimeReason = overTimeReason;
		this.verifyState = verifyState;
		this.overTimeCheckReason = overTimeCheckReason;
		this.employee = employee;
	}

	public String getOverTimeFormId() {
		return overTimeFormId;
	}

	public void setOverTimeFormId(String overTimeFormId) {
		this.overTimeFormId = overTimeFormId;
	}

	

	public Date getOverTimeDate() {
		return overTimeDate;
	}

	public void setOverTimeDate(Date overTimeDate) {
		this.overTimeDate = overTimeDate;
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

	public Date getOverTimeStart() {
		return overTimeStart;
	}

	public void setOverTimeStart(Date overTimeStart) {
		this.overTimeStart = overTimeStart;
	}

	public Date getOverTimeEnd() {
		return overTimeEnd;
	}

	public void setOverTimeEnd(Date overTimeEnd) {
		this.overTimeEnd = overTimeEnd;
	}

	public Integer getOverTimeHour() {
		return overTimeHour;
	}

	public void setOverTimeHour(Integer overTimeHour) {
		this.overTimeHour = overTimeHour;
	}

	public Integer getOverTimeLeftHour() {
		return overTimeLeftHour;
	}

	public void setOverTimeLeftHour(Integer overTimeLeftHour) {
		this.overTimeLeftHour = overTimeLeftHour;
	}

	public oldOverTimeTypeData getOverTimeType() {
		return overTimeType;
	}

	public void setOverTimeType(oldOverTimeTypeData overTimeType) {
		this.overTimeType = overTimeType;
	}

	public Integer getOverTimeTypeId() {
		return overTimeTypeId;
	}

	public void setOverTimeTypeId(Integer overTimeTypeId) {
		this.overTimeTypeId = overTimeTypeId;
	}

	public oldOverTimeTypeForDayData getOverTimeTypeForDay() {
		return overTimeTypeForDay;
	}

	public void setOverTimeTypeForDay(oldOverTimeTypeForDayData overTimeTypeForDay) {
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

	public oldEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(oldEmployee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "OverTime [overTimeFormId=" + overTimeFormId + ", overTimeDate=" + overTimeDate + ", empId=" + empId
				+ ", empName=" + empName + ", empDepartment=" + empDepartment + ", empDeptno=" + empDeptno + ", empJob="
				+ empJob + ", overTimeStart=" + overTimeStart + ", overTimeEnd=" + overTimeEnd + ", overTimeHour="
				+ overTimeHour + ", overTimeLeftHour=" + overTimeLeftHour + ", overTimeType=" + overTimeType
				+ ", overTimeTypeId=" + overTimeTypeId + ", overTimeTypeForDay=" + overTimeTypeForDay
				+ ", overTimeTypeForDayId=" + overTimeTypeForDayId + ", overTimeReason=" + overTimeReason
				+ ", verifyState=" + verifyState + ", overTimeCheckReason=" + overTimeCheckReason + ", employee="
				+ employee + "]";
	}

	

}