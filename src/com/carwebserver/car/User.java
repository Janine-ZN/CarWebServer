package com.carwebserver.car;

/**
 * 用于封装User表的实体类
 * 
 * @author zhaonan
 */
public class User {
	// 编号
	private int id;
	// 密码
	private String password;
	// 密码
	private String name;
	// 用户电话
	private String phonenum;
	// 设备ID
	private String deviceid;
	// 用户等级
	private String level;
	// 用户头像
	private String head;
	// 行业
	private String buiness;
	// 职业
	private String job;
	// 问题
	private String question;
	// 答案
	private String answer;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getBuiness() {
		return buiness;
	}

	public void setBuiness(String buiness) {
		this.buiness = buiness;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
