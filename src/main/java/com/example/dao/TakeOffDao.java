package com.example.dao;


import com.example.entity.TakeOff;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.entity.EmpBook;
import com.example.entity.OverTime;

public interface TakeOffDao {
	
		//1. 新增請假申請
		int addTakeOff(TakeOff takeOff);
		
		//2. 依據empId查詢使用者
		Optional<EmpBook>findEmpByEmpId(Integer empId);
		
		//3. 依據empId查詢請假資料
		List<TakeOff> findTakeOffByEmpId(Integer empId);
		
		//4. 依據empId查詢已經審核過的加班資料
		List<TakeOff> findCheckoutTakeOffByEmpId(Integer empId);
		
		//5. 依據formId修改請假(注意!! 不能修改已經審核過的申請單)
		int updateTakeOffByEmpId(String formId, TakeOff takeOff);
		
		//6. 依照FormId取消請假申請
		int cancelTakeOffByFormId(String formId);

		//7. 依據DeptNo查詢部門的請假資料
		List<TakeOff> findAllTakeOffByDeptNo(Integer empDeptno);
		
		//8. 依照empId查詢尚未審核的請假資料
		List<TakeOff> findNonCheckoutTakeOffFormByEmpId(Integer empId);
		
		// 9. 查詢員工請假紀錄(根據起始日期與員工ID)
		List<TakeOff> findAllTakeOffByEmpIdAndStartDateAndEndDate(Integer empId, Date startDate, Date endDate);
		
		// 10. 依照formId查詢請假表單
		Optional<TakeOff> findTakeOffByFormId(String formId);
<<<<<<< HEAD

		// 11.依照formId 同意請假狀態 verifyState = 1
		int passTakeOffByFormId(String formId);

		// 12.依照formId 不同意請假狀態 verifyState = 0
		int falseTakeOffByFormId(String formId, String checkReason);
=======
>>>>>>> branch 'master' of https://github.com/YTsung01/ManagementSystem.git

		// 11.依照formId 同意請假狀態 verifyState = 1
		int passTakeOffByFormId(String formId);

		// 12.依照formId 不同意請假狀態 verifyState = 0
		int falseTakeOffByFormId(String formId, String checkReason);
		

}