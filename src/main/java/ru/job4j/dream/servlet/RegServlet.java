package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (!name.isEmpty() & !email.isEmpty() & !password.isEmpty()) {
            HttpSession sc = req.getSession();
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            sc.setAttribute("user", user);
            PsqlStore.instOf().createUser(user);
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            req.setAttribute("error", "Одно или несколько полей пустые");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
    }
}
