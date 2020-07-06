package manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Card;
import dao.CardDAO;

public class ManagerCardList extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Card> cardList = new ArrayList<>();
		CardDAO dao = new CardDAO();
		RequestDispatcher dispatcher = null;

		try {
				cardList = dao.allSearch();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
				e.printStackTrace();
		}
		request.setAttribute("cardList",cardList);
		dispatcher = request.getRequestDispatcher("Manager-Card-List.jsp");
		dispatcher.forward(request, response);
	}
}