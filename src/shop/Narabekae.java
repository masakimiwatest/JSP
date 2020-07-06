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

public class Narabekae extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Card> cardList = new ArrayList<>();
		CardDAO dao = new CardDAO();
		RequestDispatcher dispatcher = null;


		String cardSort = request.getParameter("shurui").toString();
		String cardSortWhat ="";
		int cardWhat = 0;
		//genre
		if(request.getParameter("narabekae1") != null){
			cardWhat = Integer.parseInt(request.getParameter("narabekae1"));
			try {
				switch(cardSort){
				case "priceA":
					cardList = dao.genreSearchPriceAsc(cardWhat);
					break;
				case "priceD":
					cardList = dao.genreSearchPriceDesc(cardWhat);
					break;
				case "nameA":
					cardList = dao.genreSearchNameAsc(cardWhat);
					break;
				case "nameD":
					cardList = dao.genreSearchNameDesc(cardWhat);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			request.setAttribute("narabekaeGenre", cardWhat);
		}else{
			cardSortWhat = request.getParameter("narabekae2");
			try {
				switch(cardSort){
				case "priceA":
					cardList = dao.listSearchPriceAsc(cardSortWhat);
					break;
				case "priceD":
					cardList = dao.listSearchPriceDesc(cardSortWhat);
					break;
				case "nameA":
					cardList = dao.listSearchNameAsc(cardSortWhat);
					break;
				case "nameD":
					cardList = dao.listSearchNameDesc(cardSortWhat);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			request.setAttribute("narabekaeName", cardSortWhat);
		}






		request.setAttribute("cardList", cardList);

		dispatcher = request.getRequestDispatcher("testCard-List.jsp");
		System.out.println("jspへ移動");
		dispatcher.forward(request, response);

	}
}
