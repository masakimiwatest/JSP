package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Manager;
import dao.ManagerDAO;

public class ManagerCreate extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("ManagerCreate.javaなう");

		String map = "";

		RequestDispatcher dispatcher = request.getRequestDispatcher(map);

		String managerUserId = (String) request.getAttribute("managerUserId");

		int managerLevel = (Integer) request.getAttribute("managerLevel");
		String managerPassword = (String) request.getAttribute("managerPassword");

		// beanに入れる
		Manager managerObj = new Manager();
		managerObj.setManagerUserId(managerUserId);
		managerObj.setManagerLevel(managerLevel);
		managerObj.setManagerPassword(managerPassword);

		ManagerDAO ManagerDAO = new ManagerDAO();

		int lineTranSearch = 0;
		try {
			lineTranSearch = ManagerDAO.transearch(managerUserId);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if (lineTranSearch == 0) {// かぶりが無い
			int lineInsert = 0;
			try {
				lineInsert = ManagerDAO.insert(managerObj);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			if (lineInsert == 1) {// 新規登録成功

				System.out.println("登録成功");
				map = "ManagerList";// ManagerList.javaに飛ばす
				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
			}

		} else {// かぶり有り//新規登録失敗

			System.out.println("新規登録失敗");
			map = "Manager-Create.jsp";
			dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);
		}
	}
}