package com.example.model.entity.spform;

public class AgentData {
	
	public AgentData() {}
	
	private Integer empId; // 員工編號
	private String empDept; // 部門
	private String empName; // 員工姓名
	
	public String getDisplay() {
		return empName + "(" + empId + ")";
	}
	

}
