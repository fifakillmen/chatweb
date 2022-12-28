package controller;

import dal.accountDBcontext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.account;

import java.io.IOException;


public class signUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("./view/signUp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email").trim();
        String password = req.getParameter("password").trim();
        String cfpassword = req.getParameter("cfpassword").trim();
        String mess = "";
        dal.accountDBcontext db = new accountDBcontext();
        boolean emailIsExist = db.accountIsExist(email);
        if (email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
            if (emailIsExist == false) {
                if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
                    if (cfpassword.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
                        if (password.equals(cfpassword)) {

                        } else {
                            mess += "\nConfirm password must be same Password!!";
                        }
                    } else {
                        mess += "\nConfirm password invalid!!";
                    }
                } else {
                    mess += "\nPassword invalid!!";
                }
            } else {
                mess = "Email already used!!";
            }
        } else {
            mess = "Email invalid!!";
        }
        if (mess.equals("")) {
            model.account account = new account();
            account.setEmail(email);
            account.setPassword(password);
            req.getSession().setAttribute("account", account);
            resp.getWriter().print("confirmEmail");
        } else {
            resp.getWriter().print(mess);
        }
    }
}
