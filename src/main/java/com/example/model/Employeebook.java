package com.example.model;

import java.util.Date;

public class Employeebook extends Object{
	
	private Integer id;        //員工編號
	private String nickname;   //名稱
	private String sex;        //性別
	private String department; //部門
	private String job;        //職位
	private Date date;         //時間
	private Double salary;      //薪水
	private Double bonus;       //加薪or減薪
	
	
	public Employeebook() {
		
	}




	public Employeebook(Integer id, String nickname, String sex, String department, String job, Date date, double d,
			double e) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.sex = sex;
		this.department = department;
		this.job = job;
		this.date = date;
		this.salary = d;
		this.bonus = e;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getNickname() {
		return nickname;
	}




	public void setNickname(String nickname) {
		this.nickname = nickname;
	}




	public String getSex() {
		return sex;
	}




	public void setSex(String sex) {
		this.sex = sex;
	}




	public String getDepartment() {
		return department;
	}




	public void setDepartment(String department) {
		this.department = department;
	}




	public String getJob() {
		return job;
	}




	public void setJob(String job) {
		this.job = job;
	}




	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}




	public Double getSalary() {
		return salary;
	}




	public void setSalary(Double salary) {
		this.salary = salary;
	}




	public Double getBonus() {
		return bonus;
	}




	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}




	
	

}
