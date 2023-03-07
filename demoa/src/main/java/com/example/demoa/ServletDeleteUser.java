package com.example.demoa;

import com.example.demoa.Entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;
@SuppressWarnings("unchecked")
@WebServlet(name = "ServletDeleteUser", value = "/ServletDeleteUser")
public class ServletDeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getServletContext().getAttribute("users") instanceof Map) {
            Map<String, User> Users = (Map<String, User>) getServletContext().getAttribute("users");
            String user = request.getParameter("username");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
