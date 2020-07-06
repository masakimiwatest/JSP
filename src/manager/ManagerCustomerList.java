package manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Customer;
import dao.CustomerDAO;

public class ManagerCustomerList extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		System.out.println("ManagerCustomerList.javaなう");
		String map;
		List<Customer> list=null;
		//CustoemrDAOのallSerechメソッドを使って、全ユーザーのリストを得る
		CustomerDAO CustomerDAO=new CustomerDAO();

		try {
			list=CustomerDAO.allSearch();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("customerList",list);
		//飛ばす
		map="Manager-Customer-List.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);
	}
}