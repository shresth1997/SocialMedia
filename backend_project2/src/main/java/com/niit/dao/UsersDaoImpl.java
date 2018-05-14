package com.niit.dao;

import java.util.List;

import org.apache.catalina.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Users;
@Repository
public  class UsersDaoImpl implements UsersDao {
	@Autowired
	private SessionFactory sessionFactory;
		public void registration(Users users) {
			Session session=sessionFactory.openSession();
			session.save(users);
			session.flush();
			session.close();
		}
		
	/*
	 * (non-Javadoc)
	 * @see com.niit.dao.UsersDao#getAllUsers()
	 * to check for duplicate username
	 * 
	 */
	
	
	public List<Users> getAllUsers() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Users");
		List<Users> users=query.list();
		session.close();
		return users;
	}
	public Users login(Users users) {
		System.out.println(users.getUsername()+ " " + users.getPassword());
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Users where username=? and password=? and enabled=?");
		query.setString(0,users.getUsername());
		query.setString(1,users.getPassword());
		query.setBoolean(2, true);
		Users validUsers=(Users) query.uniqueResult();
		session.close();
		return validUsers;
		
	}
	
	public Users updateUser(Users validUser) {
		Session session=sessionFactory.openSession();
		//update users_Batch19 set on_line=true where username='jone'
		session.update(validUser);
		session.flush();
		session.close();
		return validUser;
		
	}

	public Users getUserByUsername(String username) {
		Session session=sessionFactory.openSession();
		Users users=(Users)session.get (Users.class,username);
		session.close();
		return users;
	}

}