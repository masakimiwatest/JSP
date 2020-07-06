package shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Admin;
import dao.AdminDAO;

public class AdminAction extends HttpServlet {
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AdminDAO adminDao = new AdminDAO();
		List<Admin> adminList = null;
		String action = request.getParameter("action");

		Admin adminObj = new Admin();

		int adminId = 0;
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");


		if (request.getParameter("adminId") != null) {
			adminId = Integer.parseInt(request.getParameter("adminId"));
		}

		try {
			switch (action) {
			case "削除":
				adminDao.delete(adminId);
				break;
			case "変更":
				System.out.println(adminId);
				adminObj.setAdminId(adminId);
				adminObj.setAdminName(adminName);
				adminObj.setAdminPassword(adminPassword);
				adminDao.update(adminObj);
				break;
			case "追加":
				adminObj.setAdminName(adminName);
				adminObj.setAdminPassword(adminPassword);
				adminDao.insert(adminObj);
				break;
			}
		} catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminManager");
		dispatcher.forward(request, response);
	}
}
