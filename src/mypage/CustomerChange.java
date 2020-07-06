package mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Customer;
import dao.CustomerDAO;

public class CustomerChange extends HttpServlet{
	public void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {

		//デフォルトでマップ先を指定しておく
		String map = "Mypage.jsp";

		//リクエストから、新しいデータを得る（customerIdなし）
		Customer CustomerObjNew=(Customer)request.getAttribute("CustomerObj");

		//セッションから既存のデータを得る（customerIdあり）
		HttpSession session = request.getSession();
		Customer CustomerObjOld=(Customer)session.getAttribute("CustomerObj");

		//customerIdを得る
		int customerId=CustomerObjOld.getCustomerId();



		CustomerDAO CustomerDAO =new CustomerDAO();

		int lineCustomerChange=0;

		try {
			lineCustomerChange = (Integer)CustomerDAO.update(CustomerObjNew,customerId);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if(lineCustomerChange==1){//情報変更成功
			System.out.println("情報変更成功");

			//sessionを新しいデータに上書きする
			CustomerObjNew.setCustomerId(customerId);
			session.setAttribute("CustomerObj", CustomerObjNew);//追加//名前が一緒の場合は、前のセッションデータは消える

			// 必要ないです。上で上書きされたので。session.removeAttribute("CustomerObjOld");//削除

		}else{//情報変更失敗
			System.out.println("情報変更失敗");
			String error = "miss";
			request.setAttribute("error", error);
			map="Customer-Change.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(map);
		dispatcher.forward(request, response);

	}
}