package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Card;
import dao.CardDAO;

public class ManagerCardChange extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {

		//必要な変数を取得する
		CardDAO dao = new CardDAO();
		RequestDispatcher dispatcher = null;
		Card CardObj = null;

		CardObj = (Card) request.getAttribute("CardObj");
		System.out.println(CardObj.getCardName());
		System.out.println(CardObj.getCardId());
		try {
			dao.update(CardObj);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		dispatcher = request.getRequestDispatcher("ManagerCardList");
		dispatcher.forward(request, response);
	}
}