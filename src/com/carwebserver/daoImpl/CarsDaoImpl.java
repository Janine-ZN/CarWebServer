package com.carwebserver.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.carwebserver.car.Cars;
import com.carwebserver.dao.CarsDao;
import com.carwebserver.jdbc.JDBCTools;

public class CarsDaoImpl implements CarsDao {

	/**
	 * 获取数据库的连接,查询语句的静态代码块
	 * 
	 * @param sql
	 */
	public Cars insert(Object... args) {

		Cars cars = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			String sql = "INSERT INTO tb_map(map_ip,map_deviceid,map_longitude,map_latitude,map_precision,map_address,map_method,map_time)"
					+ "VALUES(?,?,?,?,?,?,?,?)";
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
		return cars;

	}

	/**
	 * 获取数据库的连接,查询语句的静态代码块
	 * 
	 * @param sql
	 */
	public List<Cars> Taxiselect(String deviceid, String lan, String lon) {

		ResultSet rs = null;
		Connection conn = null;
		List<Cars> list = new ArrayList<Cars>();
		PreparedStatement preparedStatement = null;

		double lan1 = Double.parseDouble(lan) - 0.15;
		double lan2 = Double.parseDouble(lan) + 0.15;
		double lon1 = Double.parseDouble(lon) - 0.15;
		double lon2 = Double.parseDouble(lon) + 0.15;
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			String sql = "SELECT * FROM (SELECT map_id, map_deviceid, map_longitude, map_latitude, map_time"
					+ " FROM tb_map" + " where map_time like '%14%' and ( map_longitude> ? and  map_longitude < ?  ) "
					+ " and ( map_latitude> ? and  map_latitude < ?  ) order by map_time desc) "
					+ " AS a where map_deviceid != ? GROUP BY map_deviceid";
			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 设置查询参数
			preparedStatement.setString(1, Double.toString(lon1));
			preparedStatement.setString(2, Double.toString(lon2));
			preparedStatement.setString(3, Double.toString(lan1));
			preparedStatement.setString(4, Double.toString(lan2));
			preparedStatement.setString(5, deviceid);

			System.out.println("3.sql语句:" + preparedStatement);

			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();
			// 5.处理ResultSet
			while (rs.next()) {

				Cars c = new Cars();
				c.setId(rs.getInt("map_id"));
				c.setDeviceid(rs.getString("map_deviceid"));
				c.setLongitude(rs.getString("map_longitude"));// 获取数据库里的经度
				c.setLatitude(rs.getString("map_latitude"));// 获取数据库里的纬度
				c.setTime(rs.getString("map_time"));

				// 添加到list中
				list.add(c);// 布尔型
				System.out.println("Clist:" + list.add(c));
			}
			// 输出的结果是Clist:[Cars [第一条数据的字段=字段值],Cars [第二条数据的字段=字段值]...]
			System.out.println("Clist:" + list);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(rs, preparedStatement, conn);
		}
		return list;

	}

}
