package com.neosoft.sportsclubmanagement.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.neosoft.sportsclubmanagement.model.User;

import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sf;
	
	public UserDaoImpl(SessionFactory sessionFactory) {
        this.sf = sessionFactory;
    }
	
	
	
	public UserDaoImpl() {
		super();
	}

	
	Session ses=null;
	Transaction t=null;
	
	@SuppressWarnings("unchecked")
	public User loginUser(User u) throws Exception {
		List <User> user=new ArrayList<User>();
		User us=new User();
		ses=sf.openSession();
		t=ses.beginTransaction();
		Query q=ses.createQuery("from User where userName=:u and password=:p");
		q.setParameter("u",u.getUserName());
		q.setParameter("p",u.getPassword());
		user=q.list();
		if(!user.isEmpty()) {
			us=user.get(0);
			return us;
		}
		t.commit();
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public int checkUsername(User u) throws Exception {
		int status;
		List <User> user=new ArrayList<User>();
		ses=sf.openSession();
		t=ses.beginTransaction();
		Query q=ses.createQuery("from User where userName=:u");
		q.setParameter("u",u.getUserName());	
		user=q.list();
		if(user.isEmpty())
		{
			status=0;
		}
		else
			status=1;
	
		t.commit();
		return status;
	}

	}

