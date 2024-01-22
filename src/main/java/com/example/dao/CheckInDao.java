package com.example.dao;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.entity.CheckIn;

public interface CheckInDao {
	
	//1. 新增上班時間
	int addCheckIn(CheckIn checkIn);
	
	//2. 新增下班時間
	int addCheckOut(CheckIn checkIn);
	
	//3. 依據empId查詢所有的打卡紀錄 
	List<CheckIn> findAllCheckInByEmpId(Integer empId);
	
	//4. 依據empId查詢指定日期(含當日)的打卡紀錄
	List<CheckIn> findTodayCheckInByEmpId(Integer empId, Date date);

	//5. 依據empDeptno查詢部門所有的打卡紀錄
	List<CheckIn> findAllCheckInByDeptNo(Integer empDeptno);
	
	//6. 依據empDeptno查詢部門定日期(含當日)的打卡紀錄
	List<CheckIn> findTodayCheckInByDeptNo(Integer empDeptno, Date date);
	
	//8. 統計上班遲到次數
	int countLateCheckIns(Integer empId);
	
	//9.  查詢最近一次打卡紀錄
	Optional<CheckIn> findLatestCheckInByEmpId(Integer empId);

	//11. 上班時間限制
	boolean isOnTime(CheckIn checkIn);
	
	//12. 查詢員工打卡紀錄(根據起始日期與員工ID)
	List<CheckIn> findAllCheckInByEmpIdAndStartDateAndEndDate(Integer empId,Date startDate,Date endDate);
}
