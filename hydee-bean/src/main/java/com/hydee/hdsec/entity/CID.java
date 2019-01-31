package com.hydee.hdsec.entity;

import java.io.Serializable;

public class CID implements Serializable{
	private static final long serialVersionUID = 1L;

	public CID() {
	}

	public CID(String cid) {
		this.cid = cid;
	}

	private String cid;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
