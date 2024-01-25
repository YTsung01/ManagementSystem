package com.example.model.entity;

import java.util.Date;

//Entity
public class oldCheckIn {
	
	// 一般欄位
		private Integer empId;
		private String empName;
		private String empDepartment;
		private Integer empDetno;
		private String empJob;
		private Date checkInTime;
		private Date checkOutTime;
		private Date todayDate;
		

		public oldCheckIn() {
			
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

		public Integer getEmpDetno() {
			return empDetno;
		}

		public void setEmpDetno(Integer empDetno) {
			this.empDetno = empDetno;
		}

		public String getEmpJob() {
			return empJob;
		}

		public void setEmpJob(String empJob) {
			this.empJob = empJob;
		}

		public Date getCheckInTime() {
			return checkInTime;
		}

		public void setCheckInTime(Date checkInTime) {
			this.checkInTime = checkInTime;
		}

		public Date getCheckOutTime() {
			return checkOutTime;
		}

		public void setCheckOutTime(Date checkOutTime) {
			this.checkOutTime = checkOutTime;
		}

		public Date getTodayDate() {
			return todayDate;
		}

		public void setTodayDate(Date todayDate) {
			this.todayDate = todayDate;
		}

		@Override
		public String toString() {
			return "CheckIn [empId=" + empId + ", empName=" + empName + ", empDepartment=" + empDepartment
					+ ", empDetno=" + empDetno + ", empJob=" + empJob + ", checkInTime=" + checkInTime
					+ ", checkOutTime=" + checkOutTime + ", todayDate=" + todayDate + "]";
		}

		

		
		

		
		

}