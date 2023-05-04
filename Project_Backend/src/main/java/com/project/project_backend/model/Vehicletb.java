package com.project.project_backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicletb {

	@Id
	@GeneratedValue
	private int vehicleid;
	private int tourmasterid;
	private String vehicle_name;
	
	public int getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
	}
	public int getTourmasterid() {
		return tourmasterid;
	}
	public void setTourmasterid(int tourmasterid) {
		this.tourmasterid = tourmasterid;
	}
	public String getVehicle_name() {
		return vehicle_name;
	}
	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}
	
	@Override
	public String toString() {
		return "Vehicletb [vehicleid=" + vehicleid + ", tourmasterid=" + tourmasterid + ", vehicle_name=" + vehicle_name
				+ "]";
	}
}
