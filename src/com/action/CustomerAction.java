package com.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.dao.CustomerInfoDao;
import com.opensymphony.xwork2.ActionContext;
import com.pojo.Customer;
import com.service.CustomerService;

public class CustomerAction extends BaseAction {

	private Customer customer;

	private String keyString;

	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	// public String add(){
	// ActionContext context=ActionContext.getContext();
	// Map<String, Object> map=context.getParameters();
	// Set<String> set=map.keySet();
	// for (String s : set) {
	//
	// }
	// return null;
	// }
	public String add() {
		try {
			
			new CustomerInfoDao().save(customer);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
		}

		return ERROR;
	}

	public String update() {
		try {
			customer = new CustomerService().getById(customer.getCust_id());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("customer", customer);
		return "update";
	}

	public String edit() {
		try {

			new CustomerService().update(customer);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
	public String delete() {
		try {

			new CustomerService().delete(customer);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

	public String create() throws Exception {
		return "add";
	}

	public String list() {
		System.out.println("jjjj");
		int pageSize = 3, count = 0;
		List<Customer> cList = null;
		try {
			count = new CustomerInfoDao().count(keyString);
			int total = (int) Math.ceil((double) count / pageSize);
			if (page <= 0) {
				page = 1;
			} else if (page > total) {
				page = total;
			} 
			cList = new CustomerInfoDao().csList(keyString, page, pageSize);
			// cList = new CustomerInfoDao().list();

			
			System.out.println(page+"page");
			request.setAttribute("page", page);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("list", cList);
			request.setAttribute("totalPage", total);
			request.setAttribute("total", count);
			request.setAttribute("cust_name", keyString);
			return "list";

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "error";

	}
}
