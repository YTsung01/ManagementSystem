package com.example.service;

public enum LoginStatus {
	
	//當員工存在且密碼正確
	EXIST_EMP_CORRECT_PASSWORD,
	//當員工存在但是密碼錯誤
	EXIST_EMP_WRONG_PASSWORD,
	//當員工存在且levelID==2正確
	EXIST_EMP_EMP_LEVEL_2,
	//當員工不存在
	NON_EXIST_EMP,

}
