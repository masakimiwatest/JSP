package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Manager;
import dao.ManagerDAO;

public class ManagerChange extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String map = "";

		RequestDispatcher dispatcher = request.getRequestDispatcher(map);

		// 必要な変数を取得する
		int managerId = (Integer) request.getAttribute("managerId");
		String managerUserId = (String) request.getAttribute("managerUserId");
		int managerLevel = (Integer) request.getAttribute("managerLevel");
		String managerPassword = (String) request.getAttribute("managerPassword");

		// beanに入れる
		Manager managerObj = new Manager();
		managerObj.setManagerId(managerId);
		managerObj.setManagerUserId(managerUserId);
		managerObj.setManagerLevel(managerLevel);
		managerObj.setManagerPassword(managerPassword);

		ManagerDAO ManagerDAO = new ManagerDAO();

		int line = 0;
		try {
			line = ManagerDAO.update(managerObj);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if (line == 1) {// 変更成功
			map = "ManagerList";// ManagerList.javaに飛ばす
								// ManagerList.java:DB内の管理者表示サーブレット
			dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);

		} else {// 変更失敗
			map = "Manager-List.jsp";
			dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);
		}
	}
}