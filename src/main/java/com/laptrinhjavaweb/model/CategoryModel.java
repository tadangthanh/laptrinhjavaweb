package com.laptrinhjavaweb.model;

public class CategoryModel extends AbstractModel<CategoryModel> {
	private String name;
	private String code;
	public CategoryModel() {
		// TODO Auto-generated constructor stub
	}
	public CategoryModel(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
