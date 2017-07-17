package Servlet;

import accounts.AccountService;
import com.google.gson.Gson;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.MessegeDataSet;
import dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 17.7.17.
 */
public class Chat extends HttpServlet {
    final DBService dbService;
    final AccountService accountService;

    public Chat(DBService dbService, AccountService accountService) {
        this.dbService = dbService;
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDataSet user = accountService.getUserBySessionId(req.getSession().getId());
        try {
            if (user == null)
                getContentToUnauthorizedUser(req, resp);

            else
                getUserContent(user, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UsersDataSet user = dbService.getUser(req.getParameter("login"));
            if (user.getPassword().equals(req.getParameter("password"))) {
                accountService.addSession(req.getSession().getId(), user);
                getUserContent(user, req, resp);
            } else {
                getContentToUnauthorizedUser(req, resp);

            }

        } catch (DBException e) {
            e.printStackTrace();
        }

    }

    private void getContentToUnauthorizedUser(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    private void getUserContent(UsersDataSet user, HttpServletRequest req, HttpServletResponse resp) throws IOException, DBException {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(json);
        resp.getWriter().println("<p>Сообщения:</p>");

        for (Map.Entry<String, ArrayList<MessegeDataSet>> entry : dbService.getMesseges(user.getLogin()).entrySet()) {
            resp.getWriter().println("<p>" + entry.getKey() + ":</p>");
            for (MessegeDataSet messege : entry.getValue())
                resp.getWriter().println("<p>" + messege + "</p>");
        }

        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
