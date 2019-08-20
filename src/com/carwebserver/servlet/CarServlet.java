package com.carwebserver.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carwebserver.daoImpl.CarsDaoImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class CarServlet
 */
@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 重写doPost方法
		// 获取数据库的操作
		CarsDaoImpl dao = new CarsDaoImpl();
		// 设置请求的编码格式
		request.setCharacterEncoding("UTF-8");
		// 1.获取请求
		String json = request.getParameter("carRequest");// 获取请求参数request.getParameter("requestParam")

		// System.out.println("请求的URL---------->" + json);

		// 2.将请求到的参数进行Json解析
		JSONObject jsonObject = JSONObject.fromObject(json);
		String deviceid = jsonObject.getString("deviceid");
		String longitude = jsonObject.getString("longitude");//经度，正确
		String latitude = jsonObject.getString("latitude");//纬度，正确
		String precision = jsonObject.getString("precision");
		String address = jsonObject.getString("address");
		String method = jsonObject.getString("method");

		// 获取当前具体时间
		// Date time = new Date();//获取时间对象
		// Date time = Long.toString(new Date().getTime());

		// time.getHours();//获取当前时
		// time.getMinutes();//获取当前分
		// time.getSeconds();//获取当前秒

		// 获取客户端的手机IP
		String ip = request.getRemoteAddr();
		// 将解析出来的参数，传到数据库
		// System.out.println("快要对数据库操作！");

		dao.insert(ip,deviceid, longitude, latitude, precision, address, method,
				Long.toString(System.currentTimeMillis()));
		// System.out.println("获取系统毫秒数方法2："+Long.toString(System.currentTimeMillis()));
		// System.out.println("获取系统毫秒数方法2："+time);
	}

}
