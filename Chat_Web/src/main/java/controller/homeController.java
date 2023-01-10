package controller;

import dal.contactDBcontext;
import dal.conversationDBcontext;
import dal.friendDbcontext;
import dal.messageDBcontext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import model.message;
import model.conversation;

public class homeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dal.contactDBcontext contactDBcontext = new contactDBcontext();
        model.account account = (model.account) req.getSession().getAttribute("account");
        String isSignIn = (String) req.getSession().getAttribute("isSignIn");
        if (account != null && account.getUser() != null && isSignIn.equalsIgnoreCase("true")) {
            ArrayList<model.contact> contacts = contactDBcontext.gettextedContacts(account);

            req.setAttribute("contacts", contacts);
            req.getRequestDispatcher("./view/home/home.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("signIn");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
