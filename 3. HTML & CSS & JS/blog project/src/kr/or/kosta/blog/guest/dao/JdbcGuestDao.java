package kr.or.kosta.blog.guest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;


public class JdbcGuestDao implements GuestDao{
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
	}

	@Override
	public void create(Guest guest) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO guestbook \r\n" + 
				     "VALUES     (GUESTBOOK_SEQ.NEXTVAL," + 
				     "            ?, \r\n" + 
				     "            ?, \r\n" + 
				     "            SYSDATE)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, guest.getUser_id());
			pstmt.setString(2, guest.getContents());
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
	}
	
	private Guest createGuest(ResultSet rs) throws SQLException{
		Guest guest = new Guest();
		guest.setGuestbook_id(rs.getString("guestbook_id"));
		guest.setUser_id(rs.getString("user_id"));
		guest.setContents(rs.getString("contents"));
		guest.setRegdate(rs.getString("regdate"));
		return guest;
	}

	@Override
	public List<Guest> listAll(String id) throws Exception {
		List<Guest> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		if(id.equals("")) {
			sql = "SELECT guestbook_id, user_id, contents, TO_CHAR(regdate, 'YYYY/MM/DD HH24:MI DAY') regdate\r\n" + 
					"FROM guestbook\r\n" + 
					"ORDER BY guestbook_id desc";
		}else {
			sql = "SELECT guestbook_id, user_id, contents, TO_CHAR(regdate, 'YYYY/MM/DD HH24:MI DAY') regdate\r\n" + 
					"FROM guestbook\r\n" + 
					"Where user_id = ?" + 
					"ORDER BY guestbook_id desc";
		}
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			if(!(id.equals(""))) {
				pstmt.setString(1, id);
			}
			rs = pstmt.executeQuery();
			list = new ArrayList<Guest>();
			while(rs.next()) {
				Guest guest = createGuest(rs);
				list.add(guest);
			}
		}finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		
		if(!(id.equals(""))) {
			try {
				sql = "SELECT guestbook_id, user_id, contents, TO_CHAR(regdate, 'YYYY/MM/DD HH24:MI DAY') regdate\r\n" + 
						"FROM guestbook\r\n" + 
						"WHERE user_id != ?\r\n" + 
						"ORDER BY guestbook_id desc";
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Guest guest = createGuest(rs);
					list.add(guest);
				}
			}finally {
				try {
					if(rs != null)    rs.close();
					if(pstmt != null) pstmt.close();
					if(con != null)   con.close();
				}catch (Exception e) {}
			}
		}
		
		return list;
	}

	
}
