package com.niit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.CoverPicture;
@Repository
public class CoverPictureDaoImpl implements CoverPictureDao {
	@Autowired
	private SessionFactory sessionFactory;
	public void saveCoverPicture(CoverPicture coverPicture) {
		Session session=sessionFactory.openSession();
		session.saveOrUpdate(coverPicture);
		session.flush();
		session.close();
		
		
	}
	 
	public CoverPicture getCoverPic(String username) {
		Session session=sessionFactory.openSession();
		//select * from coverPicture where username='admin'
		CoverPicture coverPicture=(CoverPicture)session.get(CoverPicture.class, username);
		session.close();
		return coverPicture;

   

	}


}
