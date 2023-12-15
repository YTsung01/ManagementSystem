package model;

import java.util.List;

/**
 * 定義上下班表單
 * 新增與查詢
 * 修改?
 * */

public interface EmployeebookDao {
	
	void create(Employeebook employeebook);
	List<Employeebook> readAll();

}
