package com.example.demoa;

import com.example.demoa.Entity.User;
import com.example.demoa.Enums.Permission;
import com.example.demoa.Enums.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@SuppressWarnings("unchecked")
@WebServlet(name = "ServletAddUser", value = "/ServletAddUser")
public class ServletAddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getServletContext().getAttribute("users") instanceof Map) {
            Map<String, User> Users = (Map<String, User>) getServletContext().getAttribute("users");
            String username = request.getParameter("name");
            String password = request.getParameter("password");
            Permission permission = null;

            if (request.getParameter("permission") != null)
                permission = Permission.valueOf(request.getParameter("permission"));

            if (!Users.containsKey(username.toLowerCase()) && !username.equalsIgnoreCase("")) {
                if (!password.contains(" ") && password.length() > 5) {
                    //Add user to map
                    Users.put(username.toLowerCase(), new User(username, password, permission));
                    getServletContext().setAttribute("users", Users);

                    request.setAttribute(Status.USER_ADD_ATTEMPTED.toString(), "User added successfully");
                }
                else {
                    request.setAttribute(Status.USER_ADD_ATTEMPTED.toString(), "Password is invalid. Password must be more than 5 characters long and cannot contain spaces.");
                }
            }
            else {
                request.setAttribute(Status.USER_ADD_ATTEMPTED.toString(), "User not added. User already exists or username is invalid.");
            }
            request.getRequestDispatcher("WEB-INF/AdminHome.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
