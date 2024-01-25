package com.example.dao;


import com.example.entity.TakeOff;

import java.util.List;
import java.util.Optional;

import com.example.entity.EmpBook;

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

}
