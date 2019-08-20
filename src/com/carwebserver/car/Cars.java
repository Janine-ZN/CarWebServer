package com.carwebserver.car;

/**
 * Cars 的实体集
 * @author zhaonan
 *
 */
public class Cars {
	private int id;
	private String ip;
	private String deviceid;
	private String longitude;
	private String latitude;
	private String precision;
	private String address;
	private String method;
	private String time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public Cars() {
	// TODO Auto-generated constructor stub
	}
	public Cars(int id, String ip, String deviceid, String longitude, String latitude, String precision,
			String address, String method, String time) {
		super();
		this.id = id;
		this.ip = ip;
		this.deviceid = deviceid;
		this.longitude = longitude;
		this.latitude = latitude;
		this.precision = precision;
		this.address = address;
		this.method = method;
		this.time = time;
	}
	@Override
	public String toString() {
		return "Cars [id=" + id + ", ip=" + ip + ",  deviceid=" + deviceid + ", longitude="
				+ longitude + ", latitude=" + latitude + ", precision=" + precision + ", address=" + address
				+ ", method=" + method + ", time=" + time + "]";
	}
	

}
