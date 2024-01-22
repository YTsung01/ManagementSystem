package com.example.entity;

import java.util.Date;

//Entity
public class CheckIn {
	
	// 一般欄位
		private Integer empId;
		private Integer checkInId;
		private Date checkInTime;
		private Date checkOutTime;

		public CheckIn() {
			
		}
		

		public CheckIn(Integer empId, Integer checkInId, Date checkInTime, Date checkOutTime) {
			
			this.empId = empId;
			this.checkInId = checkInId;
			this.checkInTime = checkInTime;
			this.checkOutTime = checkOutTime;
		}


		public Integer getEmpId() {
			return empId;
		}

		public void setEmpId(Integer empId) {
			this.empId = empId;
		}

		public Integer getCheckInId() {
			return checkInId;
		}

		public void setCheckInId(Integer checkInId) {
			this.checkInId = checkInId;
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

		@Override
		public String toString() {
			return "CheckIn [empId=" + empId + ", checkInId=" + checkInId + ", checkInTime=" + checkInTime
					+ ", checkOutTime=" + checkOutTime + "]";
		}

}