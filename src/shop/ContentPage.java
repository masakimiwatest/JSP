package shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Content;
import bean.Review;
import dao.ContentDAO;
import dao.ReviewDAO;

public class ContentPage extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Content contentObj = null;
		ReviewDAO reviewDao = new ReviewDAO();
		ContentDAO contentDao = new ContentDAO();

		int contentId = Integer.parseInt(request.getParameter("cotentId"));
		try {
			contentObj = contentDao.searchId(contentId);
			List<Review> reviewList = reviewDao.search(contentId);
			request.setAttribute("contentObj", contentObj);
			request.setAttribute("reviewList", reviewList);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("content.jsp");
		dispatcher.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Content contentObj = null;
		ReviewDAO reviewDao = new ReviewDAO();
		ContentDAO contentDao = new ContentDAO();

		int contentId = (int)request.getAttribute("contentId");
		try {
			contentObj = contentDao.searchId(contentId);
			List<Review> reviewList = reviewDao.search(contentId);
			request.setAttribute("contentObj", contentObj);
			request.setAttribute("reviewList", reviewList);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("content.jsp");
		dispatcher.forward(request, response);

	}
}