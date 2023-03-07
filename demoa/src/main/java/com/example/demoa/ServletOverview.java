package com.example.demoa;

import com.example.demoa.Entity.User;
import com.example.demoa.Enums.Permission;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletOverview", value = "/ServletOverview")
public class ServletOverview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") instanceof User user) {
            if (user.getPermission() == Permission.ADMIN) {
                request.getRequestDispatcher("WEB-INF/AdminHome.jsp").forward(request, response);
            }
            else {
                request.getRequestDispatcher("WEB-INF/UserHome.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
