package com.laptrinhjavaweb.controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.UsereModel;
@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		UsereModel userModel=  new UsereModel();
		userModel.setFullName("xin chao ae");
		request.setAttribute("model", userModel);
		request.getRequestDispatcher("/views/web/home.jsp").forward(request, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, resp);
	}
	
}
