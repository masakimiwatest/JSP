package shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDAO;
public class UserCreate extends HttpServlet {
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mail = (String) request.getParameter("mail");
		String userName = (String) request.getParameter("userName");
		String password = (String) request.getParameter("password");
		String map = "";



		boolean isMail = Validation.MailMatch(mail);
		boolean isUserName = Validation.StringMatch(userName);
		boolean isPassword = Validation.StringMatch(password);


		if (isUserName && isPassword && isMail) {
			UserDAO userDao = new UserDAO();
			User userObj = new User();
			userObj.setMail(mail);
			userObj.setPassword(password);
			userObj.setUserName(userName);
			try {

				int tranMail = userDao.tranMailSearch(mail);
				int tranUserName = userDao.tranUserNameSearch(userName);

				if(tranMail != 0 ) {
					String error = "既に登録済みのメールアドレスです";
					request.setAttribute("error", error);
					map = "userCreate.jsp";
				}else if(tranUserName != 0) {
					String error = "既に登録済みのユーザー名です";
					request.setAttribute("error", error);
					map = "userCreate.jsp";
				}else {
					userDao.insert(userObj);
					userObj = userDao.nameSearch(userName);
					session.setAttribute("UserObj", userObj);
					map = "userCreateComplete.jsp";
				}


			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				System.out.println(e);
				map ="error.jsp";
			}

		}else{
			String error = "不正な値が入力されています";
			request.setAttribute("error", error);
			map = "userCreate.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);
	}
}
