package com.example.dao;

import com.example.entity.OverTime;

import java.util.List;
import java.util.Optional;

import com.example.entity.EmpBook;

public interface OverTimeDao {
	
		//1. 新增加班申請
		int addOverTime(OverTime overTime);
		
		//2. 依據empId查詢使用者
		Optional<EmpBook>findEmpByEmpId(Integer empId);
		
		//3. 依據empId查詢加班資料
		List<OverTime> findOverTimeByEmpId(Integer empId);
		
		//4. 依據empId查詢已經審核過的加班資料
		List<OverTime> findCheckoutOverTimeFormByEmpId(Integer empId);
		
		//5. 依據formid以及審核狀態修改加班(注意!! 不能修改已經審核過的申請單)
		int updateOverTimeByFormId(String formId, OverTime overTime);
		
		//6. 依照FormId取消加班申請
		int cancelOverTimeByFormId(String formId);

		//7. 依據DeptNo查詢部門的加班資料
		List<OverTime> findAllOverTimeByDeptNo(Integer empDeptno);
		
		//8. 依照empId查詢尚未審核的加班資料
		List<OverTime> findNonCheckoutOverTimeFormByEmpId(Integer empId);

}
