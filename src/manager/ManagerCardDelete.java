package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Card;
import dao.CardDAO;

public class ManagerCardDelete extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		//必要な変数を取得する
		int CardId=(Integer)request.getAttribute("CardId");
		CardDAO dao = new CardDAO();

		Card card = new Card();
		try {
		dao.delete(CardId);
		card = dao.idSearch(CardId);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		dispatcher = request.getRequestDispatcher("ManagerCardList");
		dispatcher.forward(request, response);
	}
}