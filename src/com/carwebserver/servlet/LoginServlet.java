package com.carwebserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carwebserver.car.User;
import com.carwebserver.dao.UserDao;
import com.carwebserver.daoImpl.UserDaoImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
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
		// 获取关于数据库的操作
		UserDao dao = (UserDao) new UserDaoImpl();
		// 设置响应的编码格式
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 设置请求的编码格式
		request.setCharacterEncoding("UTF-8");
		// 1.获取请求
		String json = request.getParameter("loginRequest");

		System.out.println("LoginServlet的请求URL----->" + json);

		// 2.将请求到的参数进行Json解析
		JSONObject jsonObject = JSONObject.fromObject(json);
		String account = jsonObject.getString("account");
		String password = jsonObject.getString("password");
		if (mat_cher(account) == 1) {
			// 将解析出来的参数，传到数据库
			User up = dao.selectUphone(account, password);// 查询用户名、密码
			if (up != null) {
				// 将查询出来的结果,打包成Json格式传到客户端
				JSONObject jObject = new JSONObject();
				jObject.put("user_id", up.getId());
				jObject.put("username", up.getName());
				jObject.put("phone", up.getPhonenum());
				jObject.put("image", up.getHead());
				jObject.put("result", 0);
				// dao.updateName(nameorphone, up.getId());// 更新昵称，，，暂时不做修改
				System.out.println("LoginServlet.telephone.phone--->" + up.getPhonenum());
				out.println(jObject.toString());
				System.out.println("LoginServlet.toString--->" + jObject.toString());
			} else {
				// 将查询出来的结果,打包成Json格式传到客户端
				JSONObject jObject = new JSONObject();
				jObject.put("result", -1);

				out.println(jObject.toString());
			}
		} else {
			// 将解析出来的参数，传到数据库
			User u = dao.selectUser(account, password);// 查询用户名、密码
			if (u != null) {
				// 将查询出来的结果,打包成Json格式传到客户端
				JSONObject jObject = new JSONObject();
				jObject.put("user_id", u.getId());
				jObject.put("username", u.getName());
				jObject.put("phone", u.getPhonenum());
				jObject.put("image", u.getHead());
				jObject.put("result", 0);
				System.out.println("LoginServlet.user.phone--->" + u.getPhonenum());
				// dao.updateName(nameorphone, u.getId());// 更新昵称，，，暂时不做修改

				out.println(jObject.toString());
				System.out.println("LoginServlet.toString--->" + jObject.toString());
			} else {
				// 将查询出来的结果,打包成Json格式传到客户端
				JSONObject jObject = new JSONObject();
				jObject.put("result", -1);

				out.println(jObject.toString());
			}
		}
		out.flush();
		out.close();

	}

	/**
	 * 手机号格式验证
	 * 
	 * @param mobile
	 * @return
	 */
	private int mat_cher(String mobile) {

		String line = mobile;
		String pattern = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";

		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		if (m.find()) {
			return 1;
		} else {
			return 0;
		}

	}

}
