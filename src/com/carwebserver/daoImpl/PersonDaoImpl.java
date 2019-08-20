package com.carwebserver.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.carwebserver.car.Persons;
import com.carwebserver.dao.PersonDao;
import com.carwebserver.jdbc.JDBCTools;

public class PersonDaoImpl implements PersonDao {

	/**
	 * 查询tb_person表中的设备ID和好友电话号码
	 * 
	 * @param deviceid
	 * @param number
	 * @return
	 */
	public Persons selectResultSet(String deviceid, String number) {

		ResultSet rs = null;
		Connection conn = null;
		Persons persons = null;
		PreparedStatement preparedStatement = null;

		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写查询的SQL语句
			String sql = " select * from tb_person" + " where map_deviceid=? and person_phone=?";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置查询参数
			preparedStatement.setString(1, deviceid);
			preparedStatement.setString(2, number);

			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();

			// 5.处理ResultSet
			if (rs.next()) {

				persons = new Persons();
				persons.setDeviceid(rs.getString("map_deviceid"));

			} else {
				System.out.println("result=" + (-1));
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(rs, preparedStatement, conn);
		}
		return persons;
	}

	/**
	 * 添加tb_person表
	 * 
	 * @param args
	 * @return
	 */
	public Persons insert(Object... args) {

		Persons persons = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();
			// 写新增SQL语句
			String sql = "INSERT INTO tb_person(map_deviceid,person_name,person_phone)" + "VALUES(?,?,?)";
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
		return persons;

	}

	/**
	 * 查询tb_person表和tb_user表同时存在的用户，即好友司机
	 * 
	 * @param deviceid
	 * @return
	 */
	public List<Persons> selectFriend(String deviceid) {

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;// 定义一个List<?>
		List<Persons> list = new ArrayList<Persons>();
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写查询的SQL语句
			String sql = " SELECT b.`map_deviceid` FROM `tb_person` as a , tb_user as b  WHERE a.`map_deviceid` =  ? and a.person_phone = b.user_phonenum";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置查询参数
			preparedStatement.setString(1, deviceid);

			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();
			System.out.println("@@@sql语句:" + preparedStatement);
			// 5.处理ResultSet
			// 循环输出数据
			while (rs.next()) {

				Persons persons = new Persons();
				persons.setDeviceid(rs.getString("map_deviceid"));
				
				// 将每行的数据依次添加到list中
				list.add(persons);// 布尔型
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(rs, preparedStatement, conn);
		}
		return list;
	}

	public List<Persons> selectGoodFriend(String deviceid) {

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;// 定义一个List<?>
		List<Persons> list = new ArrayList<Persons>();
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写查询的SQL语句
			String sql = " SELECT * FROM `tb_person` as a , tb_user as b  WHERE a.`map_deviceid` =  ? and a.person_phone = b.user_phonenum";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置查询参数
			preparedStatement.setString(1, deviceid);

			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();

			System.out.println("5.rs是:" + rs);
			// 5.处理ResultSet
			// 循环输出数据
			while (rs.next()) {

				Persons persons = new Persons();
				persons.setName(rs.getString("person_name"));
				persons.setPhonenum(rs.getString("person_phone"));
				persons.setDeviceid(rs.getString("map_deviceid"));

				// 将每行的数据依次添加到list中
				list.add(persons);// 布尔型
			}

			System.out.println("6.Plist:" + list);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(rs, preparedStatement, conn);
		}
		return list;
	}
}
