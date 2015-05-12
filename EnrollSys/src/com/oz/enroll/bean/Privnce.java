package com.oz.enroll.bean;

public class Privnce  extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pname;
	private String pid;
	private String pcode;
	
	public Privnce() {}
	
	public Privnce(String pname, String pid, String pcode) {
		super();
		this.pname = pname;
		this.pid = pid;
		this.pcode = pcode;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	@Override
	public String getItem() {
		// TODO Auto-generated method stub
		return pname;
	}
	
	
	
	
}
