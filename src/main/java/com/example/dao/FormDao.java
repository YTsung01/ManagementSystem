package com.example.dao;

import java.util.List;
import java.util.Optional;
import com.example.entity.Form;

public interface FormDao {
	
	//1. 新增表單
	int addForm(Form form);
	

	//2. 依據formId查找表單(單筆)
	Optional<Form> findFormByFormId(String FormId);
	
	//3. 依據empId查找其所有表單(多筆)
	List<Form> findAllFormsByEmpId(Integer empId);

	//4. 依據empId以及申請類型(type)查找表單(單筆)
	List<Form> findFormByEmpIdAndType(Integer empId,Integer type);
	
	//5. 查找所有表單
	List<Form> findAllForms();
	
	//6. 依據formId修改表單
	
	int updateFormByFormID(String formId, Form form);
	
	//7. 依據formId刪除表單
	
	int deleteFormByFormId(String formId);
	
	
	

}
