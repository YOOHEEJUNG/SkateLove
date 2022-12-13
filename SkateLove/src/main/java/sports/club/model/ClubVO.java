package sports.club.model;

import java.sql.Timestamp;

public class ClubVO {

	private int cno;
	private String writer;
	private String title;
	private String content;
	private Timestamp regdate;

	public ClubVO() {
		
	}

	public ClubVO(int cno, String writer, String title, String content, Timestamp regdate) {
		this.cno = cno;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
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
