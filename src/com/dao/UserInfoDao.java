package com.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pojo.User;
import com.util.HibernateSessionFactory;

public class UserInfoDao {

	
	public User login(String userName,String userPassword) throws Exception {
		Session session = null;
		User u = null;
		try {
			// 通过HibernateSessionFactory会话工厂类得到会话工厂，然后调用openSession得到session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// 开始数据库事务
			session.beginTransaction();
			// 把session交给usersDAO

			Query query = session.createQuery("from User where user_name=? and user_password=?");
			query.setString(0, userName.trim());
			query.setString(1, userPassword.trim());
			u = (User) query.uniqueResult();
			// 提交事务
		
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return u;
	}
}
