package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.NewsModel;

public class NewMapper implements RowMapper<NewsModel> {

	@Override
	public NewsModel mappRow(ResultSet resultSet) { 
		try {
			NewsModel newsModel = new NewsModel();
			newsModel.setId(resultSet.getLong("id"));
			newsModel.setContent(resultSet.getString("content"));
			newsModel.setShortDescription(resultSet.getString("shortdescription"));
			newsModel.setCategoryId(resultSet.getLong("categoryid"));
			newsModel.setTitle(resultSet.getString("title"));
			newsModel.setThumbnail(resultSet.getString("thumbnail"));
			newsModel.setCreatedBy(resultSet.getString("createdby"));
			newsModel.setCreatedDate(resultSet.getTimestamp("createddate"));
			if (resultSet.getTimestamp("modifieddate") != null) {
				newsModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			if (resultSet.getString("modifiedby") != null) {
				newsModel.setModifiedBy(resultSet.getString("modifiedby"));
			}
			return newsModel;
		} catch (SQLException e) {
			return null;
		}

	}

}
