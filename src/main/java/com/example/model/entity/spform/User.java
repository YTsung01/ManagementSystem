package com.example.model.entity.spform;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;

// 對應 spring form 的表單資訊
public class User {
	private Integer empId; // 員工編號
	
	@NotEmpty(message = "{user.name.notempty}")
	@Size(min = 3, max = 10, message = "{user.name.size}")
	private String empDept; // 部門
	private String empName; // 姓名

	@NotNull(message = "{user.birth.notnull}")
	@Past(message = "{user.birth.past}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date leaveStart; // 請假開始
	private Date leaveEnd;	// 請假結束
	@Size(max = 1000, message = "{user.resume.size}")
	private String leaveReason; // 請假事由
	
	@NotNull(message = "{user.educationId.notnull}")
	private Integer agentId; // 代理人Id(給表單用)
	private AgentData agent; // 代理人(給 User Spring List 呈現用)
	
	@NotNull(message = "{user.educationId.notnull}")
	private Integer leaveDayTypeId; // 請假類別Id(給表單用)
	private LeaveDayType leaveDayType; // 請假類別(給 User Spring List 呈現用)
	
	
	// ---------------------------------------------------------
	
	

	public Integer getEmpId() {
		return empId;
	}


	public void setEmpId(Integer empId) {
		this.empId = empId;
	}


	public String getEmpDept() {
		return empDept;
	}


	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public Date getLeaveStart() {
		return leaveStart;
	}


	public void setLeaveStart(Date leaveStart) {
		this.leaveStart = leaveStart;
	}


	public Date getLeaveEnd() {
		return leaveEnd;
	}


	public void setLeaveEnd(Date leaveEnd) {
		this.leaveEnd = leaveEnd;
	}


	public String leaveReason() {
		return leaveReason;
	}


	public void setleaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}


	public Integer getAgentId() {
		return agentId;
	}


	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}


	public AgentData getAgent() {
		return agent;
	}


	public void setAgent(AgentData agent) {
		this.agent = agent;
	}


	public Integer getLeaveDayTypeId() {
		return leaveDayTypeId;
	}


	public void setLeaveDayTypeId(Integer leaveDayTypeId) {
		this.leaveDayTypeId = leaveDayTypeId;
	}


	public LeaveDayType getLeaveDayType() {
		return leaveDayType;
	}


	public void setLeaveDayType(LeaveDayType leaveDayType) {
		this.leaveDayType = leaveDayType;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	
}
