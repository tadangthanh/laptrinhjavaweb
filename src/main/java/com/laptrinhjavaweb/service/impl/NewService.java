package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.InewDAO;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewService;

public class NewService implements INewService {

	@Inject
	private InewDAO newDao;
	@Inject
	private ICategoryDAO categoryDAO;

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		return newDao.findByCategoryId(categoryId);
	}

	@Override 
	public NewsModel save(NewsModel newModal) {
		newModal.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		newModal.setCategoryId(categoryDAO.findOneByCode(newModal.getCategoryCode()).getId());
		Long newId = newDao.save(newModal);
		return newDao.findOne(newId);
	}

	@Override
	public void delete(Long[] id) {
		for (int i = 0; i < id.length; i++) {
			//delete comment trước khi delete newss
			newDao.delete(id[i]);
		}
	}

	@Override
	public NewsModel update(NewsModel updateNews) {
		NewsModel oldNews = newDao.findOne(updateNews.getId());
		updateNews.setCreatedBy(oldNews.getCreatedBy());
		updateNews.setCreatedDate(oldNews.getCreatedDate());
		updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		updateNews.setCategoryId(categoryDAO.findOneByCode(updateNews.getCategoryCode()).getId());
		newDao.update(updateNews);
		return newDao.findOne(updateNews.getId());
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		return newDao.findAll(pageble);
	}

	@Override
	public int getTotalItem(Pageble  pageble) {
		return newDao.getTotalItem(pageble);
	}

	@Override
	public NewsModel finOne(Long id) {
		NewsModel news= newDao.findOne(id);
		news.setCategoryCode(categoryDAO.findOne(news.getCategoryId()).getCode());
		return news;
	}

}
