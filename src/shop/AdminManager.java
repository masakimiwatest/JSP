package shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import dao.AdminDAO;

public class AdminManager extends HttpServlet {
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminDAO adminDao = new AdminDAO();
		List<Admin> adminList = null;

		try {
			adminList = adminDao.allSearch();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.setAttribute("adminList", adminList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminManager.jsp");
		dispatcher.forward(request, response);
	}
}
