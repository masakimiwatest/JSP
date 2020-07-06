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
public class UserChange extends HttpServlet {
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User tranUserObj = (User)session.getAttribute("UserObj");
		String mail = (String) request.getParameter("mail");
		String userName = (String) request.getParameter("userName");
		String password = (String) request.getParameter("password");
		String map = "";
		int tranMail = 0;
		int tranUserName = 0;
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
				if(!(tranUserObj.getMail().equals(mail))) {
					System.out.println("test1");
					tranMail = userDao.tranMailSearch(mail);
				}
				if(!(tranUserObj.getUserName().equals(userName))) {
					System.out.println("test2"+tranUserObj.getUserName());
					tranUserName = userDao.tranUserNameSearch(userName);
					System.out.println(tranUserName);
				}

				if(tranMail != 0 ) {
					String error = "既に登録済みのメールアドレスです";
					request.setAttribute("error", error);
					map = "userCreate.jsp";
				}else if(tranUserName != 0) {
					String error = "既に登録済みのユーザー名です";
					System.out.println("test3");
					request.setAttribute("error", error);
					map = "userCreate.jsp";
				}else {
					userDao.update(userObj,tranUserObj.getUserId());
					userObj = userDao.nameSearch(userName);
					session.setAttribute("UserObj", userObj);
					map = "userSetting.jsp";
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
