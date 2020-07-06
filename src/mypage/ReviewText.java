package mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Card;
import bean.Customer;
import bean.Review;
import dao.CardDAO;
import dao.ReviewDAO;



public class ReviewText extends HttpServlet {

	public void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String map;

		int line=0;

		//リクエストから①cardIdを取得する。
		int cardId=(Integer)request.getAttribute("cardId");

		//セッションから、カスタマーオブジェクトをゲットし、②customerIdをゲットする。
		Customer CustomerObj=(Customer)session.getAttribute("CustomerObj");
		int customerId=CustomerObj.getCustomerId();//OK


		//リクエストから、③reviewTextをゲットする。
		String reviewText=(String)request.getAttribute("reviewText");//OK

		//レビューオブジェクトに格納
		Review ReviewObj=new Review();
		ReviewObj.setCardId(cardId);//OK
		ReviewObj.setCustomerId(customerId);//OK
		ReviewObj.setReviewText(reviewText);//OK


		ReviewDAO ReviewDAO=new ReviewDAO();
		try {
			line=ReviewDAO.insert(ReviewObj);//ReviewDAOのinsertメソッドを使用
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		ReviewDAO reviewDAO = new ReviewDAO();
		CardDAO dao = new CardDAO();

		Card card = null;
		List<Review> list1 = null;

		try {
			card = dao.idSearch(cardId);
			list1 = (List<Review>)reviewDAO.search(cardId);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}



		if(line==1){
			//飛ばす
			request.setAttribute("card", card);
			request.setAttribute("review", list1);
			map = "/shop/testCard-Index.jsp";

		}else{
			String error = "error";
			request.setAttribute("error", error);
			map="Review.jsp";//レビュー記入画面へ戻る
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);

	}
}