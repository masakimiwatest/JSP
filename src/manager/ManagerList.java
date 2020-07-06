package manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Manager;
import dao.ManagerDAO;

public class ManagerList extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {

		String map;
		List<Manager> list=null;
		//ManagerDAOのallSerechメソッドを使って、全管理者のリストを得る
		ManagerDAO ManagerDAO=new ManagerDAO();
		try {
			list=ManagerDAO.allSearch();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//listをリクエストにセットする
		request.setAttribute("managerList", list);

		//飛ばす
		map="Manager-List.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);
	}
}