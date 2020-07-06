package shop;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Review;
import bean.User;
import dao.ReviewDAO;

public class ReviewInsert extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = null;
		Review review = new Review();
		ReviewDAO reviewDao = new ReviewDAO();
		HttpSession session = request.getSession();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String strDate = sdf.format(cal.getTime());



		int contentId = Integer.parseInt(request.getParameter("contentId"));
		int reviewPoint = Integer.parseInt(request.getParameter("reviewPoint"));
		String reviewText = request.getParameter("reviewText");
		if(session.getAttribute("UserObj") != null) {
			user = (User)session.getAttribute("UserObj");
			int userId = user.getUserId();
			review.setUserId(userId);
			review.setContentId(contentId);
			review.setReviewText(reviewText);
			review.setReviewData(strDate);
			review.setReviewPoint(reviewPoint);
			try {
				reviewDao.insert(review);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		request.setAttribute("contentId",contentId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ContentPage");
		dispatcher.forward(request, response);

	}
}