package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Card;
import dao.CardDAO;


public class ManagerCardInsert extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {

		CardDAO dao = new CardDAO();
		RequestDispatcher dispatcher = null;
		Card CardObj = null;

		CardObj = (Card) request.getAttribute("CardObj");
		try {
			dao.insert(CardObj);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		dispatcher = request.getRequestDispatcher("ManagerCardList");
		dispatcher.forward(request, response);
	}
}