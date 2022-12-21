package controller;

import helperCode.random.randomHelper;
import helperCode.email.emailHelper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class confirmEmailController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        model.account account = (model.account) req.getSession().getAttribute("account");
        if (account != null) {
            String content = "Hey " + account.getEmail() + " !\n" +
                    "\n" +
                    "A sign up attempt requires further verification.\n" +
                    "To complete the sign up, enter the verification code on the unrecognized device.\n" +
                    "\n" +
                    "Verification code: ";
            randomHelper randomCode = new randomHelper();
            String code = randomCode.getRandomCode();
            emailHelper emailHelper = new emailHelper();
            emailHelper.sendEmail(account.getEmail(), "Chat web Sign Up confirm!!", content + code);
            req.getSession().setAttribute("confirmEmailCode", code);
            req.getRequestDispatcher("./view/signUp/confirmEmail.jsp").forward(req, resp);
        } else {
            resp.getWriter().println("ngu");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        model.account account = (model.account) req.getSession().getAttribute("account");
        if (account != null) {
            String confirmEmailCode = (String) req.getSession().getAttribute("confirmEmailCode");
            String cfCodebyUser = req.getParameter("cfemailCode");
            if (cfCodebyUser.equals(confirmEmailCode)) {
                req.getRequestDispatcher("./view/createUser/creatUser.jsp").forward(req, resp);
            } else {
                resp.setContentType("text/html");
                PrintWriter pw = resp.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Wrong Code');");
                pw.println("</script>");
                RequestDispatcher rd = req.getRequestDispatcher("./view/signUp/confirmEmail.jsp");
                rd.include(req, resp);
            }
        } else {
            resp.getWriter().println("ngu");
        }

    }
}
