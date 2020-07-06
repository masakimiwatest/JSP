package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Customer;
public class CustomerDAO extends DAO{

	public List<Customer> allSearch() throws Exception{
		List<Customer> list = new ArrayList<>();
		Customer customer = null;

		Connection con =getConnection();
		PreparedStatement st;
		st=con.prepareStatement("select * from customer");
		ResultSet rs=st.executeQuery();

		while(rs.next()){
			customer=new Customer();
			customer.setCustomerId(rs.getInt("customerId"));
			customer.setCustomerUserId(rs.getString("customerUserId"));
			customer.setCustomerPassword(rs.getString("customerPassword"));
			customer.setCustomerName(rs.getString("customerName"));
			customer.setCustomerAddress(rs.getString("customerAddress"));
			customer.setCustomerCode(rs.getInt("customerCode"));
			customer.setCustomerMail(rs.getString("customerMail"));
			customer.setCustomerTel(rs.getString("customertel"));
			list.add(customer);
		}
		st.close();
		con.close();
		return list;
	}

	public Customer login(String login,String password) throws Exception{
		Customer customer = null;

		Connection con =getConnection();
		PreparedStatement st;
		st=con.prepareStatement("select * from customer where customerUserId=? and customerPassword=?");
		st.setString(1, login);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();

		while(rs.next()){
			customer=new Customer();
			customer.setCustomerId(rs.getInt("customerId"));
			customer.setCustomerUserId(rs.getString("customerUserId"));
			customer.setCustomerPassword(rs.getString("customerPassword"));
			customer.setCustomerName(rs.getString("customerName"));
			customer.setCustomerAddress(rs.getString("customerAddress"));
			customer.setCustomerCode(rs.getInt("customerCode"));
			customer.setCustomerMail(rs.getString("customerMail"));
			customer.setCustomerTel(rs.getString("customertel"));
		}

		st.close();
		con.close();
		return customer;
	}

	public Customer search(int id) throws Exception{
		Customer customer = null;

		Connection con =getConnection();
		PreparedStatement st;
		st=con.prepareStatement("select * from customer where customerId=?");
		st.setInt(1, id);
		ResultSet rs=st.executeQuery();

		while(rs.next()){
			customer=new Customer();
			customer.setCustomerId(rs.getInt("customerId"));
			customer.setCustomerUserId(rs.getString("customerUserId"));
			customer.setCustomerPassword(rs.getString("customerPassword"));
			customer.setCustomerName(rs.getString("customerName"));
			customer.setCustomerAddress(rs.getString("customerAddress"));
			customer.setCustomerCode(rs.getInt("customerCode"));
			customer.setCustomerMail(rs.getString("customerMail"));
			customer.setCustomerTel(rs.getString("customertel"));
		}
		st.close();
		con.close();
		return customer;
	}

	public int tranSearch(String UserId) throws Exception{

		Connection con =getConnection();
		PreparedStatement st;
		st=con.prepareStatement("select * from customer where customerUserId=?");
		st.setString(1, UserId);
		ResultSet rs = st.executeQuery();
		int line = 0;
		while(rs.next()){
			line++;
		}
		return line;
	}

	public int update(Customer CustomerObj,int customerid) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("update  customer set customerUserId=?,customerPassword=?,customerName=?,customerAddress=?,customerCode=?,customerMail=?,customertel=? where customerId=?");
		st.setString(1, CustomerObj.getCustomerUserId());
		st.setString(2, CustomerObj.getCustomerPassword());
		st.setString(3, CustomerObj.getCustomerName());
		st.setString(4, CustomerObj.getCustomerAddress());
		st.setInt(5, CustomerObj.getCustomerCode());
		st.setString(6, CustomerObj.getCustomerMail());
		st.setString(7, CustomerObj.getCustomerTel());
		st.setInt(8, customerid);
		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

	public int delete(int id) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("delete from customer where customerId=?");
		st.setInt(1, id);
		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

	public int insert(Customer CustomerObj) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("insert into customer values(null, ?, ?, ?, ?, ?, ?, ?)");
		st.setString(1, CustomerObj.getCustomerUserId());
		st.setString(2, CustomerObj.getCustomerName());
		st.setString(3, CustomerObj.getCustomerAddress());
		st.setInt(4, CustomerObj.getCustomerCode());
		st.setString(5, CustomerObj.getCustomerMail());
		st.setString(6, CustomerObj.getCustomerPassword());
		st.setString(7, CustomerObj.getCustomerTel());

		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

}
