package com.carwebserver.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.carwebserver.car.Question;
import com.carwebserver.dao.QuestionDao;
import com.carwebserver.jdbc.JDBCTools;

public class QuestionDaoImpl implements QuestionDao {
	Question question = null;
	Connection conn = null;
	PreparedStatement preparedStatement = null;

	public Question insert(Object... args) {

		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			String sql = "INSERT INTO tb_security(user_id,security_problem,security_answer,user_phonenum)"
					+ "VALUES(?,?,?,?)";
			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);
			// 循环添加数据参数
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}

			// 4.执行查询，得到ResultSet
			preparedStatement.executeUpdate();
			// System.out.println(sql);
			// 5.处理ResultSet
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(null, preparedStatement, conn);
		}
		return question;

	}

	/**
	 * 查询密保问题和答案
	 */
	public Question selectQuestion(String userid, String phone) {
		ResultSet rs = null;
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写查询SQL语句
			String sql = " select security_problem,security_answer from tb_security"
					+ " where user_id=? and user_phonenum=?";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置查询参数
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, phone);

			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();

			// 5.处理ResultSet
			if (rs.next()) {

				question = new Question();
				question.setQuestion(rs.getString("security_problem"));
				question.setAnswer(rs.getString("security_answer"));

			} else {
				System.out.println("result=" + (-1));
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(rs, preparedStatement, conn);
		}
		return question;
	}

	/**
	 * 查询密保问题和答案
	 */
	public Question selectQue(String userid) {
		ResultSet rs = null;
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写查询SQL语句
			String sql = " select * from tb_security" + " where user_id=?";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置查询参数
			preparedStatement.setString(1, userid);

			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();

			// 5.处理ResultSet
			if (rs.next()) {

				question = new Question();
				question.setQuestion(rs.getString("security_problem"));
				question.setAnswer(rs.getString("security_answer"));

			} else {
				System.out.println("result=" + (-1));
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(rs, preparedStatement, conn);
		}
		return question;
	}

	/**
	 * 查询密保问题和答案
	 */
	public Question selectForgetQ(String phone, String problem, String answer) {
		ResultSet rs = null;
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写查询SQL语句
			String sql = " select * from tb_security"
					+ " where user_phonenum=? and security_problem=? and security_answer=? ";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置查询参数
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, problem);
			preparedStatement.setString(3, answer);
			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();

			// 5.处理ResultSet
			if (rs.next()) {

				question = new Question();
				question.setQuestion(rs.getString("security_problem"));
				question.setAnswer(rs.getString("security_answer"));

			} else {
				System.out.println("result=" + (-1));
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(rs, preparedStatement, conn);
		}
		return question;
	}
}
