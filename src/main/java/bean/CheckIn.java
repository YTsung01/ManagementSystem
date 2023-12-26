package bean;

import java.sql.Timestamp;

//Entity
public class CheckIn {
	
	// 一般欄位
		private Integer empId;
		private String empName;
		private String empSex;
		private String empDepartment;
		private String empJob;
		private Timestamp checkInTime;

		public CheckIn() {
			
		}

		public CheckIn(Integer empId, String empName, String empSex, String empDepartment, String empJob,
				Timestamp checkInTime) {
			super();
			this.empId = empId;
			this.empName = empName;
			this.empSex = empSex;
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

		public Timestamp getCheckInTime() {
			return checkInTime;
		}

		public void setCheckInTime(Timestamp checkInTime) {
			this.checkInTime = checkInTime;
		}

		

		
		
		
}
