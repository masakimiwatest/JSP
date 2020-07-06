package manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManagerDAO;

public class ManagerDelete extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		System.out.println("ManagerDelete.javaなう");

		String map = "";

		RequestDispatcher dispatcher = request.getRequestDispatcher(map);

		//必要な変数を取得する
		int managerId=(Integer)request.getAttribute("managerId");

		ManagerDAO ManagerDAO=new ManagerDAO();
		int line=0;

		try {
			line=ManagerDAO.delete(managerId);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if(line==1){
			map="ManagerList";//ManagerList.javaに飛ばす ManagerList.java:DB内の管理者表示サーブレット
			dispatcher = request.getRequestDispatcher(map);
			dispatcher.forward(request, response);

		}else{
		//飛ばす
		map="ManagerList";
		dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);
		}
	}
}