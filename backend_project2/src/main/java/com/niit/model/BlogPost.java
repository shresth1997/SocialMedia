package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="blogpost_batch19")
public class BlogPost {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	@Lob
		private String description;
	@ManyToOne
	@JoinColumn(name="username")
	private Users createdBy;
	private Date postedOn;
	private boolean approved;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Users getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Users users) {
		this.createdBy = users;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date date) {
		this.postedOn = date;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
}