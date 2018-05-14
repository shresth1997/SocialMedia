package com.niit.controllers;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogPostDao;
import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Error;
import com.niit.model.Users;
@RestController
public class BlogController {
@Autowired
private BlogPostDao blogPostDao;
@RequestMapping(value="/saveblogpost",method=RequestMethod.POST)
public ResponseEntity<?> saveBlogPost (@RequestBody BlogPost blogPost,HttpSession session){
Users users=(Users) session.getAttribute("user");
if(users==null){
	Error error = new Error(3,"UnAuthorized user");
	return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
}
try{
	blogPost.setPostedOn(new Date());
	blogPost.setCreatedBy(users);
	blogPostDao.saveBlogPost(blogPost);
	return new ResponseEntity <Void>(HttpStatus.OK);
	
}
catch(Exception e){
	Error error = new Error (2,"Cannot insert blog post details");
	return new ResponseEntity <Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
}
}
/**
*@param approved
*@param session
*@return
*approved=0,list of blogs waiting for approval 
*approval=1,List of blogs which are approved
*http://localhost:9080/backend_project2/listpfblogs/0
*http://localhost:9080/backend_project2/listpfblogs/1
*/
@RequestMapping (value="/listofblogs/{approved}",method=RequestMethod.GET)
public ResponseEntity<?> getAllBlogs(@PathVariable int approved,HttpSession session)
{
	Users users=(Users) session.getAttribute("user");
	if(users==null){
		Error error = new Error(3,"UnAuthorized user");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
List<BlogPost> blogPosts=blogPostDao.getAllBlogs(approved);
System.out.println(blogPosts.size());
return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
}


@RequestMapping (value="/getblogpost/{id}",method=RequestMethod.GET)
public ResponseEntity<?> getBlogPost(@PathVariable int id,HttpSession session){
	Users users=(Users)session.getAttribute("user");
	if(users==null){
		Error error = new Error (3,"UnAuthorized user");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
BlogPost blogPost=blogPostDao.getBlogById(id);
return new ResponseEntity <BlogPost> (blogPost,HttpStatus.OK);


}
@RequestMapping(value="/updateblogpost",method=RequestMethod.PUT)
public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
	Users users=(Users)session.getAttribute("user");
	if(users==null){
		Error error = new Error (3,"UnAuthorized user" );
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
blogPostDao.updateBlogPost(blogPost);
return new ResponseEntity<Void>(HttpStatus.OK);
}
@RequestMapping(value="/addblogcomment",method=RequestMethod.POST)
public ResponseEntity<?> addBlogComment (@RequestBody BlogComment blogComment,HttpSession session){
 Users users=(Users) session.getAttribute("user");	
 if(users==null){
	  Error error = new Error (3,"UnAuthorized user");
	  return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
 }
 try{
	 blogComment.setCommentedBy(users);
	 blogComment.setCommentedOn(new Date());
	 blogPostDao.addComment(blogComment);
	 return new ResponseEntity<Void>(HttpStatus.OK);
	  }
 catch(Exception e){
	 Error error = new Error (4,"Unable to add comment"+ e.getMessage());
	 return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
 }
}
@RequestMapping(value="/getblogcomments/{blogId}",method=RequestMethod.GET)
public ResponseEntity<?> getBlogComments(@PathVariable int blogId,HttpSession session){
	System.out.println("ENTERING GETBLOGCOMMENTS");
	Users users=(Users)session.getAttribute("user");
	if(users==null){
		Error error = new Error (3,"UnAuthorized user" );
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
try{
	List<BlogComment> blogComments=blogPostDao.getBlogComments(blogId);
	System.out.println(blogComments.size());
	return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	
}
catch (Exception e)
{
System.out.println(e.getMessage());
return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
}
}
}


