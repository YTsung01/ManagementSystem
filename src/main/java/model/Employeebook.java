package model;

import java.util.Date;

public class Employeebook {
	
	private Integer id;        //員工編號
	private String nickname;   //名稱
	private String department; //部門
	private Date date;         //時間

	
	
	public Employeebook() {}


	public Employeebook(Integer id, String nickname, String department,  Date date) {
	
		this.id = id;
		this.nickname = nickname;
		this.department = department;
		this.date = date;

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


	public String getDepartment() {
		return department;
	}




	public void setDepartment(String department) {
		this.department = department;
	}




	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}




	

}
