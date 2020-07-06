package manager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Sale;
import dao.SaleDAO;

public class SaleList extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Sale> list = new ArrayList<>();
		List<Sale> list1 = new ArrayList<>();
		List<Sale> list2 = new ArrayList<>();
		SaleDAO dao = new SaleDAO();
		RequestDispatcher dispatcher = null;

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String strDate = sdf.format(cal.getTime());
		strDate = strDate.substring(0,6);

		try {
			list = dao.allSearch();
			list1 = dao.allSearchShippingNo();
			list2 = dao.allSearchCsv(strDate);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}




		HttpSession session = request.getSession();
		request.setAttribute("list",list);
		request.setAttribute("list1",list1);
		session.setAttribute("list2",list2);
		dispatcher = request.getRequestDispatcher("Sale-List.jsp");
		dispatcher.forward(request, response);

	}
}
