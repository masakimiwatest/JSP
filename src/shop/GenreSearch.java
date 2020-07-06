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

public class GenreSearch extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		List<Card> cardList = new ArrayList<>();
		CardDAO dao = new CardDAO();
		RequestDispatcher dispatcher = null;


		int GenreId = (Integer)( request.getAttribute("GenreId") );
		try {
			cardList = (List<Card>) dao.genreSearch(GenreId);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.setAttribute("cardList", cardList);
		cardList = (List<Card>) request.getAttribute("cardList");

		request.setAttribute("narabekaeGenre",GenreId);
		dispatcher = request.getRequestDispatcher("testCard-List.jsp");
		dispatcher.forward(request, response);
	}


}
