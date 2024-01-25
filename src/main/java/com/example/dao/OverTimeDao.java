package com.example.dao;

import com.example.entity.OverTime;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.entity.CheckIn;
import com.example.entity.EmpBook;

public interface OverTimeDao {

	// 1. 新增加班申請
	int addOverTime(OverTime overTime);

	// 2. 依據empId查詢使用者
	Optional<EmpBook> findEmpByEmpId(Integer empId);

	// 3. 依據empId查詢加班資料
	List<OverTime> findAllOverTimeByEmpId(Integer empId);

	// 4. 依據empId查詢已經審核過的加班資料
	List<OverTime> findCheckoutOverTimeFormByEmpId(Integer empId);

	// 5. 依據formid以及審核狀態修改加班(注意!! 不能修改已經審核過的申請單)
	int updateOverTimeByFormId(String formId, OverTime overTime);

	// 6. 依照FormId刪除加班申請
	int cancelOverTimeByFormId(String formId);

	// 7. 依據DeptNo查詢部門的加班資料
	List<OverTime> findAllOverTimeByDeptNo(Integer empDeptno);

	// 8. 依照empId查詢尚未審核的加班資料
	List<OverTime> findNonCheckoutOverTimeFormByEmpId(Integer empId);

	// 9. 查詢員工加班紀錄(根據起始日期與員工ID)
	List<OverTime> findAllOverTimeByEmpIdAndStartDateAndEndDate(Integer empId, Date startDate, Date endDate);

	// 10. 依照formId查詢加班表單
	Optional<OverTime> findOverTimeByFormId(String formId);

	// 11.依照formId 同意加班狀態 verifyState = 1
	int passOverTimeByFormId(String formId);

	// 12.依照formId 不同意加班狀態 verifyState = 0
	int falseOverTimeByFormId(String formId, String checkReason);

}
