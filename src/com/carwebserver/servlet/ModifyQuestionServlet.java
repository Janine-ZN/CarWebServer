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
 * Servlet implementation class ModifyQuestionServlet
 */
@WebServlet("/ModifyQuestionServlet")
public class ModifyQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyQuestionServlet() {
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
		UserDaoImpl dao = new UserDaoImpl();
		response.setCharacterEncoding("UTF-8");;
		PrintWriter out = response.getWriter();
		// 设置请求的编码格式
		request.setCharacterEncoding("UTF-8");
		// 1.获取请求
		String json = request.getParameter("ModifyQuestionRequest");

		System.out.println("ModifyQuestionServlet的请求URL----->" + json);

		// 2.将请求到的参数进行Json解析
		JSONObject jsonObject = JSONObject.fromObject(json);
		int userid = Integer.parseInt(jsonObject.getString("user_id"));
		String question = jsonObject.getString("question");
		String answer = jsonObject.getString("answer");
		System.out.println("ModifyQuestionServlet.userid.question.answer--->" + question + "," + answer);

		// 将解析出来的参数，传到数据库
		int u = dao.updateQuestion(userid, question, answer);
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
