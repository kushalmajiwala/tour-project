package com.project.project_backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Projectgroups {
	
	private String groupname;
	@Id
	private String username;
	
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Projectgroups [groupname=" + groupname + ", username=" + username + "]";
	}
}
