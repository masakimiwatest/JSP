package shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReviewDAO;

public class ReviewAction extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ReviewDAO reviewDao = new ReviewDAO();
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String action = request.getParameter("action");
		String reviewText = request.getParameter("reviewText");
		try {
			if(action.equals("削除")) {
				reviewDao.delete(reviewId);
			}else {
				reviewDao.update(reviewText,reviewId);
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		RequestDispatcher dispatcher = request.getRequestDispatcher("UserReviewList");
		dispatcher.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ReviewDAO reviewDao = new ReviewDAO();
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String action = request.getParameter("action");
		String reviewText = request.getParameter("reviewText");
		String map = "";
		try {
			if(action.equals("削除")) {
				reviewDao.delete(reviewId);
			}else {
				reviewDao.update(reviewText,reviewId);
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if(request.getParameter("userId") != null) {
			map = "AdminUserPage";
			int userId = Integer.parseInt(request.getParameter("userId"));
			request.setAttribute("userId",userId);
		}else {
			map = "ContentPage";
			int contentId = Integer.parseInt(request.getParameter("contentId"));
			request.setAttribute("contentId",contentId);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);

	}
}