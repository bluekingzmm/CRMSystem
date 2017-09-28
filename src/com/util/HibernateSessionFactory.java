package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateSessionFactory {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory (){
		try {
			//读取hibernate.cfg.xml 配置文件
			Configuration cfg = new Configuration().configure();
			//返回通过配置文件build的sessionFactory
			return cfg.buildSessionFactory();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
			// TODO: handle exception
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void main(String[] args) throws Exception{
		Configuration cfg = new Configuration().configure();
		
		SchemaExport export = new SchemaExport(cfg);
		
		export.create(true, true);
	}

}
