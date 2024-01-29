package com.example.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

//Entity
public class TakeOff {

	// 一般欄位
	String formId; // 請假申請單單號
	Integer agent; // 代理人Id
	Integer takeoffType; // 請假類型'1:特休 2:事假 3:病假 4:喪假 5:公假'

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	Date startTime; // 請假開始的時間

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	Date endTime; // 請假結束的時間

	String reason; // 請假事由
	Integer verifyState; // 請假審核結果 (0:未通過 1:通過 2:審核中)
	String checkReason; // 審核結果是否通過原因
	Integer takeoffDay; // 請假天數
	Integer takeoffHour; // 請假時數

	EmpBook empBook;
	Attachement attachement;

	public TakeOff() {

	}

	public TakeOff(String formId, Integer agent, Integer takeoffType, Date startTime, Date endTime, String reason,
			Integer verifyState, String checkReason, Integer takeoffDay, Integer takeoffHour, EmpBook empBook,Attachement attachement) {

		this.formId = formId;
		this.agent = agent;
		this.takeoffType = takeoffType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.reason = reason;
		this.verifyState = verifyState;
		this.checkReason = checkReason;
		this.takeoffDay = takeoffDay;
		this.takeoffHour = takeoffHour;
		this.empBook = empBook;
		this.attachement = attachement;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public Integer getAgent() {
		return agent;
	}

	public void setAgent(Integer agent) {
		this.agent = agent;
	}

	public Integer getTakeoffType() {
		return takeoffType;
	}

	public void setTakeoffType(Integer takeoffType) {
		this.takeoffType = takeoffType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(Integer verifyState) {
		this.verifyState = verifyState;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public Integer getTakeoffDay() {
		return takeoffDay;
	}

	public void setTakeoffDay(Integer takeoffDay) {
		this.takeoffDay = takeoffDay;
	}

	public Integer getTakeoffHour() {
		return takeoffHour;
	}

	public void setTakeoffHour(Integer takeoffHour) {
		this.takeoffHour = takeoffHour;
	}

	public EmpBook getEmpBook() {
		return empBook;
	}

	public void setEmpBook(EmpBook empBook) {
		this.empBook = empBook;
	}

	public Attachement getAttachement() {
		return attachement;
	}

	public void setAttachement(Attachement attachement) {
		this.attachement = attachement;
	}

	@Override
	public String toString() {
		return "TakeOff [formId=" + formId + ", agent=" + agent + ", takeoffType=" + takeoffType + ", startTime="
				+ startTime + ", endTime=" + endTime + ", reason=" + reason + ", verifyState=" + verifyState
				+ ", checkReason=" + checkReason + ", takeoffDay=" + takeoffDay + ", takeoffHour=" + takeoffHour
				+ ", empBook=" + empBook + "]";
	}

}