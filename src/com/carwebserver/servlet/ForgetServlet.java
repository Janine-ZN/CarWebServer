package com.carwebserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carwebserver.car.Question;
import com.carwebserver.car.User;
import com.carwebserver.dao.UserDao;
import com.carwebserver.daoImpl.QuestionDaoImpl;
import com.carwebserver.daoImpl.UserDaoImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ForgetServlet
 */
@WebServlet("/ForgetServlet")
public class ForgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgetServlet() {
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
		UserDao dao = (UserDao) new UserDaoImpl();
		// 设置响应的编码格式
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 设置请求的编码格式
		request.setCharacterEncoding("UTF-8");
		// 1.获取请求
		String json = request.getParameter("forgetRequest");// 获取请求参数request.getParameter("requestParam")

		System.out.println("Forget的JSON数据---------->" + json);

		// 2.将请求到的参数进行Json解析
		JSONObject jsonObject = JSONObject.fromObject(json);
		// 解析出三个数据,用户ID,问题和答案
		String telephone = jsonObject.getString("telephone");
		String question = jsonObject.getString("question");// 问题
		String answer = jsonObject.getString("answer");// 答案

		// 将解析出来的参数，到数据库中查询
		User userQuestion = dao.selectQuestion(telephone, question, answer);// 查询用户名、密码
		if (userQuestion != null) {
			// 将查询出来的结果,打包成Json格式传到客户端
			JSONObject jObject = new JSONObject();
			jObject.put("result", 0);
			jObject.put("user_id", userQuestion.getId());
			out.println(jObject.toString());
			System.out.println("----Janine.Z:jObject.toString()---->" + jObject.toString());
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
