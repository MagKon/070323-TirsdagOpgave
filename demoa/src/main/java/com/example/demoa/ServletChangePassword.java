package com.example.demoa;

import com.example.demoa.Entity.User;
import com.example.demoa.Enums.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;
@SuppressWarnings("unchecked")
@WebServlet(name = "ServletChangePassword", value = "/ServletChangePassword")
public class ServletChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getServletContext().getAttribute("users") instanceof Map) {
            Map<String, User> Users = (Map<String, User>) getServletContext().getAttribute("users");
            User user = (User) request.getSession().getAttribute("user");
            if (!request.getParameter("newPassword").equalsIgnoreCase("") && !request.getParameter("newPassword").contains(" ") && request.getParameter("newPassword").length() > 5) {
                user.setPassword(request.getParameter("newPassword"));
                Users.put(user.getName().toLowerCase(), user);
                request.getSession().setAttribute("user", user);
                request.setAttribute(Status.PASSWORD_ATTEMPTED_CHANGE.toString(), "Password changed");
                request.getRequestDispatcher("WEB-INF/UserHome.jsp").forward(request, response);
            }
            else {
                request.setAttribute(Status.PASSWORD_ATTEMPTED_CHANGE.toString(), "Password not changed. Password cannot be empty or contain spaces and must be longer than 5 characters");
                request.getRequestDispatcher("WEB-INF/UserHome.jsp").forward(request, response);
            }
        }
        else {
            request.setAttribute(Status.INTERNAL_SERVER_ERROR.toString(), "Internal Server Error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
