package com.example.model.entity;

import java.sql.Date;
import java.util.List;

/**
2. 使用者

 +-------+---------+----------+--------+---------------+--------+---------------------+---------+
 | empId | empName | password | empSex | empDepartment | empJob |      CheckInTime    | LevelId |
 +-------+---------+----------+--------+---------------+--------+---------------------+---------+
 |  101  |  Solar  | pass101  | Female |      sale     |Engineer|'2023-12-12 16:12:39'|    1    |
 |  102  | MoonByul| pass102  |  male  |      sale     |  Boss  |'2023-12-12 16:12:39'|    2    |
 |  103  |  WheeIn | pass103  | Female |      sale     |Engineer|'2023-12-12 16:12:39'|    1    |
 |  104  |  Hwasa  | pass104  |  male  |      sale     |Engineer|'2023-12-12 16:12:39'|    1    |
 +-------+---------+----------+--------+---------------+--------+---------------------+---------+

*/
//  overTimeLeftHour, overTimeTotalHour, takeoffTotalHours, empAcator

public class Employee {
	
	private Integer empId;        			//員工代號
	private String empName;       			// 員工名稱
	private String empPassword;   			// 使用者密碼
	private String empSex;        			// 性別
	private String empDepartment; 			// 部門
	private Integer empDeptno;    			// 部門代號
	private String empJob;        			// 職位
	private Integer levelId;     		 	//員工職等
	private Date hireDate;        			//員工入職時間
	private Float salary;        		 	//員工薪資
	
	private Integer overTimeLeftHour;       //加班剩餘時數
	private Integer overTimeTotalHour;      //加班總時數
	private Integer takeoffTotalHours;      //請假總時數
	private String empAcator;               // 員工大頭照
	

	
	private List<Service> services;
	
	public Employee() {
		
	}

	public Employee(Integer empId, String empName, String empPassword, String empSex, String empDepartment,
			Integer empDeptno, String empJob, Integer levelId, Date hireDate, Float salary, Integer overTimeLeftHour,
			Integer overTimeTotalHour, Integer takeoffTotalHours, String empAcator, List<Service> services) {
		super();
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
		this.services = services;
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

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
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

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empPassword=" + empPassword + ", empSex="
				+ empSex + ", empDepartment=" + empDepartment + ", empDeptno=" + empDeptno + ", empJob=" + empJob
				+ ", levelId=" + levelId + ", hireDate=" + hireDate + ", salary=" + salary + ", overTimeLeftHour="
				+ overTimeLeftHour + ", overTimeTotalHour=" + overTimeTotalHour + ", takeoffTotalHours="
				+ takeoffTotalHours + ", empAcator=" + empAcator + ", services=" + services + "]";
	}

	

}



	