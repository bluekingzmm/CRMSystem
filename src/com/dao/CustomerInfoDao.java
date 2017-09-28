package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pojo.Customer;
import com.service.CustomerService;
import com.util.HibernateSessionFactory;

import sun.awt.dnd.SunDragSourceContextPeer;

public class CustomerInfoDao {

	public void save(Customer c) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			// ͨ��HibernateSessionFactory�Ự������õ��Ự������Ȼ�����openSession�õ�session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// ��ʼ���ݿ�����
			session.beginTransaction();
			// ��session����usersDAO

			session.save(c);
			// �ύ����
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

	public void update(Customer c) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			// ͨ��HibernateSessionFactory�Ự������õ��Ự������Ȼ�����openSession�õ�session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// ��ʼ���ݿ�����
			session.beginTransaction();
			// ��session����usersDAO

			session.update(c);
			// �ύ����
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

	

	public void delete(Customer c) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			// ͨ��HibernateSessionFactory�Ự������õ��Ự������Ȼ�����openSession�õ�session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// ��ʼ���ݿ�����
			session.beginTransaction();
			// ��session����usersDAO

			session.delete(c);
			// �ύ����
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
			// ͨ��HibernateSessionFactory�Ự������õ��Ự������Ȼ�����openSession�õ�session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// ��ʼ���ݿ�����
			session.beginTransaction();
			// ��session����usersDAO
			Query query = null;
			System.out.println("serviceCount");

			if (keyString == null || keyString.equals("")) {
				System.out.println("kkkk");
				query = session.createQuery("from Customer");

			} else {
				query = session.createQuery("from Customer as u where u.cust_name like :cust_name");
				query.setString("cust_name", "%" + keyString + "%");

			}
			result = query.list().size();
			System.out.println("countDao" + result);
			// �ύ����
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

	public List<Customer> csList(String keyString,int page,int pageSize) throws Exception {
		Session session = null;
		List<Customer> cstList = null;
		try {
			// ͨ��HibernateSessionFactory�Ự������õ��Ự������Ȼ�����openSession�õ�session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// ��ʼ���ݿ�����
			session.beginTransaction();
			// ��session����usersDAO
			Query query = null;
			System.out.println("service");

			if (keyString==null||keyString.equals("")) {
				query = session.createQuery("from Customer");
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
			} else {
				query = session.createQuery("from Customer as u where u.cust_name like :cust_name");
				query.setString("cust_name", "%" + keyString + "%");
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
			}
			cstList = query.list();

			// cstList=new CustomerService().cList(keyString,page,pageSize);
			// �ύ����
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return cstList;
	}

	public List<Customer> list() throws Exception {
		Session session = null;
		List<Customer> cstList = null;
		try {
			// ͨ��HibernateSessionFactory�Ự������õ��Ự������Ȼ�����openSession�õ�session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// ��ʼ���ݿ�����
			session.beginTransaction();
			// ��session����usersDAO
			cstList = session.createQuery("from Customer").list();

			// cstList=new CustomerService().cList(keyString,page,pageSize);
			// �ύ����
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return cstList;
	}

	public Customer getById(long id) throws Exception {
		Session session = null;
		Customer c = null;
		try {
			// ͨ��HibernateSessionFactory�Ự������õ��Ự������Ȼ�����openSession�õ�session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// ��ʼ���ݿ�����
			session.beginTransaction();
			// ��session����usersDAO

			// cstList=new CustomerService().cList(keyString,page,pageSize);
			// �ύ����
			c = (Customer) session.get(Customer.class, id);
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return c;
	}

	public Customer getByName(String cust_name) {
		// TODO Auto-generated method stub
		Session session = null;
		Customer c = null;
		Query query=null;
		try {
			// ͨ��HibernateSessionFactory�Ự������õ��Ự������Ȼ�����openSession�õ�session
			session = HibernateSessionFactory.getSessionFactory().openSession();
			// ��ʼ���ݿ�����
			session.beginTransaction();
			// ��session����usersDAO

			// cstList=new CustomerService().cList(keyString,page,pageSize);
			// �ύ����
			query = session.createQuery("from Customer where cust_name=?");
			query.setString(0, cust_name.trim());
//			c = (Customer) session.get(Customer.class, cust_name);
			c=(Customer) query.uniqueResult();
			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return c;
	}
}
