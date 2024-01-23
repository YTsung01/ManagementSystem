package com.example.service;

import java.util.Base64;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.EmpBookDao;
import com.example.entity.EmpBook;
import com.example.util.KeyUtil;

@Service
public class LoginImpl implements LogIn{
	
	@Autowired
	EmpBookDao empBookDao;

	@Override
	public LoginStatus getLoginStatusByEmpBook(EmpBook empBook, Optional<EmpBook> empOpt,String password) throws Exception {
		if (empOpt.isPresent()) {
			empBook = empOpt.get();
			String EmpPassword = empBook.getEmpPassword();
			// 將 password 進行 AES 加密
			// -------------------------------------------------------------------
			final String KEY = KeyUtil.getSecretKey();
			SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
			byte[] encryptedPasswordECB = KeyUtil.encryptWithAESKey(aesKeySpec, password);
			String encryptedPasswordECBBase64 = Base64.getEncoder().encodeToString(encryptedPasswordECB);

			// -------------------------------------------------------------------------------------------
			if (EmpPassword.equals(encryptedPasswordECBBase64)) { 
				if (empBook.getLevelId() == 1) {
					return LoginStatus.EXIST_EMP_EMP_LEVEL_1;
				} else if(empBook.getLevelId() == 2){
					return LoginStatus.EXIST_EMP_EMP_LEVEL_2;
				}
			} else {
				return LoginStatus.EXIST_EMP_WRONG_PASSWORD;
			}
		} 
			return LoginStatus.NON_EXIST_EMP;
		
	}

}
