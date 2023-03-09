package com.example.demoa;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demoa.Entity.User;
import com.example.demoa.Enums.Status;

@WebServlet(name = "ServletAddItemToUser", value = "/ServletAddItemToUser")
public class ServletAddItemToUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") instanceof User user) {
            String newItem = request.getParameter("newItem");
            List<String> todoList = user.getTodoList();
            List<String> itemsToRemove = new ArrayList<>();
            for (String item : todoList) {
                if (request.getParameter(String.valueOf(todoList.indexOf(item))) == null || request.getParameter(String.valueOf(todoList.indexOf(item))).replace(" ", "").equals("")) {
                    itemsToRemove.add(item);
                }
                else if (!item.equals(request.getParameter(String.valueOf(todoList.indexOf(item))))) {
                    todoList.set(todoList.indexOf(item), request.getParameter(String.valueOf(todoList.indexOf(item))));
                }
            }
            if (newItem != null && !newItem.replace(" ", "").equals(""))
                user.addToTodoList(newItem);
            for (String item : itemsToRemove) {
                user.removeFromTodoList(item);
            }
            user.setTodoList(todoList);
            request.setAttribute("user", user);
            request.setAttribute(Status.USER_ITEM_ADD_ATTEMPTED.toString(), "Item added successfully and list updated");
            request.getRequestDispatcher("WEB-INF/UserHome.jsp").forward(request, response);
        }
        else {
            request.setAttribute(Status.USER_ITEM_ADD_ATTEMPTED.toString(), "Item not added and list not updated. User not found");
            request.getRequestDispatcher("WEB-INF/UserHome.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
