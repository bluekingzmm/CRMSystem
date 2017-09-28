package com.action;

import java.util.List;

import com.dao.CustomerInfoDao;
import com.dao.LinkManInfoDao;
import com.dao.UserInfoDao;
import com.pojo.Customer;
import com.pojo.LinkMan;
import com.pojo.User;

public class UserAction extends BaseAction {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String login() {
		User u = null;
		int page = 1;
		try {
			List<Customer> cList = null;
			List<LinkMan> lList = null;
			int pageSize = 3;
			System.out.println(user.getUser_name() + "0000000000000");
			u = new UserInfoDao().login(user.getUser_name(), user.getUser_password());
			if (u != null) {
				session.put("user", u);
				// cList=new CustomerInfoDao().csList(null, page, pageSize);
				cList = new CustomerInfoDao().list();
				lList = new LinkManInfoDao().list();
				request.setAttribute("cList", cList);
				request.setAttribute("lList", lList);
				return "index";
			} else {
				request.setAttribute("msg", "用户名或密码错误 ");
				return "input";
			}

		} catch (Exception e) {

		}
		return "error";
	}

	public String quit() throws Exception {
		try {
			if (session != null) {
				session.remove("user");// 删除session数据
				return "login";
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "error";
	}
}
