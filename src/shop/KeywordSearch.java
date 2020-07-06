package shop;

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

public class KeywordSearch extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Card> cardList = new ArrayList<>();
		CardDAO dao = new CardDAO();
		RequestDispatcher dispatcher = null;


		String CardName = request.getAttribute("CardName").toString();

		System.out.println(CardName);

		try {

			cardList = dao.listSearch(CardName);


			if (cardList.size() == 0) {
				String error = "商品は見つかりませんでした。<br>他のキーワードをお試しください。";
				request.setAttribute("error", error);
				}

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("cardList", cardList);
		request.setAttribute("narabekaeName",CardName);
		dispatcher = request.getRequestDispatcher("testCard-List.jsp");
		System.out.println("jspへ移動");
		dispatcher.forward(request, response);

	}
}
