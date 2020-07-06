package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Customer;
import dao.CustomerDAO;

public class ManagerCustomerChange extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		System.out.println("ManagerCustomerChange.javaなう");

		String map = "";

		RequestDispatcher dispatcher = request.getRequestDispatcher(map);

		//必要な変数を取得する
		int customerId=(Integer)request.getAttribute("customerId");
		int customerCode=(Integer)request.getAttribute("customerCode");
		String customerTel=(String)request.getAttribute("customerTel");
		String customerPassword=(String)request.getAttribute("customerPassword");
		String customerUserId=(String)request.getAttribute("customerUserId");
		String customerName=(String)request.getAttribute("customerName");
		String customerAddress=(String)request.getAttribute("customerAddress");
		String customerMail=(String)request.getAttribute("customerMail");

		//beanに入れる//////////////////////////7つ(customerId以外)
		Customer customerObj =new Customer();
		customerObj.setCustomerId(customerCode);
		customerObj.setCustomerTel(customerTel);
		customerObj.setCustomerPassword(customerPassword);
		customerObj.setCustomerUserId(customerUserId);
		customerObj.setCustomerName(customerName);
		customerObj.setCustomerAddress(customerAddress);
		customerObj.setCustomerMail(customerMail);

		CustomerDAO CustomerDAO=new CustomerDAO();
		int line=0;
		try {
			line=CustomerDAO.update(customerObj,customerId);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if(line==1){//変更成功
			map="ManagerCustomerList";//ManagerCustomerList.javaに飛ばす ManagerCustomerList.java:DB内の管理者表示サーブレット
			dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);

		}else{//変更失敗
			map="Manager-Customer-List.jsp";
			dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);
		}
	}
}