package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerLogout extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("logoutJsp");
		// sessionからオブジェクトを取り出し、データを消す。
		HttpSession session = request.getSession();

		// sessionからデータを消す。
		if(session.getAttribute("ManagerObj") == null){
		System.out.println("ログアウトエラー");
		}else{


		String map = "";
		session.removeAttribute("ManagerObj");
		System.out.println("ログアウト成功");
		map = "Manager-Login.jsp";
		System.out.println("ログインページへ戻る");
		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);// ログアウト完了して、トップページ画面に戻る。
		}

	}
}