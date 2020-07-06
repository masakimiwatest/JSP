package shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Card;

public class CartDelete extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		System.out.println("test");
		HttpSession session = request.getSession();
		List<Card> list=(List<Card>)session.getAttribute("cart");

		int cardIdDelete=(Integer)request.getAttribute("cardId");
		for(Card card:list){
			int cardId=card.getCardId();
			if(cardId==cardIdDelete){
				list.remove(card);
				break;
			}
		}

		session.setAttribute("list", list);

		String map="../shop/Cart.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);
	}
}