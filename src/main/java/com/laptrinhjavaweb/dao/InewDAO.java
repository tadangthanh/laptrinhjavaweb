package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface InewDAO {
	NewsModel findOne(Long id);
	List<NewsModel> findAll(Pageble pageble);

	List<NewsModel> findByCategoryId(Long categoryId);

	Long save(NewsModel newsModel);

	void delete(Long id);

	void update(NewsModel updateNews);
	int getTotalItem(Pageble pageble);
}
