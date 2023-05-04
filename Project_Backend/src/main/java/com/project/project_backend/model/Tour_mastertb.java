package com.project.project_backend.model;

import java.sql.Date;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tour_mastertb {

	@Id
	@GeneratedValue
	private int tourmasterid;
	private String tour_title;
	private String tour_pic;
	private Date start_date;
	private Date end_date;
	private Time journey_begin_time;
	private int per_person_price;
	private String pickup_address;
	
	public int getTourmasterid() {
		return tourmasterid;
	}
	public void setTourmasterid(int tourmasterid) {
		this.tourmasterid = tourmasterid;
	}
	public String getTour_title() {
		return tour_title;
	}
	public void setTour_title(String tour_title) {
		this.tour_title = tour_title;
	}
	public String getTour_pic() {
		return tour_pic;
	}
	public void setTour_pic(String tour_pic) {
		this.tour_pic = tour_pic;
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
	public Time getJourney_begin_time() {
		return journey_begin_time;
	}
	public void setJourney_begin_time(Time journey_begin_time) {
		this.journey_begin_time = journey_begin_time;
	}
	public int getPer_person_price() {
		return per_person_price;
	}
	public void setPer_person_price(int per_person_price) {
		this.per_person_price = per_person_price;
	}
	public String getPickup_address() {
		return pickup_address;
	}
	public void setPickup_address(String pickup_address) {
		this.pickup_address = pickup_address;
	}
	@Override
	public String toString() {
		return "Tour_mastertb [tourmasterid=" + tourmasterid + ", tour_title=" + tour_title + ", tour_pic=" + tour_pic
				+ ", start_date=" + start_date + ", end_date=" + end_date + ", journey_begin_time=" + journey_begin_time
				+ ", per_person_price=" + per_person_price + ", pickup_address=" + pickup_address + "]";
	}

}
