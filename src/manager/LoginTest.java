package manager;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDAO;
public class LoginTest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		TestDAO dao = new TestDAO();
		String testName = request.getParameter("name");
		String testPass = request.getParameter("pass");
		Test testObj = null;

		try {
			testObj = dao.login(testName,testPass);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("testObj",testObj);
		RequestDispatcher dispatcher  = request.getRequestDispatcher("test1.jsp");
		dispatcher.forward(request, response);
	}

}
