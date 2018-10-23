package kr.or.kosta.blog.article.dao;

public class Article {
	private int article_id;		//게시글 식별번호
	private int board_id;		//소속 게시판번호
	private String writer;		//작성자 아이디
	private String subject;		//게시글 제목
	private String content;		//게시글 내용
	private String regdate;		//게시글 등록일자
	private int hitcount;		//게시글 조회수
	private String ip;			//작성자 아이피
	private String passwd;		//게시글 비밀번호
	private String attach_file;	//첨부파일
	private int group_no;		//계층형 게시판 구조를 위한 게시글 그룹번호
	private int level_no;		//계층형 게시판 구조를 위한 그룹내 게시글 레벨
	private int order_no;		//계층형 게시판 구조를 위한 그룹내 게시글 순서
	
	
	public Article() {
		super();
	}


	public Article(int article_id, int board_id, String writer, String subject, String content, String regdate,
			int hitcount, String ip, String passwd, String attach_file, int group_no, int level_no, int order_no) {
		super();
		this.article_id = article_id;
		this.board_id = board_id;
		this.writer = writer;
		this.subject = subject;
		this.content = content;
		this.regdate = regdate;
		this.hitcount = hitcount;
		this.ip = ip;
		this.passwd = passwd;
		this.attach_file = attach_file;
		this.group_no = group_no;
		this.level_no = level_no;
		this.order_no = order_no;
	}


	public int getArticle_id() {
		return article_id;
	}


	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}


	public int getBoard_id() {
		return board_id;
	}


	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	public int getHitcount() {
		return hitcount;
	}


	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public String getAttach_file() {
		return attach_file;
	}


	public void setAttach_file(String attach_file) {
		this.attach_file = attach_file;
	}


	public int getGroup_no() {
		return group_no;
	}


	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}


	public int getLevel_no() {
		return level_no;
	}


	public void setLevel_no(int level_no) {
		this.level_no = level_no;
	}


	public int getOrder_no() {
		return order_no;
	}


	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}


	@Override
	public String toString() {
		return "Article [article_id=" + article_id + ", board_id=" + board_id + ", writer=" + writer + ", subject="
				+ subject + ", content=" + content + ", regdate=" + regdate + ", hitcount=" + hitcount + ", ip=" + ip
				+ ", passwd=" + passwd + ", attach_file=" + attach_file + ", group_no=" + group_no + ", level_no="
				+ level_no + ", order_no=" + order_no + "]";
	}
	
}
