package cn.tedu.note.entity;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable{
	private static final long serialVersionUID = -2677335708367280584L;
	
	private String id;
	private String name;
	private String context;//带格式的内容
	private String bz;//纯文本内容
	private Integer share;// 是否发表到论坛
	private Date createdate;//创建日期
	private Date updatedate;//更新日期
	private String owner;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Integer getShare() {
		return share;
	}
	public void setShare(Integer share) {
		this.share = share;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return "Note [id=" + id + ", name=" + name + ", context=" + context + ", bz=" + bz + ", share=" + share
				+ ", createdate=" + createdate + ", updatedate=" + updatedate + ", owner=" + owner + "]";
	}
	public Note(String id, String name, String context, String bz, Integer share, Date createdate, Date updatedate,
			String owner) {
		super();
		this.id = id;
		this.name = name;
		this.context = context;
		this.bz = bz;
		this.share = share;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.owner = owner;
	}
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}