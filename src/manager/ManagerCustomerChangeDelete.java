package manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Customer;
import bean.Sale;
import dao.CustomerDAO;
import dao.SaleDAO;

public class ManagerCustomerChangeDelete extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {

		String map = "";

		RequestDispatcher dispatcher = request.getRequestDispatcher(map);

		//必要な変数を得る
		int customerId=(Integer)request.getAttribute("customerId");


		//必要なオブジェクトを得る
		CustomerDAO CustomerDAO=new CustomerDAO();
		Customer customerObj=null;
		try {
			customerObj=CustomerDAO.search(customerId);//①
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		SaleDAO SaleDAO=new SaleDAO();
		List<Sale> list=null;
		List<Sale> list2=null;
		try {
			list=SaleDAO.customerShippingSearch(customerId);//②
			list2=SaleDAO.customerShippingSearchOk(customerId);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//リクエストにセットする
		request.setAttribute("customerObj",customerObj);
		request.setAttribute("customerShippingList",list);
		request.setAttribute("customerShippingList2",list2);

		map = "Manager-Customer-Change.jsp";
		dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);
	}
}