package bean;

import java.sql.Date;
import java.sql.Timestamp;

//Entity
public class CheckIn {
	
	// 一般欄位
		private Integer empId;
		private String empName;
		private String empDepartment;
		private String empJob;
		private String checkInTime;

		public CheckIn() {
			
		}

		public CheckIn(Integer empId, String empName, String empDepartment, String empJob, String checkInTime) {
			super();
			this.empId = empId;
			this.empName = empName;
			this.empDepartment = empDepartment;
			this.empJob = empJob;
			this.checkInTime = checkInTime;
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

		public String getEmpJob() {
			return empJob;
		}

		public void setEmpJob(String empJob) {
			this.empJob = empJob;
		}

		public String getCheckInTime() {
			return checkInTime;
		}

		public void setCheckInTime(String checkInTime) {
			this.checkInTime = checkInTime;
		}
		
		

}