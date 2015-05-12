package com.oz.enroll.bean;

public class County  extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cname;
	private String ccode;
	private String cid;
	private String nid;
	
	public County() {}

	public County(String cname, String ccode, String cid, String nid) {
		super();
		this.cname = cname;
		this.ccode = ccode;
		this.cid = cid;
		this.nid = nid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	@Override
	public String getItem() {
		
		return getCname();
	}
	
	
	
	
}
