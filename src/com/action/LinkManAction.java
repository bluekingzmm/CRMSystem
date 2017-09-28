package com.action;

import java.util.List;

import com.dao.CustomerInfoDao;
import com.dao.LinkManInfoDao;
import com.pojo.Customer;
import com.pojo.LinkMan;
import com.service.LinkManService;

public class LinkManAction extends BaseAction {

	private LinkMan linkman;
	private String cust_name;

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

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

	public LinkMan getLinkMan() {
		return linkman;
	}

	public void setLinkMan(LinkMan linkman) {
		this.linkman = linkman;
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
	public String list() {
		System.out.println(keyString + "keyString");
		int pageSize = 3, count = 0;
		List<LinkMan> lList = null;
		try {
			count = new LinkManInfoDao().count(keyString);
			int total = (int) Math.ceil((double) count / pageSize);
			// lList=new LinkManInfoDao().list();
			if (page <= 0) {
				page = 1;
			} else if (page > total) {
				page = total;
			}
			lList = new LinkManInfoDao().lList(keyString, page, pageSize);
			request.setAttribute("page", page);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("list", lList);
			request.setAttribute("totalPage", total);
			request.setAttribute("total", count);
			request.setAttribute("cust_name", keyString);
			return "list";

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "error";

	}

	public String add() {
		Customer c = null;
		try {
			c = new CustomerInfoDao().getByName(cust_name);
			if (c != null) {
				c.setCust_id(c.getCust_id());
				linkman.setCustomer(c);
				// System.out.println(c.toString()+"ll");
				new LinkManInfoDao().save(linkman);
				return "success";
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ERROR;
	}

	public String update() {

		try {

			linkman = new LinkManService().getById(linkman.getLkm_id());
			System.out.println(linkman.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("linkman", linkman);
		return "update";
	}

	public String edit() {
		Customer c = null;
		try {
			c = new CustomerInfoDao().getByName(cust_name);
			if (c != null) {
				c.setCust_id(c.getCust_id());
				linkman.setCustomer(c);
				new LinkManService().update(linkman);
				return "success";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

	public String delete() {
		try {

			new LinkManService().delete(linkman);
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
}
