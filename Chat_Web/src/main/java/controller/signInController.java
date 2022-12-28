package controller;

import dal.UserDBcontext;
import dal.accountDBcontext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.account;

import java.io.IOException;

public class signInController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("./view/signIn/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        model.account account = new account();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        dal.accountDBcontext accountDBcontext = new accountDBcontext();
        account = accountDBcontext.loginCheck(email, password);
        if (account != null) {
            dal.UserDBcontext userDBcontext = new UserDBcontext();
            model.user user = userDBcontext.getUser(account.getUser().getUid());
            account.setUser(user);
            req.getSession().setAttribute("account", account);
            req.getSession().setAttribute("isSignIn", "true");
            resp.getWriter().print("home");
        } else {
            resp.getWriter().print("Can't find Email");

        }

    }
}
