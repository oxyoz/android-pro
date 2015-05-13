package com.oz.m;

public class Netherlands  extends Entity{

	private String nname; 
	private String nid; 
	private String ncode; 
	private String pid;
	
	public Netherlands() {}

	public Netherlands(String nname, String nid, String ncode, String pid) {
		super();
		this.nname = nname;
		this.nid = nid;
		this.ncode = ncode;
		this.pid = pid;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getNcode() {
		return ncode;
	}

	public void setNcode(String ncode) {
		this.ncode = ncode;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	
	
	
}
