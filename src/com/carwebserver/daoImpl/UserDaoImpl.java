package com.carwebserver.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.carwebserver.car.User;
import com.carwebserver.dao.UserDao;
import com.carwebserver.jdbc.JDBCTools;

public class UserDaoImpl implements UserDao {

	User user = null;
	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement preparedStatement = null;

	/**
	 * 修改密保问题
	 * 
	 * @param id
	 * @param question
	 * @param answer
	 * @return
	 */
	public int updateQuestion(int id, String question, String answer) {

		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写更新SQL语句
			String sql = "UPDATE tb_user SET security_problem = ? , security_answer=? where user_id=?";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置参数
			preparedStatement.setString(1, question);
			preparedStatement.setString(2, answer);
			preparedStatement.setInt(3, id);

			// 4.执行查询，得到ResultSet
			int result = preparedStatement.executeUpdate();

			// 判断是否更新成功
			if (result > 0) {

				System.out.println("result=" + 0);

				// 更新成功返回0
				return 0;
			} else {

				System.out.println("result=" + (-1));

				// 更新失败返回-1
				return -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(null, preparedStatement, conn);
		}
		return -1;

	}

	/**
	 * 查询密保问题
	 */
	public User selectAll(int userid) {
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写查询SQL语句
			String sql = " select * from tb_user" + " where user_id=? ";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置查询参数
			preparedStatement.setInt(1, userid);

			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();

			// 5.处理ResultSet
			if (rs.next()) {

				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setQuestion(rs.getString("security_problem"));
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

	/**
	 * 登录时,查询用户名、密码
	 */
	public User selectUser(String name, String password) {
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写查询SQL语句
			String sql = " select * from tb_user" + " where user_name=? and user_password=?";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置查询参数
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);

			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();

			// 5.处理ResultSet
			if (rs.next()) {

				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPhonenum(rs.getString("user_phonenum"));
				user.setHead(rs.getString("user_head"));
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

	/**
	 * 登录时,查询用户名、密码
	 */
	public User selectUphone(String phone, String password) {
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写查询SQL语句
			String sql = " select * from tb_user" + " where user_phonenum=? and user_password=?";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置查询参数
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, password);

			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();

			// 5.处理ResultSet
			if (rs.next()) {

				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPhonenum(rs.getString("user_phonenum"));
				user.setHead(rs.getString("user_head"));

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

	/**
	 * 获取数据库的连接,新增语句的静态代码块
	 * 
	 * @param sql
	 */
	public User insert(Object... args) {

		// 获取id=5的customers数据表的记录，并打印
		User user = null;
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写新增SQL语句name, password, phone, level, question, answer, deviceid
			String sql = "INSERT INTO tb_user(user_name,user_password,user_phonenum,user_level,user_head,security_problem,security_answer,map_deviceid)"
					+ "VALUES(?,?,?,?,?,?,?,?)";
			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.循环添加数据参数(即设置参数)
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}

			// 4.执行preparedStatement
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(null, preparedStatement, conn);
		}
		return user;
	}

	/**
	 * 修改用户手机号
	 * 
	 * @param id
	 * @param telephone
	 * @return
	 */
	public int updateTelephone(int id, String telephone) {

		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写更新SQL语句
			String sql = "UPDATE tb_user SET user_phonenum = ?  where user_id=?";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置参数
			preparedStatement.setString(1, telephone);
			preparedStatement.setInt(2, id);

			// 4.执行查询，得到ResultSet
			int result = preparedStatement.executeUpdate();

			// 判断是否更新成功
			if (result > 0) {

				System.out.println("result=" + 0);

				// 更新成功返回0
				return 0;
			} else {

				System.out.println("result=" + (-1));

				// 更新失败返回-1
				return -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(null, preparedStatement, conn);
		}
		return -1;

	}

	/**
	 * 修改用户密码
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public int updatePassword(int id, String password) {

		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写更新SQL语句
			String sql = "UPDATE tb_user SET user_password = ?  where user_id=?";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置参数
			preparedStatement.setString(1, password);
			preparedStatement.setInt(2, id);

			// 4.执行查询，得到ResultSet
			int result = preparedStatement.executeUpdate();

			// 判断是否更新成功
			if (result > 0) {

				System.out.println("result=" + 0);

				// 更新成功返回0
				return 0;
			} else {

				System.out.println("result=" + (-1));

				// 更新失败返回-1
				return -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(null, preparedStatement, conn);
		}
		return -1;

	}

	/**
	 * 更新图片
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public int updateImage(int id, String image) {

		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写更新SQL语句
			String sql = "UPDATE tb_user SET user_head = ?  where user_id=?";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置参数
			preparedStatement.setString(1, image);
			preparedStatement.setInt(2, id);

			// 4.执行查询，得到ResultSet
			int result = preparedStatement.executeUpdate();

			// 判断是否更新成功
			if (result > 0) {

				System.out.println("result=" + 0);

				// 更新成功返回0
				return 0;
			} else {

				System.out.println("result=" + (-1));

				// 更新失败返回-1
				return -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(null, preparedStatement, conn);
		}
		return -1;

	}

	/**
	 * 用户忘记密码，进行修改
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public int forgetUpdate(String phone, String password) {

		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写更新SQL语句
			String sql = "UPDATE tb_user SET user_password = ? where user_phonenum=?";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置参数
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, phone);

			// 4.执行查询，得到ResultSet
			int result = preparedStatement.executeUpdate();

			// 判断是否更新成功
			if (result > 0) {

				System.out.println("result=" + 0);

				// 更新成功返回0
				return 0;
			} else {

				System.out.println("result=" + (-1));

				// 更新失败返回-1
				return -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(null, preparedStatement, conn);
		}
		return -1;

	}

	/**
	 * 更新昵称
	 */
	public int updateName(String username, int userid) {

		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写更新SQL语句
			String sql = "UPDATE tb_user SET  user_name=?  where user_id= ?";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置参数
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, userid);

			// 4.执行查询，得到ResultSet
			int result = preparedStatement.executeUpdate();

			// 判断是否更新成功
			if (result > 0) {

				System.out.println("result=" + 0);

				return 0;
			} else {

				System.out.println("result=" + (-1));

				return -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(null, preparedStatement, conn);
		}
		return -1;

	}

	/**
	 * 注册时,查询手机号
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
			String sql = "select * from tb_user where user_phonenum=?";

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
				user.setPhonenum(rs.getString("user_phonenum"));
				user.setHead(rs.getString("user_head"));

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

	/**
	 * 查询密保问题和答案,返回用户的id号
	 */
	public User selectQuestion(String telephone, String question, String answer) {
		ResultSet rs = null;
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写查询SQL语句
			String sql = " select * from tb_user"
					+ " where user_phonenum=? and security_problem=? and security_answer=? ";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置查询参数
			preparedStatement.setString(1, telephone);
			preparedStatement.setString(2, question);
			preparedStatement.setString(3, answer);
			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();

			// 5.处理ResultSet
			if (rs.next()) {

				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setQuestion(rs.getString("security_problem"));
				user.setAnswer(rs.getString("security_answer"));

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
