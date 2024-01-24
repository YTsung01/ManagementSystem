package com.example.service;

import java.util.Optional;

import com.example.entity.EmpBook;

public interface MainPage {

	MainPageStatus getMainPageStatusByEmpBook(EmpBook empBook,Optional<EmpBook> empBossOpt);
	
}
