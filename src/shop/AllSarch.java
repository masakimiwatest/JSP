package shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Content;
import dao.ContentDAO;

public class AllSarch extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		Content content = null;
		ContentDAO contentDao = new ContentDAO();
		List<Content> contentList = new ArrayList<>();

		try {
			contentList = contentDao.allSearch();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String map = "cotentList.jsp";
		if(request.getParameter("admin") != null) {
			map = "adminContentList.jsp";
		}
		request.setAttribute("contentList", contentList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		Content content = null;
		ContentDAO contentDao = new ContentDAO();
		List<Content> contentList = new ArrayList<>();

		try {
			contentList = contentDao.allSearch();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String map = "cotentList.jsp";
		if(request.getParameter("admin") != null) {
			map = "adminContentList.jsp";
		}
		request.setAttribute("contentList", contentList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);

	}
}