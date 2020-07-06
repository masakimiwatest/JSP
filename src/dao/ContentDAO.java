package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Card;
import bean.Content;

public class ContentDAO extends DAO {

	public List<Content> allSearch() throws Exception {
		List<Content> list = new ArrayList<>();
		Content content = null;
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from (Content inner join category on content.categoryId = category.categoryId) inner join subcategory on content.subcategoryid = subcategory.subcategoryid");
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			content = new Content();
			content.setContentId(rs.getInt("ContentId"));
			content.setContentName(rs.getString("ContentName"));
			content.setContentText(rs.getString("ContentText"));
			content.setContentData(rs.getString("ContentData"));
			content.setCategoryId(rs.getInt("CategoryId"));
			content.setSubCategoryId(rs.getInt("SubCategoryId"));
			content.setCategoryName(rs.getString("CategoryName"));
			content.setSubCategoryName(rs.getString("SubCategoryName"));
			content.setContentImage(rs.getString("contentimage"));
			content.setContentPoint(rs.getString("contentpoint"));
			list.add(content);
		}
		st.close();
		con.close();
		return list;
	}

	public List<Content> search(String searchWord) throws Exception {
		List<Content> list = new ArrayList<>();
		Content content = null;
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from (Content inner join category on content.categoryId = category.categoryId) inner join subcategory on content.subcategoryid = subcategory.subcategoryid and content.contentName=?");
		st.setString(1, searchWord);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			content = new Content();
			content.setContentId(rs.getInt("ContentId"));
			content.setContentName(rs.getString("ContentName"));
			content.setContentText(rs.getString("ContentText"));
			content.setContentData(rs.getString("ContentData"));
			content.setCategoryId(rs.getInt("CategoryId"));
			content.setSubCategoryId(rs.getInt("SubCategoryId"));
			content.setCategoryName(rs.getString("CategoryName"));
			content.setSubCategoryName(rs.getString("SubCategoryName"));
			content.setContentImage(rs.getString("contentimage"));
			content.setContentPoint(rs.getString("contentpoint"));
			list.add(content);
		}
		st.close();
		con.close();
		return list;
	}

	public List<Content> categorySearch(String categorySearchWord) throws Exception {
		List<Content> list = new ArrayList<>();
		Content content = null;
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from (Content inner join category on content.categoryId = category.categoryId) inner join subcategory on content.subcategoryid = subcategory.subcategoryid and category.categoryName=?");
		st.setString(1, categorySearchWord);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			content = new Content();
			content.setContentId(rs.getInt("ContentId"));
			content.setContentName(rs.getString("ContentName"));
			content.setContentText(rs.getString("ContentText"));
			content.setContentData(rs.getString("ContentData"));
			content.setCategoryId(rs.getInt("CategoryId"));
			content.setSubCategoryId(rs.getInt("SubCategoryId"));
			content.setCategoryName(rs.getString("CategoryName"));
			content.setSubCategoryName(rs.getString("SubCategoryName"));
			content.setContentImage(rs.getString("contentimage"));
			content.setContentPoint(rs.getString("contentpoint"));
			list.add(content);
		}
		st.close();
		con.close();
		return list;
	}

	public Content searchId(int contentId) throws Exception {
		Content content = null;
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from (Content inner join category on content.categoryId = category.categoryId) inner join subcategory on content.subcategoryid = subcategory.subcategoryid and content.contentId=?");
		st.setInt(1, contentId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			content = new Content();
			content.setContentId(rs.getInt("ContentId"));
			content.setContentName(rs.getString("ContentName"));
			content.setContentText(rs.getString("ContentText"));
			content.setContentData(rs.getString("ContentData"));
			content.setCategoryId(rs.getInt("CategoryId"));
			content.setSubCategoryId(rs.getInt("SubCategoryId"));
			content.setCategoryName(rs.getString("CategoryName"));
			content.setSubCategoryName(rs.getString("SubCategoryName"));
			content.setContentImage(rs.getString("contentimage"));
			content.setContentPoint(rs.getString("contentpoint"));
		}
		st.close();
		con.close();
		return content;
	}

	public int insert(Content contentObj) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("insert into content values(null, ?, ?, ?, ?, ?)");
		st.setString(1, contentObj.getContentName());
		st.setInt(2, contentObj.getCategoryId());
		st.setInt(3, contentObj.getCategoryId());
		st.setString(4, contentObj.getContentData());
		st.setString(5, contentObj.getContentText());

		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

	public int updatePoint(int contentId,double contentPoint) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("update content set contentpoint=? where contentid=?");
		st.setInt(2, contentId);
		st.setDouble(1, contentPoint);

		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

	public int update(Card CardObj) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con
				.prepareStatement("update  card set cardName=?,cardPrice=?,cardText=?,cardpicture=? where cardId=?");
		st.setString(1, CardObj.getCardName());
		st.setInt(2, CardObj.getCardPrice());
		st.setString(3, CardObj.getCardText());
		st.setInt(5, CardObj.getCardId());
		st.setString(4, CardObj.getCardPicture());

		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

}
