package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel>{

	@Override
	public CategoryModel mappRow(ResultSet resultSet) {
		try {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setCode(resultSet.getString("CODE"));
			categoryModel.setId(resultSet.getLong("id"));
			categoryModel.setName(resultSet.getString("name")); 
			return categoryModel;
		} catch (SQLException e) {
			// TODO: handle exception
			return null;
		}
	}
	
}
