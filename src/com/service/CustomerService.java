package com.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dao.CustomerInfoDao;
import com.pojo.Customer;

public class CustomerService {

	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void save(Customer c) throws Exception {
		// TODO Auto-generated method stub
		new CustomerInfoDao().save(c);
	}
	public void delete(Customer c) throws Exception {
		// TODO Auto-generated method stub
		new CustomerInfoDao().delete(c);
	}

	public void update(Customer c) throws Exception {
		// TODO Auto-generated method stub
		new CustomerInfoDao().update(c);
	}

	public int count(String keyString) throws Exception {
		
		return new CustomerInfoDao().count(keyString);
	}

	public List<Customer> cList(String keyString, int page, int pageSize) throws Exception {

		return new CustomerInfoDao().csList(keyString, page, pageSize);
	}

	public List<Customer> list() throws Exception {

		return new CustomerInfoDao().list();
	}

	public Customer getById(long id) throws Exception {
		return new CustomerInfoDao().getById(id);
	}
	
	public Customer getByName(String cust_name) throws Exception {
		return new CustomerInfoDao().getByName(cust_name);
	}
}
