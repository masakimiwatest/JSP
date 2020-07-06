package shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Review;
import bean.User;
import dao.ReviewDAO;

public class UserReviewList extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ReviewDAO reviewDao = new ReviewDAO();
		HttpSession session = request.getSession();

		if(session.getAttribute("UserObj") != null) {
			User user = (User)session.getAttribute("UserObj");
			int userId = user.getUserId();
			try {
				List<Review> reviewList =(List<Review>)reviewDao.userIdSearch(userId);
				request.setAttribute("reviewList", reviewList);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("userReviewList.jsp");
		dispatcher.forward(request, response);

	}
}