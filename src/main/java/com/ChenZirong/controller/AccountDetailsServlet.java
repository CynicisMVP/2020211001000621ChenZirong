package com.ChenZirong.controller;

import com.ChenZirong.dao.OrderDao;
import com.ChenZirong.dao.UserDao;
import com.ChenZirong.model.Order;
import com.ChenZirong.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AccountDetailsServlet", value = "/accountDetails")
public class AccountDetailsServlet extends HttpServlet {
    private Connection con=null;

    @Override
    public void init() throws ServletException {
        con=(Connection) getServletContext().getAttribute("connection");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        if(session!=null&&session.getAttribute("user")!=null){
            User user=(User) session.getAttribute("user");
            System.out.println(user.getId());
            int id=user.getId();
            System.out.println(id);
            UserDao dao=new UserDao();
            try {
                user=dao.findById(con,id);

                request.setAttribute("user",user);
                OrderDao orderDao=new OrderDao();
                List<Order> orderList=orderDao.findByUserId(con,id);
                System.out.println(orderList);
                request.setAttribute("orderList",orderList);
                request.getRequestDispatcher("WEB-INF/views/accountDetails.jsp").forward(request,response);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            response.sendRedirect("login");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
