package com.project.project_backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tourtb {

	@Id
	@GeneratedValue
	private int tourid;
	private int tourmasterid;
	private String username;
	private String payment_method;
	private String payment_status;
	
	public int getTourid() {
		return tourid;
	}
	public void setTourid(int tourid) {
		this.tourid = tourid;
	}
	public int getTourmasterid() {
		return tourmasterid;
	}
	public void setTourmasterid(int tourmasterid) {
		this.tourmasterid = tourmasterid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	@Override
	public String toString() {
		return "Tourtb [tourid=" + tourid + ", tourmasterid=" + tourmasterid + ", username=" + username
				+ ", payment_method=" + payment_method + ", payment_status=" + payment_status + "]";
	}
}
