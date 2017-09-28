package com.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.dao.LinkManInfoDao;
import com.pojo.Customer;
import com.pojo.LinkMan;
import com.service.CustomerService;
import com.service.LinkManService;

/**
 * Servlet implementation class LinkManServlet
 */
@WebServlet("/LinkManServlet")
public class LinkManServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LinkManServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if (method.equals("add")) {
			add(request, response);
		} else if (method.equals("addsubmit")) {
			addsubmit(request, response);
		} else if (method.equals("edit")) {
			edit(request, response);
		} else if (method.equals("editsubmit")) {
			editsubmit(request, response);
		} else if (method.equals("list")) {
			list(request, response);
		}

	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int page = 1, pageSize = 6, count = 0;
		// String cust_name = request.getParameter("cust_name");
		// String pn = request.getParameter("page");
		List<LinkMan> lList = null;
		System.out.println("pppp");
		// System.out.println(cust_name + "custName");
		try {
			// if(cust_name!=null){
			// count = new CustomerService().count(cust_name);
			// }else{
			// count = new CustomerService().count("");
			//
			// }
			// int total = (int) Math.ceil((double) count / pageSize);
			// if (pn != null && !pn.equals("")) {
			// if (Integer.parseInt(pn) <= 0) {
			// page = 1;
			// } else if (Integer.parseInt(pn) > total) {
			// page = total;
			// } else {
			// page = Integer.parseInt(pn);
			// }
			// if (cust_name != null) {
			// cstList = new CustomerInfoDao().cList(cust_name, page, pageSize);
			//
			// } else {
			// cstList = new CustomerInfoDao().cList("", page, pageSize);

			// }
			lList = new LinkManInfoDao().list();
			// System.out.println(count + "count");
			System.out.println(lList.toString() + "uuuuuuuuuuuuuuuuuu");
			// request.setAttribute("page", page);
			// request.setAttribute("pageSize", pageSize);
			request.setAttribute("list", lList);
			// request.setAttribute("count", count);
			// request.setAttribute("total", total);
			// request.setAttribute("cust_name", cust_name);
			request.getRequestDispatcher("jsp/linkman/list.jsp").forward(request, response);

			// }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void editsubmit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Map<String, String[]> map = request.getParameterMap();

		LinkMan l = new LinkMan();

		try {
			BeanUtils.populate(l, map);
			System.out.println(l.toString());
			new LinkManService().update(l);
			request.getRequestDispatcher("jsp/linkman/list.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		LinkMan l = null;
		Long id = Long.parseLong(request.getParameter("lkm_id"));
		try {
			l = new LinkManService().getById(id);
			request.setAttribute("linkman", l);
			request.getRequestDispatcher("jsp/linkman/edit.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("jsp/linkman/add.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addsubmit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Map<String, String[]> map = request.getParameterMap();
		LinkMan l = new LinkMan();
		long cust_id = Long.parseLong(request.getParameter("cust_id"));
		Customer c = null;
		try {
			c = new Customer();
			c.setCust_id(cust_id);
			l.setCustomer(c);
			BeanUtils.populate(l, map);
			System.out.println(l.toString());
			new LinkManService().save(l);
			list(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
