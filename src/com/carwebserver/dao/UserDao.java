package com.carwebserver.dao;

import com.carwebserver.car.User;

/**
 * UesrDao 接口
 * 
 * @author zhaonan
 */
public interface UserDao {
	// 登录方法

	public int updateName(String username, int deviceid);

	public User selectUser(String username, String password);

	public User selectPhone(String phone);

	public User selectUphone(String phone, String password);

	public User selectAll(int userid);

	public User selectQuestion(String telephone, String question, String answer);

	public int updateImage(int parseInt, String image);

}
