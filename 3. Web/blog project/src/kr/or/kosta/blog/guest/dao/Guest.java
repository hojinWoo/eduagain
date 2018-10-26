package kr.or.kosta.blog.guest.dao;

public class Guest {
	private String guestbook_id;
	private String user_id;
	private String contents;
	private String regdate;
	
	public Guest() {
		super();
	}
	
	public Guest(String user_id, String contents) {
		super();
		this.user_id = user_id;
		this.contents = contents;
	}

	public Guest(String guestbook_id, String user_id, String contents, String regdate) {
		super();
		this.guestbook_id = guestbook_id;
		this.user_id = user_id;
		this.contents = contents;
		this.regdate = regdate;
	}

	public String getGuestbook_id() {
		return guestbook_id;
	}

	public void setGuestbook_id(String guestbook_id) {
		this.guestbook_id = guestbook_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Guest [guestbook_id=" + guestbook_id + ", user_id=" + user_id + ", contents=" + contents + ", regdate="
				+ regdate + "]";
	}
	
}
