package sports.chat.model;

import java.sql.Timestamp;

public class ChatVO {

	private int chno;
	private String writer;
	private String title;
	private String content;
	private Timestamp regdate;

	public ChatVO() {
		
	}

	public ChatVO(int chno, String writer, String title, String content, Timestamp regdate) {
		this.chno = chno;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}

	public int getChno() {
		return chno;
	}

	public void setChno(int chno) {
		this.chno = chno;
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
