package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Card;
import dao.CardDAO;

public class ManagerCardIndex extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CardDAO dao = new CardDAO();
		RequestDispatcher dispatcher = null;
		Card card = null;

		int CardId = (Integer) (request.getAttribute("CardId"));

		try {
			card = dao.idSearch(CardId);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("card", card);
		dispatcher = request.getRequestDispatcher("Manager-Card-Index.jsp");
		dispatcher.forward(request, response);

	}
}
