package com.example.demoa;

import com.example.demoa.Entity.User;
import com.example.demoa.Enums.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

import static com.example.demoa.Enums.Status.USER_DELETE_ATTEMPTED;

@SuppressWarnings("unchecked")
@WebServlet(name = "ServletDeleteUser", value = "/ServletDeleteUser")
public class ServletDeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getServletContext().getAttribute("users") instanceof Map) {
            Map<String, User> Users = (Map<String, User>) getServletContext().getAttribute("users");
            String user = request.getParameter("username");
            if (Users.containsKey(user.toLowerCase())) {
                Users.remove(user.toLowerCase());
                getServletContext().setAttribute("users", Users);
                request.setAttribute(Status.USER_DELETE_ATTEMPTED.toString(), "User deleted successfully");
                if (user.equals(((User) request.getSession().getAttribute("user")).getName())) {
                    request.getSession().invalidate();
                    response.sendRedirect("index.jsp");
                }
                else
                    request.getRequestDispatcher("WEB-INF/AdminHome.jsp").forward(request, response);
            }
            else {
                request.setAttribute(Status.USER_DELETE_ATTEMPTED.toString(), "User not deleted. User does not exist");
                request.getRequestDispatcher("WEB-INF/AdminHome.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
