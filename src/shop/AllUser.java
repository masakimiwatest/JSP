package shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDAO;
public class AllUser extends HttpServlet {
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserDAO userDao = new UserDAO();
		List<User> userList = null;

		try {
			userList = userDao.allSearch();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("userList",userList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("adminUser.jsp");
		dispatcher.forward(request, response);
	}
}
