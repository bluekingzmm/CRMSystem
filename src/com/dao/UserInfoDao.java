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
			// ͨ��HibernateSessionFactory�Ự������õ��Ự������Ȼ�����openSession�õ�session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// ��ʼ���ݿ�����
			session.beginTransaction();
			// ��session����usersDAO

			Query query = session.createQuery("from User where user_name=? and user_password=?");
			query.setString(0, userName.trim());
			query.setString(1, userPassword.trim());
			u = (User) query.uniqueResult();
			// �ύ����
		
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
