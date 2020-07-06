package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Admin;

public class AdminDAO extends DAO {
	public Admin login(String mail, String password) throws Exception {
		Admin admin = null;


		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from admin where adminname=? and adminpassword=?");
		st.setString(1, mail);
		st.setString(2, password);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			admin = new Admin();
			admin.setAdminId(rs.getInt("adminId"));
			admin.setAdminName(rs.getString("adminname"));
			admin.setAdminPassword(rs.getString("adminpassword"));
		}
		st.close();
		con.close();
		return admin;
	}

	public List<Admin> allSearch() throws Exception {
		List<Admin> list = new ArrayList<>();
		Admin admin = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from admin ");
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			admin = new Admin();
			admin.setAdminId(rs.getInt("adminId"));
			admin.setAdminName(rs.getString("adminname"));
			admin.setAdminPassword(rs.getString("adminpassword"));
			list.add(admin);
		}
		st.close();
		con.close();
		return list;
	}

	public int insert(Admin adminObj) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("insert into admin values(null, ?, ?)");
		st.setString(1, adminObj.getAdminName());
		st.setString(2, adminObj.getAdminPassword());
		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

	public int update(Admin adminObj) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con
				.prepareStatement("update  admin set adminName=?,adminpassword=? where adminId=?");
		st.setString(1, adminObj.getAdminName());
		st.setString(2, adminObj.getAdminPassword());
		st.setInt(3, adminObj.getAdminId());

		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

	public int delete(int adminId) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("delete from admin where adminId=?");
		st.setInt(1, adminId);
		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}
}
