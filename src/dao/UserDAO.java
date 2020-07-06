package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.User;

public class UserDAO extends DAO {
	public User login(String mail, String password) throws Exception {
		User user = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from user where mail=? and Password=?");
		st.setString(1, mail);
		st.setString(2, password);
		System.out.println(mail);
		System.out.println(password);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			user = new User();
			user.setUserId(rs.getInt("userId"));
			user.setUserName(rs.getString("username"));
			user.setMail(rs.getString("mail"));
			user.setPassword(rs.getString("password"));
		}

		st.close();
		con.close();
		return user;
	}

	public List<User> allSearch() throws Exception {
		List<User> list = new ArrayList<>();
		User user = null;
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from User");
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			user = new User();
			user.setUserId(rs.getInt("userId"));
			user.setUserName(rs.getString("username"));
			user.setMail(rs.getString("mail"));
			user.setPassword(rs.getString("password"));
			System.out.println(user.getUserName());
			list.add(user);
		}
		st.close();
		con.close();
		return list;
	}

	public User nameSearch(String userName) throws Exception {
		User user = null;
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from User where username like ? ");
		st.setString(1, userName);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			user = new User();
			user.setUserId(rs.getInt("userId"));
			user.setUserName(rs.getString("username"));
			user.setMail(rs.getString("mail"));
			user.setPassword(rs.getString("password"));
		}
		st.close();
		con.close();
		return user;
	}

	public User idSearch(int userId) throws Exception {
		User user = null;
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from User where userid like ? ");
		st.setInt(1, userId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			user = new User();
			user.setUserId(rs.getInt("userId"));
			user.setUserName(rs.getString("username"));
			user.setMail(rs.getString("mail"));
			user.setPassword(rs.getString("password"));
		}
		st.close();
		con.close();
		return user;
	}

	public int insert(User userObj) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("insert into user values(null, ?, ?, ?)");
		st.setString(1, userObj.getUserName());
		st.setString(2, userObj.getMail());
		st.setString(3, userObj.getPassword());
		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

	public int tranUserNameSearch(String userName) throws Exception {

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from user where username=?");
		st.setString(1, userName);
		ResultSet rs = st.executeQuery();
		int line = 0;
		while (rs.next()) {
			line++;
		}
		return line;
	}

	public int tranMailSearch(String mail) throws Exception {

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from user where mail=?");
		st.setString(1, mail);
		ResultSet rs = st.executeQuery();
		int line = 0;
		while (rs.next()) {
			line++;
		}
		return line;
	}

	public int update(User userObj,int userId) throws Exception {
		System.out.println("test3");
		System.out.println(userId);
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("update  user set Password=?,userName=?,Mail=? where userId=?");
		st.setString(1, userObj.getPassword());
		st.setString(2, userObj.getUserName());
		st.setString(3, userObj.getMail());
		st.setInt(4, userId);
		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}


	public int delete(int userId) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("delete from user where userId=?");
		st.setInt(1, userId);
		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}
















}
