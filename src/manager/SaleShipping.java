package manager;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SaleDAO;

public class SaleShipping extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("SaleShipping.javaなう");

		SaleDAO dao = new SaleDAO();
		RequestDispatcher dispatcher = null;
		String strCustomerId = "";
		int SaleId = (Integer) (request.getAttribute("SaleId") );
		System.out.println(SaleId);
		if(request.getAttribute("where") != null){
			strCustomerId = request.getAttribute("where").toString();
			
		}
		try {
			int shipping = dao.shipping(SaleId);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		dispatcher = request.getRequestDispatcher("SaleList");//SaleList.javaへ
		dispatcher.forward(request, response);

	}
}