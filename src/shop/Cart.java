package shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Card;
import dao.CardDAO;

public class Cart extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		Card card = null;
		CardDAO dao = new CardDAO();

		// もらったカードCardIdをidSearchでオブジェクト化して、カード型のリスト「カート」に格納
		int CardId = (Integer) (request.getAttribute("CardId"));
		int CardCount = (Integer) (request.getAttribute("CardStock"));
		List<Card> cart = new ArrayList<>();
		if (session.getAttribute("cart") != null) {
			cart = (List<Card>) session.getAttribute("cart");
		}
		int a = 0;
		try {
			card = dao.idSearch(CardId);
			for (Card ccc : cart) {
				if (ccc.getCardId() == card.getCardId()) {
					CardCount = ccc.getCardCount() + CardCount;
					if (CardCount > card.getCardStock()) {
						CardCount = card.getCardStock();
					}
					ccc.setCardCount(CardCount);
					a++;
				}
			}

			if (a == 0) {
				card.setCardCount(CardCount);
				cart.add(card);
			}

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		// sessionで保持
		session.setAttribute("cart", cart);
		request.setAttribute("card", card);
		dispatcher = request.getRequestDispatcher("Cart.jsp");
		dispatcher.forward(request, response);

	}
}