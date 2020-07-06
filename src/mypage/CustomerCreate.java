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

public class CustomerCreate extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {

		Customer CustomerObj=(Customer)request.getAttribute("CustomerObj");

		String map = "";

		RequestDispatcher dispatcher = request.getRequestDispatcher(map);

		CustomerDAO CustomerDAO =new CustomerDAO();

		int lineTranSearch=0;

		try {
			lineTranSearch = CustomerDAO.tranSearch(CustomerObj.getCustomerUserId());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if(lineTranSearch==0){//かぶりが無い
			int lineInsert=0;

			try {
				lineInsert = CustomerDAO.insert(CustomerObj);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				//System.out.println("エラー");
				e.printStackTrace();
			}

			if(lineInsert==1){//新規登録成功         //カーソルが動いた数（line）

				HttpSession session = request.getSession();
				try {
					CustomerObj = CustomerDAO.login(CustomerObj.getCustomerUserId(), CustomerObj.getCustomerPassword());
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				session.setAttribute("CustomerObj",CustomerObj);

			map="../shop/Index.jsp";
			dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);//ログイン完了して、トップページ画面に戻る。

			}

		}else{//新規登録失敗
			String error = "new";
			request.setAttribute("error",error);
			map="Customer-Login.jsp";
			dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);//ログイン画面に戻る。

		}
	}
}