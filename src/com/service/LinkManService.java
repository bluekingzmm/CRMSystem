package com.service;

import java.util.List;

import com.dao.LinkManInfoDao;
import com.pojo.LinkMan;

public class LinkManService {
//
//	private Session session;
//
//	public Session getSession() {
//		return session;
//	}
//
//	public void setSession(Session session) {
//		this.session = session;
//	}
	public LinkMan getById(long id)throws Exception{
		return new LinkManInfoDao().getById(id);
	}
	public void save(LinkMan l) throws Exception {
		// TODO Auto-generated method stub
		new LinkManInfoDao().save(l);
	}
	public void delete(LinkMan l) throws Exception {
		// TODO Auto-generated method stub
		new LinkManInfoDao().delete(l);
	}
	public void update(LinkMan l) throws Exception {
		// TODO Auto-generated method stub
		new LinkManInfoDao().update(l);
	}

//	public int count(String keyString) throws Exception {
//		Query query = null;
//
//		if (keyString.equals("")) {
//			query = session.createQuery("select count(u) from LinkMan");
//		} else {
//			query = session.createQuery("select count(u) from LinkMan as u where u.cust_name like : cust_name");
//			query.setString("cust_name", "%" + keyString + "%");
//		}
//		Object result = query.uniqueResult();
//		return Integer.parseInt(result.toString());
//	}

//	public List<LinkMan> cList(String keyString, int page, int pageSize) {
//		Query query = null;
//		System.out.println("service");
//
//		if (keyString.equals("")) {
//			query = session.createQuery("from LinkMan");
//			query.setFirstResult(page);
//			query.setMaxResults(pageSize);
//		} else {
//			query = session.createQuery("from LinkMan as u where u.cust_name like : cust_name");
//			query.setString("cust_name", "%" + keyString + "%");
//			query.setFirstResult(page);
//			query.setMaxResults(pageSize);
//
//		}
//		return query.list();
//	}

	public List<LinkMan> list() throws Exception {
		
		return new LinkManInfoDao().list() ;
	}
}
