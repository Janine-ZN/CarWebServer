package com.carwebserver.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.carwebserver.car.User;
import com.carwebserver.jdbc.JDBCTools;

public class ForgetDaoImpl {
	
	/**
	 * 查询手机号
	 */
	public User selectPhone(String phone) {
		User user = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写查询SQL语句
			String sql = " select * from tb_user" + " where user_phonenum=?";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置查询参数
			preparedStatement.setString(1, phone);

			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();

			// 5.处理ResultSet
			if (rs.next()) {

				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				System.out.println("手机号已存在！");
				

			} else {
				System.out.println("result=" + (-1));
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(rs, preparedStatement, conn);
		}
		return user;
	}
}
