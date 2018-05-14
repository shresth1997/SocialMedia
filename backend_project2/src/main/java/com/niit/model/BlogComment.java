package com.niit.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
@Entity
@Table(name="blogcomment_batch19")
public class BlogComment {
 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 private int id;
 @ManyToOne
 @JoinColumn(name="username")
 private Users commentedBy;
 private Date commentedOn;
 @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
 @JoinColumn(name="blogpost_id")
 private BlogPost blogPost;
 private String body;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Users getCommentedBy() {
	return commentedBy;
}
public void setCommentedBy(Users commentedBy) {
	this.commentedBy = commentedBy;
}
public Date getCommentedOn() {
	return commentedOn;
}
public void setCommentedOn(Date commentedOn) {
	this.commentedOn = commentedOn;
}
public BlogPost getBlogPost() {
	return blogPost;
}
public void setBlogPost(BlogPost blogPost) {
	this.blogPost = blogPost;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
}
