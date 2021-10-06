package letscode.servlets;

import letscode.accounts.AccountService;
import letscode.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    AccountService accountService = new AccountService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sessionID = req.getSession().getId();
        accountService.deleteSession(sessionID);

        resp.sendRedirect("/login.jsp");
    }
}
