package com.project.project_backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Gallerytb {

	@Id
	private int galleryid;
	private String tour_name;
	private String place_name;
	private String image_url;
	
	public int getGalleryid() {
		return galleryid;
	}
	public void setGalleryid(int galleryid) {
		this.galleryid = galleryid;
	}
	public String getTour_name() {
		return tour_name;
	}
	public void setTour_name(String tour_name) {
		this.tour_name = tour_name;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	@Override
	public String toString() {
		return "Gallerytb [galleryid=" + galleryid + ", tour_name=" + tour_name + ", place_name=" + place_name
				+ ", image_url=" + image_url + "]";
	}
}
