package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.BlogPostDao;
import com.niit.model.BlogComment;
import com.niit.model.BlogPost;

@Repository
public class BlogPostDaoImpl implements BlogPostDao
{
	@Autowired
	private SessionFactory sessionFactory;
	public void saveBlogPost(BlogPost blogPost) {
		Session session=sessionFactory.openSession();
		session.save(blogPost);
		session.flush();
		session.close();
	}

	public List<BlogPost> getAllBlogs(int approved) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from BlogPost where approved=" + approved);
		List<BlogPost>blogPosts=query.list();
		session.close();
		return blogPosts;
		
	}

	public BlogPost getBlogById(int id) {
		Session session=sessionFactory.openSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
		session.close();
		return blogPost;
	}

	public void updateBlogPost(BlogPost blogPost) {
		Session session=sessionFactory.openSession();
		session.update(blogPost);
		session.flush();
		session.close();
		
	}

	public void addComment(BlogComment blogComment) {
	 Session session=sessionFactory.openSession();
	 session.save(blogComment);
	 session.flush();
	 session.close();
		
	}

	public List<BlogComment> getBlogComments(int blogId) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from BlogComment where blogPost.id=" + blogId);
		List<BlogComment>blogComments=query.list();
		System.out.println(blogComments);
		session.close();
		return blogComments;
	}
}