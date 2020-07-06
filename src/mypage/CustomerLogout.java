package mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerLogout extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {

		//sessionからオブジェクトを取り出し、データを消す。
		HttpSession session = request.getSession();

		//sessionからデータを消す。
		session.removeAttribute("CustomerObj");
		session.removeAttribute("rireki");


		RequestDispatcher dispatcher = request.getRequestDispatcher("../shop/Index.jsp");
		dispatcher.forward(request, response);//ログアウト完了して、トップページ画面に戻る。

	}
}