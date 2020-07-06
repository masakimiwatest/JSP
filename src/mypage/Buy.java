package mypage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Card;
import bean.Customer;
import bean.Sale;
import dao.CardDAO;
import dao.SaleDAO;

public class Buy extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Buy.java");
		String map;

		HttpSession session = request.getSession();

		// CustomerObjをセッションから取り出し、①customerIdを取り出す
		Customer CustomerObj = (Customer) session.getAttribute("CustomerObj");
		int customerId = (Integer) CustomerObj.getCustomerId();
		System.out.println(customerId);

		List<Card> list = (List<Card>) session.getAttribute("cart");

		// ③int saleDateを得る
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String strDate = sdf.format(cal.getTime());
		int saleDate = Integer.parseInt(strDate);
		System.out.println(saleDate);

		for (Card card : list) {
			// ②cardIdをセッションから取り出す
			int cardId = card.getCardId();
			System.out.println(cardId);
			// ④int saleCountを得る
			int saleCount = card.getCardCount();
			System.out.println(saleCount);

			// 上記4つの変数をsaleオブジェクトにまとめる
			Sale sale = new Sale();
			sale.setCustomerId(customerId);// OK
			sale.setCardId(cardId);// OK
			sale.setSaleDate(saleDate);// OK
			sale.setSaleCount(saleCount);// OK

			if (card.getCardCount() > card.getCardStock()) {

			} else {

				SaleDAO SaleDAO = new SaleDAO();
				int lineBuy = 0;
				try {
					lineBuy = SaleDAO.insert(sale);// insertメソッドを使う
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					System.out.println(e);
				}

				CardDAO CardDAO = new CardDAO();
				int lineStock = 0;
				try {
					lineStock = CardDAO.stockMinus(sale.getCardId(), sale.getSaleCount());
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					System.out.println(e);
				}
			}

		}

		session.removeAttribute("cart");
		map = "Buy-Ok.jsp";// Buy-Ok.jspへ
		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);
	}
}