package cn.xing.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xing.domain.Student;
import cn.xing.domain.User;
import cn.xing.service.impl.BusinessServiceImpl;

public class StudentLogin extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public StudentLogin() {
		super();
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
    	BusinessServiceImpl service = new BusinessServiceImpl();
		Student user=service.stulogin(username,password);
		if (user!=null) {
			System.out.println(user.getStudentid());
			request.getSession().setAttribute("student", user);
			
			response.sendRedirect(request.getContextPath()+"/student/index.jsp");
//			request.getRequestDispatcher("/WEB-INF/admin/index.jsp");
			return;
		}
		request.setAttribute("messag", "用户名或者密码错误");
		request.getRequestDispatcher("/messag.jsp").forward(request, response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
