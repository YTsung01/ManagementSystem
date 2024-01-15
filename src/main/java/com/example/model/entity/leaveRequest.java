package com.example.model.entity;

import java.sql.Date;

public class leaveRequest {
	
	private Integer leaveRequestFormId;    				        //請假申請單單號
	private Integer empId;        								//員工代號
	private String empName;       								//員工名稱
	private String empDepartment; 								//部門
	private Integer empDeptno;    								//部門代號
	private String empJob;        								//職位
	
	private Date leaveRequestDate;               				//請假日期
	private Integer leaveRequestHour;            				//請假時數
	private OverTimeTypeData leaveRequestType;             		//請假類型(加班/補修)
	private Integer leaveRequestTypeId;             			//請假類型Id(給表單用)
	private OverTimeTypeForSalaryData leaveRequestTypeForDay;   //請假類型(平/假日 加班費不同)
	private Integer leaveRequestTypeForDayId;    				//請假類型Id(給表單用)
	private String leaveRequestReason;           				//請假事由
	private Integer verifyState;           						//請假審核結果 (通過:1 /不通過:0 )
	private String leaveRequestCheckReason;           			//審核結果是否通過原因 
	
	public leaveRequest() {
		
	}


	

}
