package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserService {
	UserModel finByUserNameAndPassWordAndStatus(String userName, String passWord, Integer status);
}
