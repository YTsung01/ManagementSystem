package com.example.model.entity;

//Entity
public class oldOverTimeTypeData {
	
	// 一般欄位
		protected Integer id;
		protected String name;
		
		
		public oldOverTimeTypeData() {}
		
		

		public Integer getId() {
			return id;
		}



		public void setId(Integer id) {
			this.id = id;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		@Override
		public String toString() {
			return "OverTimeTypeData [id=" + id + ", name=" + name + "]";
		}
		
		



}