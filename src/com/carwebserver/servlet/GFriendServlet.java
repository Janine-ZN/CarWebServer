package com.carwebserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class GFriendServlet
 */
@WebServlet("/GFriendServlet")
public class GFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GFriendServlet() {
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
		PersonDaoImpl pdao = new PersonDaoImpl();
		// 设置请求的编码格式
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		response.setContentType("text/json; charset=utf-8");
		// 1.获取请求
		String json = request.getParameter("GfriendRequest");

		System.out.println("1.GfriendRequest的请求URL----->" + json);

		// 2.将请求到的参数进行Json解析
		JSONObject jsonObject = JSONObject.fromObject(json);
		String deviceid = jsonObject.getString("deviceid");

		// 传回的数据如下
		// 将传来的数据，传到查询方法里
		List<Persons> plist = pdao.selectGoodFriend(deviceid);

		// 将查询结果打包成JSONArray数组
		JSONArray array = new JSONArray();

		// 假如不为空，即为真
		if (plist != null) {
			// 读出除自己之外的所有用户s
			for (int i = 0; i < plist.size(); i++) {

				// 1).一条条的从list中读出数据
				Persons p = plist.get(i);

				// 2).将查询出来的结果,打包成Json格式传到客户端
				JSONObject jObject = new JSONObject();

				// 3).cars.getDeviceid()获取设备ID的方法
				jObject.put("person_name", p.getName());
				jObject.put("person_phone", p.getPhonenum());
				jObject.put("map_deviceid", p.getDeviceid());
				// 4).将获取到的JObject对象加到array设数组中
				array.put(jObject.toString());// 加载JObject对象
				System.out.println("**array" + array);// 转换成array，无编码错误
			}
		}
		// 将array数组通过jObject传到客户端
		JSONObject jObject = new JSONObject();
		jObject.put("content", array);
		out.println(jObject.toString());

		out.flush();
		out.close();
	}
}
