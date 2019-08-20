package com.carwebserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carwebserver.daoImpl.UserDaoImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ModifyTelephoneServlet
 */
@WebServlet("/ModifyTelephoneServlet")
public class ModifyTelephoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyTelephoneServlet() {
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
		// 参考修改密码
		UserDaoImpl dao = new UserDaoImpl();// 获取关于数据库的操作
		PrintWriter out = response.getWriter();

		request.setCharacterEncoding("UTF-8");// 设置请求的编码格式
		// 1.获取请求
		String json = request.getParameter("ModifyTelephoneRequest");

		System.out.println("----Janine.Z:ModifyTelephoneRequest.json---->" + json);

		// 2.将请求到的参数进行Json解析
		JSONObject jsonObject = JSONObject.fromObject(json);
		int userid = Integer.parseInt(jsonObject.getString("user_id"));
		String telephone = jsonObject.getString("telephone");

		System.out.println("----Janine.Z:userid,telephone--->" + userid + "," + telephone);

		// 将解析出来的参数，传到数据库
		int u = dao.updateTelephone(userid, telephone);
		if (u == 0) {
			// 将查询出来的结果,打包成Json格式传到客户端
			JSONObject jObject = new JSONObject();
			jObject.put("result", 0);

			out.println(jObject.toString());

		} else {
			// 将查询出来的结果,打包成Json格式传到客户端
			JSONObject jObject = new JSONObject();
			jObject.put("result", -1);

			out.println(jObject.toString());
		}
		out.flush();
		out.close();
	}

}
