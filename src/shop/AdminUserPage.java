package shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Review;
import bean.User;
import dao.ReviewDAO;
import dao.UserDAO;

public class AdminUserPage extends HttpServlet {
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserDAO userDao = new UserDAO();
		User useObj = new User();
		ReviewDAO reviewDao = new ReviewDAO();
		List<Review> reviewList = null;
		int userId = 0;
		if(request.getAttribute("userId") == null) {
			 userId =Integer.parseInt(request.getParameter("userId"));
		}else {
			 userId =(int)request.getAttribute("userId");
		}


		try {
			useObj = userDao.idSearch(userId);
			reviewList = reviewDao.userIdSearch(userId);
		} catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		request.setAttribute("userObj",useObj);
		request.setAttribute("reviewList",reviewList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminUserAction.jsp");
		dispatcher.forward(request, response);
	}
}
