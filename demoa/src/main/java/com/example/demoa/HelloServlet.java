package com.example.demoa;

import com.example.demoa.Entity.User;
import com.example.demoa.Enums.Permission;

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

        getServletContext().setAttribute("Users", Users);
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

            if (getServletContext().getAttribute("Users") instanceof HashMap) {
                Map<String, User> Users = (HashMap<String, User>) getServletContext().getAttribute("Users");
                if (Users.containsKey(name) && Users.get(name).getPassword().equals(password)) {
                    request.setAttribute("user", Users.get(name));
                    request.getSession().setAttribute("user", Users.get(name));
                    request.getSession().setMaxInactiveInterval(60 * 60 * 4);

                    request.getRequestDispatcher("WEB-INF/UserHome.jsp").forward(request, response);
                    destroy();
                } else {
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
}