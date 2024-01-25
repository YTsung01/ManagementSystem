package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.EmpBookDao;
import com.example.entity.EmpBook;

@Service
public class MainPageImpl implements MainPage {

	@Autowired
	EmpBookDao empBookDao;

	@Override
	public MainPageStatus getMainPageStatusByEmpBook(EmpBook empBook,Optional<EmpBook> empBossOpt) {
		if (empBossOpt.isPresent() && empBook.getLevelId() == 1) {
			return MainPageStatus.EXIST_BOSS_EMP_LEVEL_1;
		} else if(empBossOpt.isPresent() && empBook.getLevelId() == 2) {
			return MainPageStatus.EXIST_BOSS_EMP_LEVEL_2;
		} else {
			return MainPageStatus.NON_EXIST_BOSS;
		}
	}

}
