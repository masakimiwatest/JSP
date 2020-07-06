package mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Customer;
import bean.Sale;
import dao.SaleDAO;

public class CustomerBuy extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		System.out.println("CustomerBuyサーブレット");
		HttpSession session = request.getSession();
		List<Sale> listCard=null;

		//セッションから、カスタマーオブジェクトを取り出し、カスタマーIDをゲットする
		Customer customerObj=(Customer)session.getAttribute("CustomerObj");
		int customerId=customerObj.getCustomerId();

		//SaleDAOのcustomerSearchメソッドを使用し、listを得る
		SaleDAO SaleDAO=new SaleDAO();
		try {
			listCard=SaleDAO.customerSearch(customerId);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("listCard",listCard);

		String map="Customer-Buy.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);
	}
}