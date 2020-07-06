package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Card;

public class ManagerController extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// メニューから飛んでくるアクションを文字列として受け取り格納
		String action = request.getParameter("action");

		// デフォルトで実行結果画面へ推移するよう初期値を設定
		String map = "Manager-Login.jsp";

		HttpSession session = request.getSession();

		// フィールド
		int CardId = 0;
		int managerId;
		int managerLevel;
		int customerId;
		String managerUserId;
		String managerPassword;

		Card CardObj = null;

		// カスタマーオブジェクトをセッションから受け取る。
		// Manager ManagerObj = null;

		// リクエストでもらったものを飛ばす宛先
		RequestDispatcher dispatcher;

		// ①ログインしていない状態で、ログインしようとしている人。
		if (action.equals("login")) {
			System.out.println("test");
			String ManagerUserId = request.getParameter("managerUserId");
			String ManagerPassword = request.getParameter("managerPassword");

			request.setAttribute("ManagerUserId", ManagerUserId);
			request.setAttribute("ManagerPassword", ManagerPassword);

			map = "ManagerLogin";

			// mapに代入されているURL先に飛ばす。

		} else if (session.getAttribute("ManagerObj") != null) {

			switch (action) {

			case "login":

				break;

			case "logout":

				map = "ManagerLogout";

				break;

			// 管理者から商品リスト一覧表示javaへ
			case "card-list":

				map = "ManagerCardList";
				break;

			// 商品リストからカード詳細java
			case "card-index":

				CardId = Integer.parseInt(request.getParameter("CardId"));
				request.setAttribute("CardId", CardId);

				map = "ManagerCardIndex";
				break;

			// 商品リストからカード変更java
			case "card-change":

				int CardPrice = 0;
				String CardName = "";
				int CardStock = 0;
				String CardText = "";
				String CardPicture = "";
				int GenreId = 0;

				try {
					CardPrice = Integer.parseInt(request.getParameter("CardPrice"));
					CardName = request.getParameter("CardName");
					CardId = Integer.parseInt(request.getParameter("CardId"));
					GenreId = Integer.parseInt(request.getParameter("GenreId"));
					CardStock = Integer.parseInt(request.getParameter("CardStock"));

					CardText = request.getParameter("CardText");
					CardPicture = request.getParameter("CardPicture");

				} catch (NumberFormatException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

				// データをオブジェクトにまとめる。
				CardObj = new Card();
				CardObj.setCardPrice(CardPrice);
				CardObj.setCardName(CardName);
				CardObj.setGenreId(GenreId);
				CardObj.setCardStock(CardStock);
				CardObj.setCardText(CardText);
				CardObj.setCardPicture(CardPicture);
				CardObj.setCardId(CardId);

				// オブジェクトをリクエストにセットする。
				request.setAttribute("CardObj", CardObj);

				map = "ManagerCardChange";
				break;

			// 商品リストからカード削除java
			case "card-delete":

				CardId = Integer.parseInt(request.getParameter("CardId"));

				request.setAttribute("CardId", CardId);
				map = "ManagerCardDelete";
				break;

			// 商品リストからカード登録jsp
			case "card-create":

				map = "Manager-Card-Create.jsp";
				break;

			// 商品リストからカード登録java
			case "card-insert":

				int cardPrice = 0;
				String cardName = "";
				int genreId = 0;
				int cardStock = 0;
				String cardText = "";
				String cardPicture = "";
				try {
					cardPrice = Integer.parseInt(request.getParameter("cardPrice"));
					cardName = request.getParameter("cardName");
					genreId = Integer.parseInt(request.getParameter("genreId"));
					cardStock = Integer.parseInt(request.getParameter("cardStock"));
					cardText = request.getParameter("cardText");
					cardPicture = request.getParameter("cardPicture");
				} catch (NumberFormatException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

				if (cardPrice == 0 || cardName.equals("") || genreId == 0 || cardStock == 0 || cardText.equals("")
						|| cardPicture.equals("")) {

					map = "Manager-Card-Create.jsp";

				} else {

					// データをオブジェクトにまとめる。
					CardObj = new Card();
					CardObj.setCardPrice(cardPrice);
					CardObj.setCardName(cardName);

					CardObj.setGenreId(genreId);
					CardObj.setCardStock(cardStock);
					CardObj.setCardText(cardText);
					CardObj.setCardPicture(cardPicture);

					// オブジェクトをリクエストにセットする。
					request.setAttribute("CardObj", CardObj);

					// mapを情報変更ページURLに変更する。
					// map = "CardInsert";
					map = "ManagerCardInsert";
				}

				break;

			// 管理者TOPページから管理者リストjavaへ//OK///////////////////////////
			case "manager-list":

				map = "ManagerList";
				break;

			// 管理者リストから変更・削除jspへ//OK////////////////////////////////
			case "manager-change-delete-Jsp":
				managerId = Integer.parseInt(request.getParameter("managerId"));
				managerUserId = request.getParameter("managerUserId");
				managerLevel = Integer.parseInt(request.getParameter("managerLevel"));
				managerPassword = request.getParameter("managerPassword");

				request.setAttribute("managerId", managerId);
				request.setAttribute("managerUserId", managerUserId);
				request.setAttribute("managerLevel", managerLevel);
				request.setAttribute("managerPassword", managerPassword);

				map = "Manager-Change.jsp";
				break;

			// 変更・削除jspから管理者変更javaへ//OK//////////////////////////////
			case "manager-change-Java":
				managerId = Integer.parseInt(request.getParameter("managerId"));
				managerUserId = request.getParameter("managerUserId");
				managerLevel = Integer.parseInt(request.getParameter("managerLevel"));
				managerPassword = request.getParameter("managerPassword");

				request.setAttribute("managerId", managerId);
				request.setAttribute("managerUserId", managerUserId);
				request.setAttribute("managerLevel", managerLevel);
				request.setAttribute("managerPassword", managerPassword);

				map = "ManagerChange";
				break;

			// 変更・削除jspから管理者削除javaへ//OK//////////////////////////////
			case "manager-delete-Java":
				managerId = Integer.parseInt(request.getParameter("managerId"));
				request.setAttribute("managerId", managerId);

				map = "ManagerDelete";
				break;

			// 管理者リストから管理者登録jspへ//OK//////////////////////////////
			case "manager-create-Jsp":

				map = "Manager-Create.jsp";
				break;

			// 管理者登録jspから管理者登録javaへ//OK///////////////////////////////
			case "manager-create-java":
				managerUserId = request.getParameter("managerUserId");
				managerLevel = Integer.parseInt(request.getParameter("managerLevel"));
				managerPassword = request.getParameter("managerPassword");

				request.setAttribute("managerUserId", managerUserId);
				request.setAttribute("managerLevel", managerLevel);
				request.setAttribute("managerPassword", managerPassword);

				map = "ManagerCreate";
				break;

			// 管理者ページからカスタマーリストjavaへ//OK///////////////////////////////
			case "manager-customer-list-Java":

				map = "ManagerCustomerList";
				break;

			// 管理者ページから売り上げリストjavaへ
			case "sale-list":

				map = "SaleList";
				break;

			// 売り上げリストjspからSaleShipping.javaへ
			case "sale-shipping":

				int SaleId = Integer.parseInt(request.getParameter("SaleId"));

				if(request.getParameter("where") != null){
					String strWhere = request.getParameter("where");
					request.setAttribute("where", strWhere);
				}

				request.setAttribute("SaleId", SaleId);

				map = "SaleShipping";
				break;

			// カスタマーリストページからカスタマー変更削除javaへ//OK///////////////////////////////
			case "manager-customer-change-delete":
				customerId = Integer.parseInt(request.getParameter("customerId"));
				request.setAttribute("customerId", customerId);

				map = "ManagerCustomerChangeDelete";
				break;

			// カスタマー変更削除jspからカスタマー変更javaへ//OK///////////////////////////////
			case "manager-customer-change":

				customerId = Integer.parseInt(request.getParameter("customerId"));
				int customerCode = Integer.parseInt(request.getParameter("customerCode"));
				String customerTel = request.getParameter("customerTel");

				String customerName = request.getParameter("customerName");
				String customerAddress = request.getParameter("customerAddress");
				String customerMail = request.getParameter("customerMail");
				String customerUserId = request.getParameter("customerUserId");
				String customerPassword = request.getParameter("customerPassword");

				request.setAttribute("customerId", customerId);
				request.setAttribute("customerCode", customerCode);
				request.setAttribute("customerTel", customerTel);
				request.setAttribute("customerName", customerName);
				request.setAttribute("customerAddress", customerAddress);
				request.setAttribute("customerMail", customerMail);
				request.setAttribute("customerUserId", customerUserId);
				request.setAttribute("customerPassword", customerPassword);

				map = "ManagerCustomerChange";
				break;

			// カスタマー変更削除jspからカスタマー削除javaへ//OK///////////////////////////////
			case "manager-customer-delete":
				customerId = Integer.parseInt(request.getParameter("customerId"));
				request.setAttribute("customerId", customerId);

				map = "ManagerCustomerDelete";
				break;

			// カスタマーリストページからカスタマー登録java
			case "customer-create":

				map = "CustomerCardCreate";
				break;

			// カスタマーリストjspからManagerCSV.javaへ
			case "manager-csv":

				map = "ManagerCSV";
				break;

			default:
				break;
			}

		}
		dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);
	}
}