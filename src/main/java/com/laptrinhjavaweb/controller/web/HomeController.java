package com.laptrinhjavaweb.controller.web;

import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ICategoryService categoryService;
    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
        	String message =request.getParameter("message");
        	String alert=request.getParameter("alert");
        	if(message!=null && alert!=null) {
        		ResourceBundle bundle = ResourceBundle.getBundle("message");
        		request.setAttribute("message", bundle.getString(message));
        		request.setAttribute("alert",alert);
        	}
        	
            request.getRequestDispatcher("/views/login.jsp").forward(request, resp);
        } else if (action != null && action.equals("logout")) {
            SessionUtil.getSessionUtil().removeValue(request, "USERMODEL");
            resp.sendRedirect(request.getContextPath() + "/trang-chu");
        } else {
            request.setAttribute("categorys", categoryService.findAll());
            request.getRequestDispatcher("/views/web/home.jsp").forward(request, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            UserModel model = FormUtil.toModel(UserModel.class, request);
            model = userService.finByUserNameAndPassWordAndStatus(model.getUserName(), model.getPassWord(),
                    1);
            if (model != null) {
                SessionUtil.getSessionUtil().putValue(request, "USERMODEL", model);
                if (model.getRole().getCode().equals("USER")) {
                    resp.sendRedirect(request.getContextPath() + "/trang-chu");
                } else if (model.getRole().getCode().equals("ADMIN")) {
                    resp.sendRedirect(request.getContextPath() + "/admin-home");
                }
            } else {
                resp.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
            }
        }
    }

}
