package com.carwebserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carwebserver.car.Persons;
import com.carwebserver.daoImpl.PersonDaoImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class FrientServlet
 */
@WebServlet("/FrientServlet")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FriendServlet() {
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
		// 获取关于数据库的操作
		PersonDaoImpl dao = new PersonDaoImpl();
		PrintWriter out = response.getWriter();
		// 设置请求的编码格式
		request.setCharacterEncoding("UTF-8");
		// 1.获取请求
		String json = request.getParameter("friendRequest");

		System.out.println("3.FriendServlet的请求URL----->" + json);

		// 2.将请求到的参数进行Json解析
		JSONObject jsonObject = JSONObject.fromObject(json);

		String deviceid = jsonObject.getString("deviceid");
		String content = jsonObject.getString("content");
		// System.out.println("deviceid" + deviceid);
		// System.out.println("content" + content);

		JSONArray jsonArray = new JSONArray(content);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsoncontent2 = new JSONObject(jsonArray.getString(i));
			// System.out.println(jsoncontent2.getString("name"));
			String name = jsoncontent2.getString("name");
			String number = jsoncontent2.getString("number");
			// 将解析出来的参数，传到数据库
			Persons persons = dao.selectResultSet(deviceid, number);
			if (persons == null) {
				// 将查询出来的结果,打包成Json格式传到客户端
				dao.insert(deviceid, name, number);

			}
		}

		out.flush();
		out.close();
	}
}
