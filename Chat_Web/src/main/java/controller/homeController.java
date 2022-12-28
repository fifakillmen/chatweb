package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class homeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        model.account account = (model.account) req.getSession().getAttribute("account");
        String isSignIn = (String) req.getSession().getAttribute("isSignIn");
        if (account != null && account.getUser() != null && isSignIn.equalsIgnoreCase("true")) {
            req.getRequestDispatcher("./view/home/home.jsp").forward(req, resp);
        } else {
            resp.getWriter().print("signIn");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
