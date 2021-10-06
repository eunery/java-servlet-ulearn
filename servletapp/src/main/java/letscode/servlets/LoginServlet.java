package letscode.servlets;

import letscode.accounts.AccountService;
import letscode.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserProfile userProfile = accountService.getUserBySessionId("sessionID-" + req.getRemoteAddr());
        if (userProfile != null) {
            resp.sendRedirect("/");

        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        UserProfile userProfile = accountService.getUserBySessionId("sessionID-" + req.getRemoteAddr());

        accountService.addSession(req.getSession().getId(), userProfile);
        resp.sendRedirect("/");
    }
    private boolean checkForErrors(HttpServletRequest req, String login, String pass){

        if (login==null||login.equals(""))
            req.setAttribute("loginError", "Поле пустое");
        else if(accountService.checkUserExist(accountService.getUserByLogin(login))) {

        }
        return true;
    }
}
