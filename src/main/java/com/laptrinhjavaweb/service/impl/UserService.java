package com.laptrinhjavaweb.service.impl;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService{
	@Inject
	private IUserDAO userDao;
	@Override
	public UserModel finByUserNameAndPassWordAndStatus(String userName, String passWord, Integer status) {
		return userDao.finByUserNameAndPassWordAndStatus(userName, passWord, status);
	}

}
