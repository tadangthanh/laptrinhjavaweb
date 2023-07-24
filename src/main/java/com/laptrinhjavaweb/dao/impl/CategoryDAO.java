package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {
	@Override
	public List<CategoryModel> findAll() {
		String sql = "select * from category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(Long id) {
		String sql = "select * from category where id=?";
		return query(sql, new CategoryMapper(), id).isEmpty() ? null : query(sql, new CategoryMapper(), id).get(0);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql=  "select * from category where code=?";
		return query(sql, new CategoryMapper(), code).isEmpty() ? null : query(sql, new CategoryMapper(), code).get(0);
	}

}
