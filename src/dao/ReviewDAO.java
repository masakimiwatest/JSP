package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Review;
public class ReviewDAO extends DAO {


	public List<Review> search(int contentId) throws Exception {
		List<Review> list = new ArrayList<>();
		Review review = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from review inner join user on review.userId = user.userId and contentId=?");
		st.setInt(1, contentId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			review = new Review();
			review.setReviewId(rs.getInt("reviewId"));
			review.setReviewText(rs.getString("reviewText"));
			review.setUserId(rs.getInt("userId"));
			review.setContentId(rs.getInt("contentId"));
			review.setReviewData(rs.getString("reviewdata"));
			review.setUserName(rs.getString("username"));
			review.setReviewPoint(rs.getInt("reviewpoint"));
			list.add(review);
		}
		st.close();
		con.close();
		return list;
	}

	public double numPoint(int contentId) throws Exception {
		Review review = null;
		double contentPoint = 0;
		double count = 0;
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from review inner join user on review.userId = user.userId and contentId=?");
		st.setInt(1, contentId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			count ++ ;
			review = new Review();
			contentPoint += (double)rs.getInt("reviewpoint");
		}
		contentPoint = contentPoint / count;
		st.close();
		con.close();
		return contentPoint;
	}

	public List<Review> userIdSearch(int userId) throws Exception {
		List<Review> list = new ArrayList<>();
		Review review = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from (Review inner join content on review.contentId = content.contentId) inner join user on review.userId = user.userId and review.userId=?");
		st.setInt(1, userId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			review = new Review();
			review.setReviewId(rs.getInt("reviewId"));
			review.setReviewText(rs.getString("reviewText"));
			review.setUserId(rs.getInt("userId"));
			review.setContentId(rs.getInt("contentId"));
			review.setReviewData(rs.getString("reviewdata"));
			review.setUserName(rs.getString("username"));
			review.setContentName(rs.getString("contentName"));
			review.setReviewPoint(rs.getInt("reviewpoint"));
			list.add(review);
		}
		st.close();
		con.close();
		return list;
	}

	public boolean tranSearch(int userId,int contentId) throws Exception {
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from Review where userid =? and contentid=?");
		st.setInt(1, userId);
		st.setInt(2, contentId);
		ResultSet rs = st.executeQuery();
		int count = 0;
		boolean isFlag = true;
		while (rs.next()) {
			count ++;
		}
		System.out.println(count);
		if(count != 0) {
			isFlag = false;
		}
		st.close();
		con.close();
		return isFlag;
	}


	public int userIdSearchCount(int userId) throws Exception {
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from review inner join user on review.userId = user.userId and review.userId=?");
		st.setInt(1, userId);
		ResultSet rs = st.executeQuery();
		int count = 0;
		while (rs.next()) {
			count ++;
		}
		st.close();
		con.close();
		return count;
	}


	public Review reviewIdSearch(int reviewId) throws Exception {
		Review review = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from review inner join user on review.userId = user.userId and review.reviewId=?");
		st.setInt(1, reviewId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			review = new Review();
			review.setReviewId(rs.getInt("reviewId"));
			review.setReviewText(rs.getString("reviewText"));
			review.setUserId(rs.getInt("userId"));
			review.setContentId(rs.getInt("contentId"));
			review.setReviewData(rs.getString("reviewdata"));
			review.setUserName(rs.getString("username"));
;			review.setReviewPoint(rs.getInt("reviewpoint"));
		}
		st.close();
		con.close();
		return review;
	}

	public int delete(int id) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("delete from review where reviewId=?");
		st.setInt(1, id);
		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

	public int update(String reviewText,int reviewId) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con
				.prepareStatement("update  review set reviewText=? where reviewId=?");
		st.setString(1, reviewText);
		st.setInt(2, reviewId);
		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

	public int insert(Review reviewObj) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("insert into review values(null, ?, ?, ?, ?, ?)");
		st.setString(1, reviewObj.getReviewText());
		st.setString(3, reviewObj.getReviewData());
		st.setInt(4, reviewObj.getUserId());
		st.setInt(2, reviewObj.getContentId());
		st.setInt(5, reviewObj.getReviewPoint());
		int line = st.executeUpdate();
		st.close();
		con.close();
		double contentPoint = numPoint(reviewObj.getContentId());
		BigDecimal bi = new BigDecimal(String.valueOf(contentPoint));
		double k1 = bi.setScale(1,BigDecimal.ROUND_DOWN).doubleValue();
		ContentDAO contentDao = new ContentDAO();
		contentDao.updatePoint(reviewObj.getContentId(), k1);
		return line;

	}

}