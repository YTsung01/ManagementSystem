package com.example.model.entity;

import java.sql.Date;
import java.sql.Timestamp;

//Entity
public class OverTimeTypeData {
	
	// 一般欄位
		private Integer empId;
		private String empName;
		
		public OverTimeTypeData() {}

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

		@Override
		public String toString() {
			return "OverTimeTypeData [empId=" + empId + ", empName=" + empName + "]";
		}
		

}