package shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Card;
import bean.Review;
import dao.CardDAO;
import dao.ReviewDAO;


public class GenreSearchIndex extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ReviewDAO reviewDAO = new ReviewDAO();
		CardDAO dao = new CardDAO();
		RequestDispatcher dispatcher = null;
		Card card = null;
		List<Review> list1 = null;
		int CardId = (Integer)(request.getAttribute("CardId") );
		try {
			card = dao.idSearch(CardId);
			list1 = (List<Review>)reviewDAO.search(CardId);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("card", card);
		request.setAttribute("review", list1);
		dispatcher = request.getRequestDispatcher("testCard-Index.jsp");
		dispatcher.forward(request, response);

	}
}
