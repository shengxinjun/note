package cn.tedu.note.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable{
	private static final long serialVersionUID = -2677335708367280584L;
	
	private String id;
	private String context;
	private Date date;
	private String user_id;
	private String note_id;
	private String bz;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getNote_id() {
		return note_id;
	}
	public void setNote_id(String note_id) {
		this.note_id = note_id;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", context=" + context + ", date=" + date + ", user_id=" + user_id + ", note_id="
				+ note_id + ", bz=" + bz + "]";
	}
	public Comment(String id, String context, Date date, String user_id, String note_id, String bz) {
		super();
		this.id = id;
		this.context = context;
		this.date = date;
		this.user_id = user_id;
		this.note_id = note_id;
		this.bz = bz;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
}
