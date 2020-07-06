package shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Review;
import dao.ReviewDAO;

public class UserReview extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ReviewDAO reviewDao = new ReviewDAO();
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		try {
			Review reviewObj = (Review) reviewDao.reviewIdSearch(reviewId);
			request.setAttribute("reviewObj", reviewObj);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("userReview.jsp");
		dispatcher.forward(request, response);

	}
}