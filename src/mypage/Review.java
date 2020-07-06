package mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Customer;

public class Review extends HttpServlet {

	public void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		//リクエストから、reviewTextとcardIdをゲットする。
		String reviewText=request.getAttribute("reviewText").toString();
		int cardId=(int)request.getAttribute("cardId");

		//セッションから、カスタマーオブジェクトをゲットする。
		HttpSession session = request.getSession();
		Customer CustomerObj=(Customer)session.getAttribute("CustomerObj");
	}






















}
