package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Card;

public class CardDAO extends DAO {
	public List<Card> genreSearchPriceAsc(int genreId) throws Exception {
		List<Card> list = new ArrayList<>();
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from card inner join genre on card.genreId = genre.genreId and card.genreid=? ORDER BY cardprice ASC");
		st.setInt(1, genreId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
			list.add(card);
		}
		st.close();
		con.close();
		return list;
	}

	public List<Card> genreSearchPriceDesc(int genreId) throws Exception {
		List<Card> list = new ArrayList<>();
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from card inner join genre on card.genreId = genre.genreId and card.genreid=? ORDER BY cardprice DESC");
		st.setInt(1, genreId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
			list.add(card);
		}
		st.close();
		con.close();
		return list;
	}

	public List<Card> genreSearchNameDesc(int genreId) throws Exception {
		List<Card> list = new ArrayList<>();
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from card inner join genre on card.genreId = genre.genreId and card.genreid=? ORDER BY cardname DESC");
		st.setInt(1, genreId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
			list.add(card);
		}
		st.close();
		con.close();
		return list;
	}

	public List<Card> genreSearchNameAsc(int genreId) throws Exception {
		List<Card> list = new ArrayList<>();
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from card inner join genre on card.genreId = genre.genreId and card.genreid=? ORDER BY cardname ASC");
		st.setInt(1, genreId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
			list.add(card);
		}
		st.close();
		con.close();
		return list;
	}

	public List<Card> listSearchNameAsc(String cardName) throws Exception {
		List<Card> list = new ArrayList<>();
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from card inner join genre on card.genreId = genre.genreId and cardName like ? ORDER BY cardname ASC");
		st.setString(1, "%"+cardName+"%");
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
			list.add(card);
		}
		st.close();
		con.close();
		return list;
	}

	public List<Card> listSearchNameDesc(String cardName) throws Exception {
		List<Card> list = new ArrayList<>();
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from card inner join genre on card.genreId = genre.genreId and cardName like ? ORDER BY cardname DESC");
		st.setString(1, "%"+cardName+"%");
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
			list.add(card);
		}
		st.close();
		con.close();
		return list;
	}


	public List<Card> listSearchPriceDesc(String cardName) throws Exception {
		List<Card> list = new ArrayList<>();
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from card inner join genre on card.genreId = genre.genreId and cardName like ? ORDER BY cardprice DESC");
		st.setString(1, "%"+cardName+"%");
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
			list.add(card);
		}
		st.close();
		con.close();
		return list;
	}

	public List<Card> listSearchPriceAsc(String cardName) throws Exception {
		List<Card> list = new ArrayList<>();
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from card inner join genre on card.genreId = genre.genreId and cardName like ? ORDER BY cardprice ASC");
		st.setString(1, "%"+cardName+"%");
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
			list.add(card);
		}
		st.close();
		con.close();
		return list;
	}


	public List<Card> allSearch() throws Exception {
		List<Card> list = new ArrayList<>();
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from card inner join genre on card.genreId = genre.genreId");
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
			list.add(card);
		}
		st.close();
		con.close();
		return list;
	}

	public List<Card> listSearch(String cardName) throws Exception {
		List<Card> list = new ArrayList<>();
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from card inner join genre on card.genreId = genre.genreId and cardName like ?");
		st.setString(1, "%"+cardName+"%");
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
			list.add(card);
		}
		st.close();
		con.close();
		return list;
	}

	public Card Search(String cardName) throws Exception {
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from card inner join genre on card.genreId = genre.genreId and card.cardName=?");
		st.setString(1, cardName);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
		}
		st.close();
		con.close();
		return card;
	}

	public Card idSearch(int cardId) throws Exception {
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from card inner join genre on card.genreId = genre.genreId and card.cardid=?");
		st.setInt(1, cardId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
		}
		st.close();
		con.close();
		return card;
	}

	public List<Card> genreSearch(int genreId) throws Exception {
		List<Card> list = new ArrayList<>();
		Card card = null;

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from card inner join genre on card.genreId = genre.genreId and card.genreid=?");
		st.setInt(1, genreId);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			card = new Card();
			card.setCardId(rs.getInt("cardId"));
			card.setCardPrice(rs.getInt("cardPrice"));
			card.setCardName(rs.getString("cardName"));
			card.setGenreId(rs.getInt("genreId"));
			card.setGenreName(rs.getString("genreName"));
			card.setCardStock(rs.getInt("cardStock"));
			card.setCardText(rs.getString("cardText"));
			card.setCardPicture(rs.getString("cardpicture"));
			list.add(card);
		}
		st.close();
		con.close();
		return list;
	}

	public int transearch(String cardName) throws Exception {

		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from card where cardName=?");
		st.setString(1, cardName);
		ResultSet rs = st.executeQuery();
		int line = 0;
		while (rs.next()) {
			line++;
		}
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

	public int delete(int id) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("delete from card where cardId=?");
		st.setInt(1, id);
		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

	public int stockMinus(int id, int cardStock) throws Exception {
		int line = 0;
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement("select * from card where cardId=?");
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		rs.next();
		int stock = rs.getInt("cardstock");
		stock = stock - cardStock;
		if (stock < 0) {
			line = 0;
		} else {

			st = con.prepareStatement("update  card set cardStock=? where cardId=?");
			st.setInt(1, stock);
			st.setInt(2, id);

			line = st.executeUpdate();
		}
		st.close();
		con.close();
		return line;

	}

	public int insert(Card cardObj) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("insert into card values(null, ?, ?, ?, ?, ?, ?)");
		st.setInt(1, cardObj.getCardPrice());
		st.setString(2, cardObj.getCardName());
		st.setInt(6, cardObj.getGenreId());
		st.setInt(3, cardObj.getCardStock());
		st.setString(4, cardObj.getCardText());
		st.setString(5, cardObj.getCardPicture());

		int line = st.executeUpdate();
		st.close();
		con.close();

		return line;

	}

}