package com.example.dao;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.entity.CheckIn;
import com.example.entity.Form;

@Repository
public class CheckInDaoImpl implements CheckInDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// 1. 新增上班時間
	@Override
	public int addCheckIn(CheckIn checkIn) {
		String sql = "INSERT INTO checkin (empId) VALUES (?)";
		return jdbcTemplate.update(sql, checkIn.getEmpId());
	}

	// 2. 新增下班時間
	// update checkin set checkOutTime = current_timestamp() where empId=101;
	@Override
	public int addCheckOut(Integer empId ) {
		String sql = "update checkin set checkOutTime = current_timestamp() where empId = ? ORDER BY checkInTime DESC "
				+ "LIMIT 1";
		return jdbcTemplate.update(sql,empId );
	}

	// 3. 依據empId查詢所有的上班紀錄
	// SELECT empid, checkInTime FROM checkin WHERE DATE(checkinTime) = '2024-01-20'
	@Override
	public List<CheckIn> findAllCheckInByEmpId(Integer empId) {
		String sql = "SELECT empId,checkInTime,checkOutTime FROM checkIn WHERE empId = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CheckIn.class), empId);
	}

	// 4. 依據empId查詢當日的上班紀錄(含當日)
	@Override
	public List<CheckIn> findTodayCheckInByEmpId(Integer empId, Date date) {
		String sql = "SELECT empId,checkInTime,checkOutTime FROM checkIn WHERE empId = ? and DATE(checkinTime) = ?  ORDER BY checkInId DESC ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CheckIn.class), empId, date);
	}

	// 5. 依據empDeptno查詢部門所有的打卡紀錄
	// select emp.empid, emp.empDeptno,c.checkintime from empbook emp, checkin c
	// where emp.empId = c.empId and empDeptno=1;
	@Override
	public List<CheckIn> findAllCheckInByDeptNo(Integer empDeptno) {
		String sql = "select emp.empid, emp.empDeptno,c.checkInTime,c.checkOutTime from empbook emp, checkin c where emp.empId = c.empId and empDeptno=?;";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CheckIn.class), empDeptno);
	}

	// 6. 依據empDeptno查詢部門當日的打卡紀錄
	// select emp.empid, emp.empDeptno,c.checkintime from empbook emp, checkin c
	// where emp.empId = c.empId and empDeptno=1 and DATE(checkinTime) =
	// '2024-01-20';
	@Override
	public List<CheckIn> findTodayCheckInByDeptNo(Integer empDeptno, Date date) {
		String sql = "select emp.empid, emp.empDeptno,c.checkInTime,c.,checkOutTime from empbook emp, checkin c where emp.empId = c.empId and empDeptno=? and DATE(checkinTime) = ?;";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CheckIn.class), empDeptno, date);
	}

	// 8. 統計上班遲到次數(還沒限定只能計算當月份)
	// SELECT COUNT(*) FROM checkIn WHERE empId = 102 AND TIME(checkInTime) >
	// '09:00:00';
	@Override
	public int countLateCheckIns(Integer empId) {
		// 定義上班時間為9點
		String sql = "SELECT COUNT(*) FROM checkIn WHERE empId = ? AND TIME(checkInTime) > '09:00'";
		return jdbcTemplate.queryForObject(sql, Integer.class, empId);
	}

	// 9. 查詢最近一次打卡紀錄
	@Override
	public Optional<CheckIn> findLatestCheckInByEmpId(Integer empId) {
		String sql = "select empid,checkInTime,checkOutTime from checkin WHERE empId = ? ORDER BY checkInTime DESC LIMIT 1";
		try {
			CheckIn checkIn = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(CheckIn.class), empId);
			return Optional.of(checkIn);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	// 11. 上班時間限制
	@Override
	public boolean isOnTime(CheckIn checkIn) {
		String sql = "SELECT empId, checkInTime FROM checkin WHERE empId = ? AND DATE(checkInTime) = CURRENT_DATE AND TIME(checkInTime) <= '09:00' ORDER BY checkInTime DESC LIMIT 1;";
		int rowCount = jdbcTemplate.queryForObject(sql, Integer.class, checkIn.getEmpId());

		// 遲到 ==0 , 準時==1
		return rowCount > 0;
	}
	
	//12.

	/**
	 * select c.checkInId, c.empId, c.checkInTime, c.checkOutTime from empbook emp, checkin c where emp.empId = c.empId  and emp.empId=101 and checkinTime BETWEEN '2024-01-18 15:40:41' AND '2024-01-18 15:40:41';
	 */
	@Override
	public List<CheckIn> findAllCheckInByEmpIdAndStartDateAndEndDate(Integer empId, Date startDate, Date endDate) {
		String sql = "select c.checkInId, c.empId, c.checkInTime, c.checkOutTime from empbook emp, checkin c where emp.empId = c.empId  and emp.empId=? and checkinTime BETWEEN ? AND ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CheckIn.class),empId,startDate, endDate);
	}
	

}
