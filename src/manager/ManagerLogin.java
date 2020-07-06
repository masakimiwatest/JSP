package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Manager;
import dao.ManagerDAO;


public class ManagerLogin extends HttpServlet {



	public void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {

		//リクエストから、CustomerUserIdとCustomerPasswordをゲットする。

		String ManagerUserId= (String)request.getAttribute("ManagerUserId");
		String ManagerPassword=(String) request.getAttribute("ManagerPassword");

		String map = "";

		//オブジェクトを得る。
		ManagerDAO managerDAO =new ManagerDAO();
		Manager ManagerObj=null;

		try {
			ManagerObj = managerDAO.login(ManagerUserId,ManagerPassword);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		if(ManagerObj==null){//ログイン失敗
			String error1 ="a";
			request.setAttribute("error", error1);
			map="Manager-Login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);//ログイン画面に戻る。

		}else{//ログイン成功
			HttpSession session = request.getSession();
			session.setAttribute("ManagerObj",ManagerObj);

			map="Manager-Index.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);//トップページ画面に戻る。

		}
	}
}