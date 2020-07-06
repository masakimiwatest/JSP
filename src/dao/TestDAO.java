package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Test;
public class TestDAO extends DAO {

	public Test login(String testName, String testPass) throws Exception {
		Test test = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from test where testname=? and testpass=?");
		st.setString(1, testName);
		st.setString(2, testPass);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			test = new Test();
			test.setTestId(rs.getInt("testid"));
			test.setTestName(rs.getString("testname"));
			test.setTestPass(rs.getString("testpass"));
		}
		st.close();
		con.close();
		return test;
	}

}
