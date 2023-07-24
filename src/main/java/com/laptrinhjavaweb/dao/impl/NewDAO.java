package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.InewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public class NewDAO extends AbstractDAO<NewsModel> implements InewDAO {

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		String sql = "select * from news where categoryid=?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewsModel newsModel) {
		String sql = "insert into news (title,thumbnail,shortdescription,content,categoryid,createddate,createdby) values(?,?,?,?,?,?,?)";
		return insert(sql, newsModel.getTitle(), newsModel.getThumbnail(), newsModel.getShortDescription(),
				newsModel.getContent(), newsModel.getCategoryId(), newsModel.getCreatedDate(),
				newsModel.getCreatedBy());
	}

	@Override
	public void delete(Long id) {
		String sql = "delete from news where id=?";
		delete(sql, id);
	}

	@Override
	public void update(NewsModel newsModel) {
		StringBuilder sql = new StringBuilder("update news set title=?,thumbnail=?,");
		sql.append("shortdescription=?,content=?,categoryid=?,");
		sql.append("createddate=?,createdby=? where id=?");
		super.update(sql.toString(), newsModel.getTitle(), newsModel.getThumbnail(), newsModel.getShortDescription(),
				newsModel.getContent(), newsModel.getCategoryId(), newsModel.getCreatedDate(), newsModel.getCreatedBy(),
				newsModel.getId());
	}

	@Override
	public NewsModel findOne(Long id) {
		String sql = "select * from news where id=?";
		return query(sql, new NewMapper(), id).isEmpty() ? null : query(sql, new NewMapper(), id).get(0);
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("select * from news");
		if (pageble.getSearchValue() != null) {
			sql.append(" where title like \"%" + pageble.getSearchValue() + "%\" ");
		}
		if (pageble.getSorter() != null) {
			sql.append(" order by " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + " ");
		}

		sql.append(" limit " + pageble.getLimit() + " offset " + pageble.getOffset() + " ");

		System.out.println(sql.toString());
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem(Pageble pageble) {
		StringBuilder sql = new StringBuilder("select count(*) from news ");
		if (pageble.getSearchValue() != null) {
			sql.append(" where title like \"%" + pageble.getSearchValue() + "%\" ");
		}
		return count(sql.toString());
	}

}
