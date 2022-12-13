package sports.re.model;

import java.sql.Timestamp;

public class ReVO {

	private int rno;
	private String writer;
	private String title;
	private String content;
	private Timestamp regdate;
	
	public ReVO() { //기본생성자
	}

	public ReVO(int rno, String writer, String title, String content, Timestamp regdate) {
		super();
		this.rno = rno;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
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
