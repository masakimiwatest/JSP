/*ShopControllerの作り方
 *
 * Indexからもらうリクエストのvalueの値によって呼ぶメソッドを変える。メソッドはnameを統一。文をswitch文で作る。
 *
 *valueの値は
 *・genre
 *・search
 *・cart
 *
 *ほしい変数は何か
 *
 *
 *(topからログインjspにいくときもコントローラー通る)←あとで
 * */

package shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShopController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// メニューから飛んでくるアクションを文字列として受け取り格納
		String action = request.getParameter("action");

		// デフォルトで実行結果画面へ推移するよう初期値を設定
		String map = "Index.jsp";

		// 後に使うエラー内容を格納
		String error = "";

		// 検索されたワードを変数に保存
		String CardName = null;

		int CardId = 0;

		// リクエストでもらったものを飛ばす宛先
		RequestDispatcher dispatcher;

		switch (action) {
		case "genre":
			try {
				int GenreId = Integer.parseInt(request.getParameter("GenreId"));

				// debug
				System.out.println(GenreId);
				System.out.println("genre");

				request.setAttribute("GenreId", GenreId);
				map = "GenreSearch";
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			break;

		case "search":

			CardName = request.getParameter("cardName");

			if (!(CardName.equals(""))) {
				request.setAttribute("CardName", CardName);
				map = "KeywordSearch";
			} else {
				map = "Index.jsp";
				error = "error";
				request.setAttribute("error", error);
			}

			break;

		case "card":

			// DAOのサーチメソッド
			try {
				CardId = Integer.parseInt(request.getParameter("CardId"));

				request.setAttribute("CardId", CardId);
				map = "GenreSearchIndex";
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			break;

		case "login":
			map = "Customer-Login";

			break;

		case "cart-in":

			CardId = Integer.parseInt(request.getParameter("CardId"));
			int CardStock = Integer.parseInt(request.getParameter("CardStock"));

			request.setAttribute("CardStock", CardStock);
			request.setAttribute("CardId", CardId);

			map = "Cart";

			break;

		case "cart":
			map = "Cart.jsp";

			break;
		case "CartDel":
			CardId = Integer.parseInt(request.getParameter("CardId"));
			request.setAttribute("cardId", CardId);
			map = "CartDelete";

			break;

		case "narabekae":
			map = "Narabekae";

			break;
		default:
			String error1 = "a";
			request.setAttribute("error", error1);
			dispatcher = request.getRequestDispatcher("../shop/Index.jsp");
			dispatcher.forward(request, response);
			break;
		}
		dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);
	}
}