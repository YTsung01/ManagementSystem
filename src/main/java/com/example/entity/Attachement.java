package com.example.entity;

import java.util.Date;

public class Attachement {
	
	Integer attachId;
	String form_id;
	String filePath;
	Date createTime;
	Date updateTime;
	
	public Attachement() {}

	public Attachement(String form_id, String filePath, Date createTime, Date updateTime) {
		this.form_id = form_id;
		this.filePath = filePath;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
	public Attachement(Integer attachId, String form_id, String filePath, Date createTime, Date updateTime) {
		this.attachId = attachId;
		this.form_id = form_id;
		this.filePath = filePath;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Integer getAttachId() {
		return attachId;
	}

	public void setAttachId(Integer attachId) {
		this.attachId = attachId;
	}

	public String getForm_id() {
		return form_id;
	}

	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Attachement [attachId=" + attachId + ", form_id=" + form_id + ", filePath=" + filePath + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	
	
	

}
