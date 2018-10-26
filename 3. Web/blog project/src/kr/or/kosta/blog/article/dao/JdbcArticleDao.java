package kr.or.kosta.blog.article.dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.or.kosta.blog.common.Params;

public class JdbcArticleDao implements ArticleDao {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
	}
	/*
	 * 신규 게시물 등록
	 */
	@Override
	public boolean create(Article article) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
	    InetAddress local = InetAddress.getLocalHost();
	    String ip = local.getHostAddress();
		String sql = "INSERT INTO article \r\n" + 
					"            (article_id, \r\n" + 
					"             board_id, \r\n" + 
					"             writer, \r\n" + 
					"             subject, \r\n" + 
					"             content, \r\n" + 
					"             regexp_substr(ip,trim(regexp_substr(ip , '[^.]+', 1,1)))||'.'||regexp_substr(ip,trim(regexp_substr(ip , '[^.]+', 1,2))) ip, \r\n" + 
					"             passwd, \r\n" + 
					"             group_no, \r\n" + 
					"             level_no, \r\n" + 
					"             order_no) \r\n" + 
					"VALUES     (article_id_seq.nextval, \r\n" + 
					"            1, \r\n" + 
					"            ?, \r\n" + 
					"            ?, \r\n" + 
					"            ?, \r\n" + 
					"            ?, \r\n" + 
					"            ?, \r\n" + 
					"            article_id_seq.currval, \r\n" + 
					"            0, \r\n" + 
					"            0)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, ip);
			pstmt.setString(5, article.getPasswd());
			pstmt.executeUpdate();
		}catch(Exception e){
			return false;
		}
		finally {
		
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return true;
	}
	
	/*
	 * 같은 level_no의 order_no 찾기 (
	 */
	public String buildOrderNo(String groupNo, String levelNo, String orderNo) throws Exception {
		int result = 99999;
		int orderNoTemp = result;
		for (int i = Integer.parseInt(levelNo); i >= 1; i--) {
			orderNoTemp = searchOrderNo(groupNo, i, orderNo);
			if(orderNoTemp > 0) {
				result = orderNoTemp < result ? orderNoTemp : result;
			}
		}
		if (result < 0 || result == 99999) {
			result = searchMaxOrderNo(groupNo);
		} else {
			updateOrderNo(groupNo, String.valueOf(result));
		}
		return String.valueOf(result);
	}
	private int searchOrderNo(String groupNo, int levelNo, String orderNo) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT Min(order_no) order_no \r\n" + "FROM   article \r\n" + "WHERE  group_no = ? \r\n"
				+ "       AND level_no = ? \r\n" + "       AND order_no > ?";
		int result = 99999;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, groupNo);
			pstmt.setInt(2, levelNo);
			pstmt.setString(3, orderNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("order_no");
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}

	private int searchMaxOrderNo(String groupNo) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT Max(order_no) + 1 order_no \r\n" + "FROM   article \r\n" + "WHERE  group_no = ?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, groupNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("order_no");
			}
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return result;
	}
	
	private void updateOrderNo(String groupNo, String orderNo) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE article \r\n" + "SET    order_no = order_no + 1 \r\n" + "WHERE  board_id = 1 \r\n"
				+ "       AND group_no = ? \r\n" + "       AND order_no >= ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, groupNo);
			pstmt.setString(2, orderNo);
			pstmt.executeQuery();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
	}

	/*
	 * 댓글 등록
	 */
	@Override
	public boolean create(Article article, int pOrderId) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String forderID = buildOrderNo(String.valueOf(article.getGroup_no()), String.valueOf(article.getOrder_no()), String.valueOf(article.getLevel_no()));
		InetAddress local = InetAddress.getLocalHost();
		String ip = local.getHostAddress();
//		System.out.println("받은 거 " + forderID);
		String sql = "INSERT INTO article \r\n" + 
						"            (article_id, \r\n" + 
						"             board_id, \r\n" + 
						"             writer, \r\n" + 
						"             subject, \r\n" + 
						"             content, \r\n" + 
						"             regexp_substr(ip,trim(regexp_substr(ip , '[^.]+', 1,1)))||'.'||regexp_substr(ip,trim(regexp_substr(ip , '[^.]+', 1,2))) ip, \r\n" + 
						"             passwd, \r\n" + 
						"             group_no, \r\n" + 
						"             level_no, \r\n" + 
						"             order_no) \r\n" + 
						"VALUES     (article_id_seq.nextval, \r\n" + 
						"            1, \r\n" + 
						"            ?, \r\n" + 
						"            ?, \r\n" + 
						"            ?, \r\n" + 
						"            ?, \r\n" + 
						"            ?, \r\n" + 
						"            ?, \r\n" + 
						"            ?, \r\n" + 
						"            ?)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, ip);
			pstmt.setString(5, article.getPasswd());
			pstmt.setString(6, String.valueOf(article.getGroup_no()));
			pstmt.setString(7, String.valueOf(article.getLevel_no()+1));
			if(forderID == null) {
//				System.out.println("null??");
				pstmt.setString(8, String.valueOf(article.getOrder_no()+1));
			}else {
//				System.out.println("부모");
				pstmt.setString(8, forderID);
			}
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
//			System.out.println("insert error");
			return false;
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return true;
	}
	
	/*
	 * 게시글 읽기
	 */
	@Override
	public Article read(String article_id) throws Exception {
		Article article = null;
		Connection con =  null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT  article_id, \r\n" + 
				"             board_id, \r\n" + 
				"             writer, \r\n" + 
				"             subject, \r\n" + 
				"             content, \r\n" + 
				"             regdate, \r\n" + 
				"             hitcount, \r\n" + 
				"             regexp_substr(ip,trim(regexp_substr(ip , '[^.]+', 1,1)))||'.'||regexp_substr(ip,trim(regexp_substr(ip , '[^.]+', 1,2))) ip, \r\n" + 
				"             passwd, \r\n" + 
				"             group_no, \r\n" + 
				"             level_no, \r\n" + 
				"             order_no \r\n" + 
				"FROM article\r\n" + 
				"WHERE board_id = 1 AND article_id=?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = createArticle(rs);
//				article.setArticle_id(rs.getInt("article_id"));
//				article.setSubject(rs.getString("subject"));
//				article.setWriter(rs.getString("writer"));
//				article.setRegdate(rs.getString("regdate"));
//				article.setIp(rs.getString("ip"));
//				article.setHitcount(rs.getInt("hitcount"));
//				article.setContent(rs.getString("content"));
			}
		}catch(Exception e){
			return null;
		}
		finally {
		
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return article;
	}
	
	/*
	 * 조회수 증가
	 */
	@Override
	public void countUp(String article_id) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE article \r\n" + 
				"SET    hitcount = hitcount + 1 \r\n" + 
				"WHERE  board_id = 1 \r\n" + 
				"       AND article_id = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article_id);
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
	}

	/*
	 * 게시글 수정
	 */
	@Override
	public boolean update(Article article) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		//System.out.println(article.getSubject());
		//System.out.println(article.getContent());
		//System.out.println(article.getArticle_id());
		String sql = "UPDATE article\r\n" + 
				"SET subject=?, content=? \r\n" + 
				"WHERE board_id = 1 AND article_id=?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getSubject());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getArticle_id());
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("error");
			return false;
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return true;
	}

	/*
	 * 게시글 삭제
	 */
	@Override
	public void delete(String article_id) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE article \r\n" + 
				"SET    subject = '게시글이 삭제되었습니다', \r\n" + 
				"       content = '게시글이 삭제되었습니다' \r\n" + 
				"WHERE 	article_id=?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article_id);
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("error");
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		System.out.println("삭제");
	}

	@Override
	public boolean isdelete(String article_id) throws Exception {
		Article article = read(article_id);
		if(article.getContent().equals("게시글이 삭제되었습니다")) {
			return true;
		}
		return false;
	}

	public List<Article> listByPage(int page, int listSize, String searchType, String searchValue) throws Exception{
		List<Article> list = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT article_id, \r\n" +
				"			subject, \r\n" + 
				"			content, \r\n" + 
				"       	writer, \r\n" + 
				"       	regdate, \r\n" + 
				"       	regexp_substr(ip,trim(regexp_substr(ip , '[^.]+', 1,1)))||'.'||regexp_substr(ip,trim(regexp_substr(ip , '[^.]+', 1,2))) ip, \r\n" + 
				"       	hitcount \r\n"; 
//		if(searchType != null) {
//			sql+="FROM article WHERE group_no like (SELECT group_no ";	
//		}
		sql+=	"	FROM   (SELECT CEIL(rownum / ?) request_page, \r\n" + 
				"	               article_id, \r\n" + 
				"	               subject, \r\n" + 
				" 	               content, \r\n" + 
				"	               writer, \r\n" + 
				" 	               TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI') regdate, \r\n" + 
				"	               ip, \r\n" + 
				"	               hitcount \r\n" + 
				"	        FROM   (SELECT article_id, \r\n" + 
				"	                       subject, \r\n" + 
				"	                       content, \r\n" + 
				"	                       writer, \r\n" + 
				"	                       regdate, \r\n" + 
				"	                       ip, \r\n" + 
				"	                       hitcount \r\n" + 
				"	                FROM   article \r\n" + 
				"					WHERE ";
		
		// 검색 유형별 WHERE 절 동적 추가
		if(searchType != null) {
			switch (searchType) {
			case "subject":		//글제목
				sql+="subject like ? AND ";
				searchValue = "%" + searchValue + "%";
				break;
			case "content":		//작성자
				sql+="content like ? AND ";
				searchValue = "%" + searchValue + "%";
				break;
			case "writer":		//작성자
				sql+="writer=? AND ";
				break;
			default:
				break;
			}
		}
		sql += 	"                board_id = 1  \r\n" + 
				"                ORDER  BY group_no DESC, \r\n" + 
				"                          order_no ASC, \r\n" + 
				"                          level_no ASC)) \r\n" + 
				"WHERE request_page = ?";
//		if(searchType!=null) {
//			sql+=")";
//		}
		//order by
		//sql += "";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, listSize);
			
			// 전체검색이 아닌경우 경우
			if(searchType != null){
				pstmt.setString(2, searchValue);
				pstmt.setInt(3, page);
			}else {
				pstmt.setInt(2, page);
			}
			rs = pstmt.executeQuery();
			list = new ArrayList<Article>();
			while (rs.next()) {
				Article article = new Article();
				article.setArticle_id(rs.getInt("article_id"));
				article.setSubject(rs.getString("subject"));
				article.setWriter(rs.getString("writer"));
				article.setRegdate(rs.getString("regdate"));
				article.setIp(rs.getString("ip"));
				article.setHitcount(rs.getInt("hitcount"));
				list.add(article);
			}
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return list;
	}

	@Override
	public List<Article> listByPage(Params params) throws Exception {
		return listByPage(params.getPage(), params.getListSize(),  params.getSearchType(), params.getSearchValue());
	}

	public int countBySearch(String searchType, String searchValue) throws Exception {
		int count = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(article_id) count from article";
		
		// 검색 유형별 WHERE 절 동적 추가
		if(searchType != null) {
//			sql+="WHERE group_no like (select group_no from article ";
			switch (searchType) {
			case "subject":		//글제목
				sql+=" WHERE subject like ?";
				searchValue = "%" + searchValue + "%";
				break;
			case "content":		//작성자
				sql+=" WHERE content like ?";
				searchValue = "%" + searchValue + "%";
				break;
			case "writer":		//작성자
				sql+=" WHERE writer=?";
				break;
			default:
				break;
			}
//			sql+=")";
		}
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			
			// 전체검색이 아닌경우 경우
			if(searchType != null){
				pstmt.setString(1, searchValue);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return count;
	}
	@Override
	public int countBySearch(Params params) throws Exception {
		return countBySearch(params.getSearchType(), params.getSearchValue());
	}
	
	public Article createArticle(ResultSet rs) throws NumberFormatException, SQLException {
		Article article = new Article();
		article.setArticle_id(Integer.parseInt(rs.getString("article_id")));
		article.setBoard_id(Integer.parseInt(rs.getString("board_id")));
		article.setWriter(rs.getString("writer"));
		article.setSubject(rs.getString("subject"));
		article.setContent(rs.getString("content"));
		article.setRegdate(rs.getString("regdate"));
		article.setHitcount(rs.getInt("hitcount"));
		article.setIp(rs.getString("ip"));
		article.setPasswd(rs.getString("passwd"));
		article.setGroup_no(Integer.parseInt(rs.getString("group_no")));
		article.setLevel_no(Integer.parseInt(rs.getString("level_no")));
		article.setOrder_no(Integer.parseInt(rs.getString("order_no")));
		return article;
	}

}
