package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.mapper.UserMapeper;
import com.laptrinhjavaweb.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel finByUserNameAndPassWordAndStatus(String userName, String passWord, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user INNER JOIN role ON user.roleid = role.id");
		sql.append(" WHERE user.username=? AND user.password=? and user.STATUS=?");
		//SELECT * FROM user INNER JOIN role ON user.roleid = role.id WHERE user.username="admin" AND user.password=123456
		List<UserModel> users= query(sql.toString(), new UserMapeper(), userName,passWord,status);
		return users.isEmpty() ? null : users.get(0);
	}

}
