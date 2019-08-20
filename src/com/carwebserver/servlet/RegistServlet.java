package com.carwebserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carwebserver.car.User;
import com.carwebserver.daoImpl.UserDaoImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 重写doPost方法
		// 获取数据库的操作
		UserDaoImpl dao = new UserDaoImpl();
		PrintWriter out = response.getWriter();
		// 设置请求的编码格式
		request.setCharacterEncoding("UTF-8");
		// 1.获取请求
		String json = request.getParameter("RegistRequest");// 获取请求参数request.getParameter("requestParam")

		System.out.println("RegistServlet的请求URL----->" + json);

		// 2.将请求到的参数进行Json解析
		JSONObject jsonObject = JSONObject.fromObject(json);
		String phone = jsonObject.getString("telephone");
		String name = jsonObject.getString("name");
		String answer = jsonObject.getString("answer");
		String question = jsonObject.getString("question");
		String password = jsonObject.getString("password");
		String deviceid = jsonObject.getString("deviceid");
		String level = jsonObject.getString("level");
		String headimg = "0";
		/***************** 以上是手机号、用户名、密码、设备id、等级 *****************/

		System.out.println("快要对数据库操作！");
		// 在未添加时,查询电话,判断是否存在
		User u = dao.selectPhone(phone);
		// 如果u!=null，说明存在,否则说明不存在
		if (u != null) {

			// 将查询出来的结果,打包成Json格式传到客户端
			JSONObject jObject = new JSONObject();
			// 存在返回结果是0
			jObject.put("result", 0);
			out.println(jObject.toString());

		} else {
			// 将查询出来的结果,打包成Json格式传到客户端
			JSONObject jObject = new JSONObject();
			// 先将数据录入到数据库
			dao.insert(name, password, phone, level, headimg, question, answer, deviceid);
			// 再查询一下是否存在
			User user = dao.selectPhone(phone);
			// 得到查询结果的用户id,传到客户端id和-1
			jObject.put("user_id", user.getId());
			jObject.put("image", user.getHead());
			jObject.put("result", 1);
			out.println(jObject.toString());
		}

		out.flush();
		out.close();

	}
}
