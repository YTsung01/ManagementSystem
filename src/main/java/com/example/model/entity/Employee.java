package com.example.model.entity;

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

public class Employee {
	
	private Integer empId;        //員工代號
	private String empName;       // 員工名稱
	private String emppassword;   // 使用者密碼
	private String empSex;        // 性別
	private String empDepartment; // 部門
	private String empJob;        // 職位
	private Integer levelId;        //員工職等
	
	public Employee() {
		
	}

	

	public Employee(Integer empId, String empName, String emppassword, String empSex, String empDepartment,
			String empJob, Integer levelId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.emppassword = emppassword;
		this.empSex = empSex;
		this.empDepartment = empDepartment;
		this.empJob = empJob;
		this.levelId = levelId;
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

	public String getEmppassword() {
		return emppassword;
	}

	public void setEmppassword(String emppassword) {
		this.emppassword = emppassword;
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
	
	
	
	
	
	

}
