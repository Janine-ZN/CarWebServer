package com.carwebserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carwebserver.car.Cars;
import com.carwebserver.car.Persons;
import com.carwebserver.daoImpl.CarsDaoImpl;
import com.carwebserver.daoImpl.PersonDaoImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class TaxiServlet
 */
@WebServlet("/TaxiServlet")
public class TaxiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaxiServlet() {
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
		CarsDaoImpl dao = new CarsDaoImpl();

		PrintWriter out = response.getWriter();
		// 设置请求的编码格式
		request.setCharacterEncoding("UTF-8");
		// 1.获取请求
		String json = request.getParameter("taxiRequest");

		System.out.println("1.请求的URL---------->" + json);

		// 2.将请求到的参数进行Json解析
		JSONObject jsonObject = JSONObject.fromObject(json);
		String deviceid = jsonObject.getString("deviceid");
		String lan = jsonObject.getString("lan");
		String lon = jsonObject.getString("lon");

		System.out.println("2.输出设备ID号:" + deviceid + ",纬度:" + lan + ",经度:" + lon);

		List<Cars> list = dao.Taxiselect(deviceid, lan, lon);
		List<Persons> plist = pdao.selectFriend(deviceid);

		JSONArray array = new JSONArray();
		// 假如不为空，即为真
		if (list != null) {
			// 读出除自己之外的所有用户s
			for (int i = 0; i < list.size(); i++) {

				// 1).一条条的从list中读出数据
				Cars cars = list.get(i);
				// System.out.println("list.get(i)" + list.get(i));

				// 2).将查询出来的结果,打包成Json格式传到客户端
				JSONObject jObject = new JSONObject();

				// 3).cars.getDeviceid()获取设备ID的方法
				jObject.put("map_deviceid", cars.getDeviceid());
				jObject.put("map_longitude", cars.getLongitude());
				jObject.put("map_latitude", cars.getLatitude());
				jObject.put("map_time", cars.getTime());

				// 读出好友用户
				if (plist != null) {
					int sum = 0;
					for (int j = 0; j < plist.size(); j++) {
						sum++;
						Persons p = plist.get(j);
						if (p.getDeviceid().equals(cars.getDeviceid())) {
							jObject.put("fTaxi", 1);
							break;
						} else if (sum == plist.size()) {
							jObject.put("fTaxi", 0);
						}

					}
					// 4).将获取到的JObject对象加到array设数组中
					array.put(jObject.toString());// 加载JObject对象
				}
			}
			JSONObject jObject = new JSONObject();

			jObject.put("result", 0);
			jObject.put("count", list.size());
			jObject.put("content", array);

			out.println(jObject.toString());
			System.out.println("jObject.toString():" + jObject.toString());
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
