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

import com.dao.CustomerInfoDao;
import com.pojo.Customer;
import com.service.CustomerService;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
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
		int page = 1, pageSize = 3, count = 0;
		String cust_name = request.getParameter("cust_name");
		String pn = request.getParameter("page");
		List<Customer> cstList = null;
//		System.out.println("page"+page);
//		System.out.println(cust_name + "custName");
		try {
			// if(cust_name!=null){
			count = new CustomerInfoDao().count(cust_name);
			// }else{
			// count = new CustomerService().count("");
			//
			// }
			int total = (int) Math.ceil((double) count / pageSize);
			if (pn != null && !pn.equals("")) {
				if (Integer.parseInt(pn) <= 0) {
					page = 1;
//					System.out.println("77777777777777777777777777777");
				} else if (Integer.parseInt(pn) > total) {
					page = total;
				} else {
					page = Integer.parseInt(pn);
				}
			}
			// if (cust_name != null) {
//			System.out.println("xiam" + count);

			cstList = new CustomerInfoDao().csList(cust_name, page, pageSize);
			//
			// } else {
			// cstList = new CustomerInfoDao().cList("", page, pageSize);

			// }
			// cstList=new CustomerInfoDao().list();
			System.out.println(count + "count");

//			System.out.println(cstList.toString() + "uuuuuuuuuuuuuuuuuu");
			request.setAttribute("page", page);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("list", cstList);
			request.setAttribute("totalPage", total);
			request.setAttribute("total", count);
			request.setAttribute("cust_name", cust_name);
			request.getRequestDispatcher("jsp/customer/list.jsp").forward(request, response);

			// }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void editsubmit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Map<String, String[]> map = request.getParameterMap();

		Customer c = new Customer();

		try {
			BeanUtils.populate(c, map);
			System.out.println(c.toString());
			new CustomerService().update(c);
			request.getRequestDispatcher("jsp/customer/list.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Customer c = null;
		Long id = Long.parseLong(request.getParameter("cust_id"));
		try {
			c = new CustomerService().getById(id);
			request.setAttribute("customer", c);
			request.getRequestDispatcher("jsp/customer/edit.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("jsp/customer/add.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addsubmit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		Map<String, String[]> map = request.getParameterMap();
		// Map<String, String> output = new HashMap<String, String>();
		// for (Entry<String, String[]> entry : map.entrySet()) {
		// output.put(entry.getKey(), entry.getValue()[0]);
		// }
		// System.out.println(output.toString());
		Customer c = new Customer();

		try {
			BeanUtils.populate(c, map);
			System.out.println(c.toString());
			new CustomerService().save(c);
			list(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
