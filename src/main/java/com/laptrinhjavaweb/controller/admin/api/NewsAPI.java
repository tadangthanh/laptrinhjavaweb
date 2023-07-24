package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-news" })
public class NewsAPI extends HttpServlet {
	private static final long serialVersionUID = 5644404405623586930L;

	@Inject
	private INewService newService;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewsModel model = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		model.setCreatedBy(((UserModel) SessionUtil.getSessionUtil().getValue(request, "USERMODEL")).getUserName());
		model = newService.save(model);
		// kiểu này ghi nhanh hơn, parameter1 là đầu ra, parameter 2 là đối tượng cần
		// gửi bằng json
		mapper.writeValue(resp.getWriter(), model);
		// cũng có thể là cách này
		// resp.getWriter().write(mapper.writeValueAsString(model));
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewsModel updateNews = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		updateNews
				.setModifiedBy(((UserModel) SessionUtil.getSessionUtil().getValue(request, "USERMODEL")).getUserName());
		updateNews = newService.update(updateNews);
		mapper.writeValue(resp.getWriter(), updateNews);
		System.out.println(updateNews.toString());
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewsModel newModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		newService.delete(newModel.getIds());
		mapper.writeValue(resp.getWriter(), "{}");
	}

}
