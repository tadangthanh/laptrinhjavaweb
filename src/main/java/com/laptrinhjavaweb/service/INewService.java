package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.opensymphony.module.sitemesh.Page;

public interface INewService {
	List<NewsModel> findByCategoryId(Long categoryId);

	NewsModel save(NewsModel newModal);

	void delete(Long[] id);

	NewsModel update(NewsModel model);
	List<NewsModel> findAll(Pageble  pageble);
	int getTotalItem(Pageble  pageble);
	NewsModel finOne(Long id);
}
