package manager;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Sale;
public class Csv extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Sale> list  = (List<Sale>) session.getAttribute("list2");
		System.out.println("list:"+list);
	     try {
	            //出力先を作成する

	            FileOutputStream fos = new FileOutputStream("C:\\sample\\sample.csv");
	            OutputStreamWriter osw = new OutputStreamWriter(fos, "SJIS");
	            PrintWriter pw = new PrintWriter(new BufferedWriter(osw));
	            pw.print("2019年3月分売上");

	            //内容を指定する
	            for(Sale a :list){
	            	System.out.println(a.getCardId());
	            	pw.println("SaleID：" + a.getSaleId());
	            	pw.println("購入者ID："+a.getCustomerId());
	            	pw.println("値段:"+a.getCardPrice());
	            	pw.println("小計:"+a.getCardPrice() * a.getSaleCount());
	            	pw.println("売上日："+a.getSaleDate());
	            	pw.println("枚数:"+a.getSaleCount());
	            	pw.println();
	            }
	            //ファイルに書き出す
	            pw.close();

	            //終了メッセージを画面に出力する

	        } catch (IOException ex) {
	            //例外時処理
	            System.out.println(ex);
	        }
	}
}