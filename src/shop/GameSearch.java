package shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Content;
import dao.ContentDAO;

public class GameSearch extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ContentDAO contentDao = new ContentDAO();
		List<Content> contentList = new ArrayList<>();

		try {
			contentList = contentDao.categorySearch("GAME");
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.setAttribute("contentList", contentList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cotentList.jsp");
		dispatcher.forward(request, response);

	}
}