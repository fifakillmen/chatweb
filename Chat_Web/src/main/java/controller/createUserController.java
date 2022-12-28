package controller;

import dal.UserDBcontext;
import dal.accountDBcontext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

import helperCode.datetimeHellper;
import model.user;

public class createUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        model.account ac = (model.account) req.getSession().getAttribute("account");
        if (ac != null) {
            req.getRequestDispatcher("./view/createUser/creatUser.jsp").forward(req, resp);
        } else {
            // chuyen ve trang signUp
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        model.account ac = (model.account) req.getSession().getAttribute("account");

        if (ac != null) {
            model.user u = new model.user();
            String fullname = req.getParameter("fullname");
            String displayname = req.getParameter("displayname");
            String dob = req.getParameter("dob");
            String address = req.getParameter("address");
            String phone = req.getParameter("phone");
            String gender = req.getParameter("gender");
            datetimeHellper hellper = new datetimeHellper();
            Date dob_sqlDate = hellper.convertString2SQLDate(dob);
            //==========================================================
            dal.UserDBcontext userDBcontext = new UserDBcontext();
            model.user user = new user();
            user.setUname(fullname);
            user.setUdisplayname(displayname);
            user.setDob(dob_sqlDate);
            user.setAddress(address);
            user.setPhone(phone);
            if (gender.equalsIgnoreCase("male")) {
                user.setGender(true);
            } else {
                user.setGender(false);
            }
            boolean userIsExist = userDBcontext.userIsExist(user);
            if (userIsExist == true) {
                resp.getWriter().print("User is exist!!");
            } else {
                // insert user and get this user id
                user.setUid(userDBcontext.insertUser(user));
                // insert account of this user
                dal.accountDBcontext accountDBcontext = new accountDBcontext();
                int insert = accountDBcontext.insertAccount(ac, user);
                if (insert == 1) {
                    // return redirect signIn
                    req.getSession().setAttribute("account", null);
                    resp.getWriter().print("signIn");
                } else {
                    // return redirect error404
                    resp.getWriter().print("error404");
                }
            }


            //==========================================================

        } else {
            // return redirect signIn
            resp.getWriter().print("signIn");
        }
    }
}
