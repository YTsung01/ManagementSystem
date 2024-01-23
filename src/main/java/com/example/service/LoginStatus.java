package com.example.service;

public enum LoginStatus {
	
	//當員工存在且levelID==1
	EXIST_EMP_EMP_LEVEL_1,
	//當員工存在但是密碼錯誤
	EXIST_EMP_WRONG_PASSWORD,
	//當員工存在且levelID==2
	EXIST_EMP_EMP_LEVEL_2,
	//當員工不存在
	NON_EXIST_EMP,

}
