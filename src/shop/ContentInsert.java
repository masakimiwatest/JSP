package shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Content;
import dao.ContentDAO;

public class ContentInsert extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Content contentObj = new Content();
		ContentDAO contentDao = new ContentDAO();

		int category = Integer.parseInt(request.getParameter("category"));
		String contentText = request.getParameter("contentText");
		String contentName = request.getParameter("contentName");
		String contentData = request.getParameter("contentData");
		contentObj.setCategoryId(category);
		contentObj.setContentName(contentName);
		contentObj.setContentText(contentText);
		contentObj.setContentData(contentData);
		try {
			contentDao.insert(contentObj);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("AllSarch");
		dispatcher.forward(request, response);

	}
}