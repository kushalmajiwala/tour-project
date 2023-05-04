package com.project.project_backend.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tour_placetb {

	@Id
	@GeneratedValue
	private int tourplaceid;
	private int tourmasterid;
	private String place_name;
	private String place_city;
	private String place_state;
	private String place_description;
	private Date start_date;
	private Date end_date;
	
	public int getTourplaceid() {
		return tourplaceid;
	}
	public void setTourplaceid(int tourplaceid) {
		this.tourplaceid = tourplaceid;
	}
	public int getTourmasterid() {
		return tourmasterid;
	}
	public void setTourmasterid(int tourmasterid) {
		this.tourmasterid = tourmasterid;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getPlace_city() {
		return place_city;
	}
	public void setPlace_city(String place_city) {
		this.place_city = place_city;
	}
	public String getPlace_state() {
		return place_state;
	}
	public void setPlace_state(String place_state) {
		this.place_state = place_state;
	}
	public String getPlace_description() {
		return place_description;
	}
	public void setPlace_description(String place_description) {
		this.place_description = place_description;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	@Override
	public String toString() {
		return "Tour_placetb [tourplaceid=" + tourplaceid + ", tourmasterid=" + tourmasterid + ", place_name="
				+ place_name + ", place_city=" + place_city + ", place_state=" + place_state + ", place_description="
				+ place_description + ", start_date=" + start_date + ", end_date=" + end_date + "]";
	}
}
