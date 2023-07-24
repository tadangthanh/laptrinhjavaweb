package Test;

import java.sql.SQLException;

import com.laptrinhjavaweb.dao.impl.CategoryDAO;

public class Test {
	public static void main(String[] args) {
		CategoryDAO c = new CategoryDAO();
		System.out.println(c.getConnection());
		try {
			c.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
