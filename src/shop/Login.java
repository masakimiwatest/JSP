package shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.User;
import dao.AdminDAO;
import dao.UserDAO;

public class Login extends HttpServlet {
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginId = (String) request.getParameter("loginId");
		String password = (String) request.getParameter("password");
		String map = "";

		if (request.getParameter("admin") == null) {
			boolean isLoginId = Validation.MailMatch(loginId);
			boolean isPassword = Validation.StringMatch(password);

			if (isLoginId && isPassword) {
				map = "";

				UserDAO userDAO = new UserDAO();
				User UserObj = null;

				try {
					UserObj = userDAO.login(loginId, password);
					userDAO.allSearch();
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					map = "error.jsp";
				}

				if (UserObj == null) {
					String error = "ログインに失敗しました";
					request.setAttribute("error", error);
					map = "login.jsp";
				} else {
					//ログイン成功
					session.setAttribute("UserObj", UserObj);
					map = "Index.jsp";
				}
			} else {
				String error = "不正な値が入力されています";
				request.setAttribute("error", error);
				map = "login.jsp";
			}
		}  else {
			AdminDAO adminDao = new AdminDAO();
			Admin adminObj = null;
			try {
				adminObj = adminDao.login(loginId, password);
				if(adminObj != null) {
					map = "adminIndex.jsp";
					session.setAttribute("adminObj", adminObj);
				}else {
					String error = "ログインに失敗しました";
					map = "adminLogin.jsp";
					request.setAttribute("error", error);
				}

			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				map = "error.jsp";
			}

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);
	}
}
