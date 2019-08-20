package com.carwebserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carwebserver.car.Question;
import com.carwebserver.daoImpl.QuestionDaoImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueServlet
 */
@WebServlet("/QueServlet")
public class QueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		QuestionDaoImpl dao = new QuestionDaoImpl();
		PrintWriter out = response.getWriter();
		// 设置请求的编码格式
		request.setCharacterEncoding("UTF-8");
		// 1.获取请求
		String json = request.getParameter("queRequest");// 获取请求参数request.getParameter("requestParam")

		System.out.println("Que的JSON数据---------->" + json);

		// 2.将请求到的参数进行Json解析
		JSONObject jsonObject = JSONObject.fromObject(json);
		// 解析出三个数据,用户ID,问题和答案
		String user_id = jsonObject.getString("user_id");

		// 添加成功后,查询是否存在
		Question q = dao.selectQue(user_id);
		// 若不为空，说明存在密保问题,无需添加
		if (q != null) {
			// 将查询出来的结果,打包成Json格式传到客户端
			JSONObject jObject = new JSONObject();
			jObject.put("result", 0);
			out.println(jObject.toString());

			System.out.println("回传的JSON数据0:---->" + jObject.toString());
		} else {
			// 将查询出来的结果,打包成Json格式传到客户端
			JSONObject jObject = new JSONObject();
			jObject.put("result", -1);
			out.println(jObject.toString());

			System.out.println("回传的JSON数据1:---->" + jObject.toString());

		}
		out.flush();
		out.close();
	}
}
