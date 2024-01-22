package com.example.service;

import java.util.Optional;

import com.example.entity.EmpBook;

public interface LogIn {
	
	LoginStatus getLoginStatusByEmpBook(EmpBook empBook,Optional<EmpBook> empOpt)throws Exception;

}
