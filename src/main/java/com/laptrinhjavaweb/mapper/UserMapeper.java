package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapeper implements RowMapper<UserModel> {

	@Override
	public UserModel mappRow(ResultSet resultSet) {
		try {
			UserModel userModel = new UserModel();
			userModel.setId(resultSet.getLong("id"));
			userModel.setUserName(resultSet.getString("username"));
			userModel.setFullName(resultSet.getString("fullname"));
			userModel.setPassWord(resultSet.getString("password"));
			userModel.setStatus(resultSet.getInt("STATUS"));
			userModel.setRoleId(resultSet.getLong("roleid"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(resultSet.getString("CODE"));
				role.setName(resultSet.getString("NAME"));
				userModel.setRole(role);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			return userModel;
		} catch (SQLException e) {
			return null;
		}
	}

}
