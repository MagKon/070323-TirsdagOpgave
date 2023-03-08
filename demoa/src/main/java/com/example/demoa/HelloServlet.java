package com.example.demoa;

import com.example.demoa.Entity.User;
import com.example.demoa.Enums.Permission;
import com.example.demoa.Enums.Status;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static com.example.demoa.Enums.Status.NOT_FOUND;

@SuppressWarnings("unchecked")
@WebServlet(name = "helloServlet", value = "/Home")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        Map<String, User> Users = new HashMap<>();

        Users.put("Magnus".toLowerCase(), new User("Magnus", "Password", Permission.ADMIN));
        for (int i = 0; i < 6; i++) {
            Users.put(("User" + i).toLowerCase(), new User("User" + i, "Password"));
        }

        getServletContext().setAttribute("users", Users);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            if (request.getSession().getAttribute("user") instanceof User) {
                request.setAttribute("user", request.getSession().getAttribute("user"));
                request.getRequestDispatcher("WEB-INF/UserHome.jsp").forward(request, response);
                destroy();
            }

            String name = request.getParameter("name").toLowerCase();
            String password = request.getParameter("password");

            if (getServletContext().getAttribute("users") instanceof HashMap) {
                Map<String, User> Users = (HashMap<String, User>) getServletContext().getAttribute("users");
                if (Users.containsKey(name) && Users.get(name).getPassword().equals(password)) {
                    request.setAttribute("user", Users.get(name));
                    request.getSession().setAttribute("user", Users.get(name));
                    request.getSession().setMaxInactiveInterval(60 * 60 * 4);

                    request.getRequestDispatcher("WEB-INF/UserHome.jsp").forward(request, response);
                    destroy();
                } else {
                    Map<String, Object> attributes = new HashMap<>();
                    request.setAttribute(NOT_FOUND.toString(), "User not found");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
            else {
                response.sendError(500, "Internal Server Error");
            }
        } catch (Exception e) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        addUser(request, response);
    }

    public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getServletContext().getAttribute("users") instanceof Map) {
            Map<String, User> Users = (Map<String, User>) getServletContext().getAttribute("users");
            String username = request.getParameter("newname");
            String password = request.getParameter("newpassword");
            String confirmPassword = request.getParameter("confirmpassword");
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
                    if (password.equals(confirmPassword))
                        request.setAttribute(Status.USER_ADD_ATTEMPTED.toString(), "Confirm password must be the same as password.");
                    else
                        request.setAttribute(Status.USER_ADD_ATTEMPTED.toString(), "Password is invalid. Password must be more than 5 characters long and cannot contain spaces.");
                }
            }
            else {
                request.setAttribute(Status.USER_ADD_ATTEMPTED.toString(), "User not added. User already exists or username is invalid.");
            }
            //Return to page
            String url = request.getHeader("referer");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}