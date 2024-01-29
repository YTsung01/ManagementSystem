package com.example.dao;

import java.util.List;
import java.util.Optional;

import com.example.entity.EmpBook;
import com.example.entity.Form;

public interface FormDao {
	
	//1. 新增表單
	int addForm(Form form);
	

	//2. 依據formId查找表單(單筆)
	Optional<Form> findFormByFormId(String formId);
	
	//3. 依據empId查找其所有表單(多筆)
	List<Form> findAllFormsByEmpId(Integer empId);

	//4. 依據empId以及申請類型(type)查找表單(單筆)
	List<Form> findFormByEmpIdAndType(Integer empId,Integer type);
	
	//5. 查找所有表單
	List<Form> findAllForms();
	
	Optional<EmpBook> findEmpBookByFormId(String formId);
	
<<<<<<< HEAD
	//6.依據formId刪除表單
	public int cancelFormByFormId(String formId);
=======
	//6.
	public int cancelFormByFormId(String formId);
	
>>>>>>> branch 'master' of https://github.com/YTsung01/ManagementSystem.git
}
