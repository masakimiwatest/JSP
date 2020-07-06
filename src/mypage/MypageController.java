package mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Customer;

public class MypageController extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// メニューから飛んでくるアクションを文字列として受け取り格納
		String action = request.getParameter("action");// 名前はactionで統一。（中身はそれぞれ違う。）

		System.out.println(action);

		// デフォルトでマイページ画面へ推移するよう初期値を設定
		String map = "Mypage.jsp";

		// カスタマーオブジェクトをセッションから受け取る。
		Customer CustomerObj;

		// ディスパーチャーの変数を置く。
		RequestDispatcher dispatcher = request.getRequestDispatcher(map);


		// ①ログインしていない状態で、ログインしようとしている人。
		if (session.getAttribute("CustomerObj") == null && action.equals("login")) {
			String customerUserId = request.getParameter("customerUserId");
			String customerPassword = request.getParameter("customerPassword");

			request.setAttribute("customerUserId", customerUserId);
			request.setAttribute("customerPassword", customerPassword);

			map = "CustomerLogin";

			// mapに代入されているURL先に飛ばす。
			dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);

			// ②ログインしていない状態で、新規ログインしようとしている人。
		} else if (session.getAttribute("CustomerObj") == null && action.equals("customerCreate")) {
			// データを受け取る。
			int customerCode = 0;
			try {
				customerCode = Integer.parseInt(request.getParameter("customerCode"));
			} catch (NumberFormatException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			String customerTel = request.getParameter("customerTel");
			String customerUserId = request.getParameter("customerUserId");
			String customerName = request.getParameter("customerName");
			String customerAddress = request.getParameter("customerAddress");
			String customerMail = request.getParameter("customerMail");
			String customerPassword = request.getParameter("customerPassword");

			// データをオブジェクトにまとめる。
			CustomerObj = new Customer();
			CustomerObj.setCustomerCode(customerCode);
			CustomerObj.setCustomerTel(customerTel);

			CustomerObj.setCustomerUserId(customerUserId);
			CustomerObj.setCustomerName(customerName);
			CustomerObj.setCustomerAddress(customerAddress);
			CustomerObj.setCustomerMail(customerMail);
			CustomerObj.setCustomerPassword(customerPassword);

			// オブジェクトをリクエストにセットする。
			request.setAttribute("CustomerObj", CustomerObj);

			// mapを新規登録サーブレットに変更する。
			map = "CustomerCreate";
			// mapに代入されているURL先に飛ばす。
			dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);

			// ③ログインしていて、何かをしたい人。
		} else if (session.getAttribute("CustomerObj") != null) {
			switch (action) {

			case "login":// ログインしているのに、ログインしようとしている人

				dispatcher = request.getRequestDispatcher(map);// マイページへ飛ぶ
				dispatcher.forward(request, response);
				break;

			case "reviewJava":// reviewText(OKor""),cardIdが送られてくる。customerIdがsessionに入っている。

				// 受け取ったパラメーターを変数におく。
				String reviewText = request.getParameter("reviewText");
				int cardId = 0;
				try {
					cardId = Integer.parseInt(request.getParameter("cardId"));
				} catch (NumberFormatException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

				if (reviewText.equals("")) {
					// mapをレビュー入力画面のURLに変換する。
					map = "Review.jsp";
					// フォワードで送信。

				} else {
					// reviewTextが空ではなかった場合
					// reviewTextとcardIdをリクエストに保持する。

					request.setAttribute("reviewText", reviewText);
					request.setAttribute("cardId", cardId);

					// mapをレビューサーブレットに変更する。
					map = "Review.java";

				}

				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);

				break;

			case "customerchangeJava":// customerId以外の変数（CustomerBean内の変数）をパラメーターゲットする。
				int customerCode = 0;

				try {
					customerCode = Integer.parseInt(request.getParameter("customerCode"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				String customerTel = request.getParameter("customerTel");
				String customerUserId = request.getParameter("customerUserId");
				String customerName = request.getParameter("customerName");
				String customerAddress = request.getParameter("customerAddress");
				String customerMail = request.getParameter("customerMail");
				String customerPassword = request.getParameter("customerPassword");

				// カスタマー情報をオブジェクトにまとめる。
				CustomerObj = new Customer();
				CustomerObj.setCustomerCode(customerCode);
				CustomerObj.setCustomerTel(customerTel);

				CustomerObj.setCustomerUserId(customerUserId);
				CustomerObj.setCustomerName(customerName);
				CustomerObj.setCustomerAddress(customerAddress);
				CustomerObj.setCustomerMail(customerMail);
				CustomerObj.setCustomerPassword(customerPassword);

				// リクエストにオブジェクトをセットする。
				request.setAttribute("CustomerObj", CustomerObj);

				// mapをサーブレットURLに変更する。
				map = "CustomerChange";

				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
				break;

			case "customerbuy":

				map = "CustomerBuy";
				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
				break;

			// Review.jspへ
			case "reviewJsp":
				String cardName = (String) request.getParameter("cardName");
				request.setAttribute("cardName", cardName);// OK

				cardId = Integer.parseInt(request.getParameter("cardId"));
				request.setAttribute("cardId", cardId);// OK

				map = "Review.jsp";
				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
				break;

			// Review.javaへ
			case "review":
				System.out.println("Review.java前コントローラ1");

				reviewText = request.getParameter("reviewText");
				request.setAttribute("reviewText", reviewText);// OK
				System.out.println("Review.java前コントローラ2");

				cardId = Integer.parseInt(request.getParameter("cardId"));
				request.setAttribute("cardId", cardId);// OK
				System.out.println("Review.java前コントローラ3");

				map = "ReviewText";
				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
				break;

			case "customerchangeJsp":

				map = "Customer-Change.jsp";
				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
				break;

			case "customerhistoryJsp":

				map = "Customer-History.jsp";
				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
				break;

			case "mypageJsp":

				map = "Mypage.jsp";
				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
				break;

			case "logoutJsp":

				map = "CustomerLogout";
				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
				break;

			// ショップページ内で使用
			case "cardindexJsp":// ?????????いる？？？

				map = "Card-Index.jsp";
				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
				break;

			case "CartJsp":
				System.out.println("CartJsp");

				map = "Buy.jsp";
				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
				break;

			case "BuyJsp":

				map = "Buy";
				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
				break;

			case "see":

				map = "See.jsp";
				dispatcher = request.getRequestDispatcher(map);
				dispatcher.forward(request, response);
				break;

			default:
				String error = "a";
				request.setAttribute("error", error);
				dispatcher = request.getRequestDispatcher("../shop/Index.jsp");
				dispatcher.forward(request, response);
				break;

			}

			// ④ログインしてない上に、ログインでも新規登録でもない人。マイページ画面に戻る。
		} else

		{
			String error = "a";
			request.setAttribute("error", error);
			dispatcher = request.getRequestDispatcher("../shop/Index.jsp");
			dispatcher.forward(request, response);
		}

	}
}