package sports.location.model;

import java.sql.Timestamp;

public class LocationVO {

	private int lno;
	private String writer;
	private String title;
	private String content;
	private Timestamp regdate;
	
	public LocationVO() { //기본생성자
	}

	public LocationVO(int lno, String writer, String title, String content, Timestamp regdate) {
		super();
		this.lno = lno;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}

	public int getLno() {
		return lno;
	}

	public void setLno(int lno) {
		this.lno = lno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	
	
	
}
