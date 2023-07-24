package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserDAO {
	UserModel finByUserNameAndPassWordAndStatus(String userName,String passWord,Integer status);
}
