package com.project.project_backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Historytb {

	@Id
	@GeneratedValue
	private int historyid;
	private int tourid;
	private String username;
	
	public int getHistoryid() {
		return historyid;
	}
	public void setHistoryid(int historyid) {
		this.historyid = historyid;
	}
	public int getTourid() {
		return tourid;
	}
	public void setTourid(int tourid) {
		this.tourid = tourid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "Historytb [historyid=" + historyid + ", tourid=" + tourid + ", username=" + username + "]";
	}
}
