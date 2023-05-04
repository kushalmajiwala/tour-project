package com.project.project_backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Carttb {

	@Id
	@GeneratedValue
	private int cartid;
	private int tourid;
	private String username;
	private String payment_status;
	
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
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
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	
	@Override
	public String toString() {
		return "Carttb [cartid=" + cartid + ", tourid=" + tourid + ", username=" + username + ", payment_status="
				+ payment_status + "]";
	}
}
