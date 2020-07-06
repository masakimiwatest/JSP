package mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Customer;
import dao.CustomerDAO;

public class CustomerLogin extends HttpServlet {
	public void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {

		//リクエストから、CustomerUserIdとCustomerPasswordをゲットする。

		String customerUserId= (String)request.getAttribute("customerUserId");
		String customerPassword=(String) request.getAttribute("customerPassword");

		String map = "";

		RequestDispatcher dispatcher = request.getRequestDispatcher(map);

		//オブジェクトを得る。
		CustomerDAO CustomerDAO =new CustomerDAO();
		Customer CustomerObj=null;

		try {
			CustomerObj = CustomerDAO.login(customerUserId,customerPassword);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		if(CustomerObj==null){//ログイン失敗

			String error = "login";
			request.setAttribute("error",error);
			map="Customer-Login.jsp";
			dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);//ログイン画面に戻る。

		}else{//ログイン成功
			HttpSession session = request.getSession();
			session.setAttribute("CustomerObj",CustomerObj);

			map="../shop/Index.jsp";
			dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);//トップページ画面に戻る。

		}
	}
}