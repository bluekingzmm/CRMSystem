package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pojo.LinkMan;
import com.util.HibernateSessionFactory;

public class LinkManInfoDao {

	public void save(LinkMan l) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			// 通过HibernateSessionFactory会话工厂类得到会话工厂，然后调用openSession得到session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// 开始数据库事务
			session.beginTransaction();
			// 把session交给usersDAO

			session.save(l);
			// 提交事务
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

	public void update(LinkMan l) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			// 通过HibernateSessionFactory会话工厂类得到会话工厂，然后调用openSession得到session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// 开始数据库事务
			session.beginTransaction();
			// 把session交给usersDAO

			session.update(l);
			// 提交事务
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

	public int count(String keyString) throws Exception {
		Session session = null;
		int result = 0;
		try {
			// 通过HibernateSessionFactory会话工厂类得到会话工厂，然后调用openSession得到session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// 开始数据库事务
			session.beginTransaction();
			// 把session交给usersDAO
			Query query = null;

			if (keyString == null || keyString.equals("")) {
				System.out.println("kkkk");
				query = session.createQuery("from LinkMan");

			} else {
				query = session.createQuery("from LinkMan as u where u.lkm_name like :lkm_name");
				query.setString("lkm_name", "%" + keyString + "%");

			}
			result = query.list().size();
			// 提交事务
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		return result;
	}
	public List<LinkMan> list() throws Exception {
		Session session = null;
		List<LinkMan> lList = null;
		try {
			// 通过HibernateSessionFactory会话工厂类得到会话工厂，然后调用openSession得到session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// 开始数据库事务
			session.beginTransaction();
			// 把session交给usersDAO
			lList=session.createQuery("from LinkMan").list();

			// lList=new LinkManService().cList(keyString,page,pageSize);
			// 提交事务
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return lList;
	}
	public List<LinkMan> lList(String keyString,int page,int pageSize) throws Exception {
		Session session = null;
		List<LinkMan> lList = null;
		try {
			// 通过HibernateSessionFactory会话工厂类得到会话工厂，然后调用openSession得到session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// 开始数据库事务
			session.beginTransaction();
			// 把session交给usersDAO
			Query query = null;

			if (keyString==null||keyString.equals("")) {
				query = session.createQuery("from LinkMan");
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
			} else {
				query = session.createQuery("from LinkMan as u where u.lkm_name like :lkm_name");
				query.setString("lkm_name", "%" + keyString + "%");
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
			}
			lList = query.list();

			// cstList=new CustomerService().cList(keyString,page,pageSize);
			// 提交事务
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return lList;
	}
	public LinkMan getById(long id) throws Exception {
		Session session = null;
		LinkMan l = null;
		try {
			// 通过HibernateSessionFactory会话工厂类得到会话工厂，然后调用openSession得到session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// 开始数据库事务
			session.beginTransaction();
			// 把session交给usersDAO

			// cstList=new CustomerService().cList(keyString,page,pageSize);
			// 提交事务
			l=(LinkMan) session.get(LinkMan.class, id);
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return l;
	}

	public void delete(LinkMan l) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			// 通过HibernateSessionFactory会话工厂类得到会话工厂，然后调用openSession得到session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// 开始数据库事务
			session.beginTransaction();
			// 把session交给usersDAO

			session.delete(l);
			// 提交事务
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}
}
