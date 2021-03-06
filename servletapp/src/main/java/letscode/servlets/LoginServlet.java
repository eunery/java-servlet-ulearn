package letscode.servlets;

import letscode.accounts.AccountService;
import letscode.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        if (login == null || pass == null || login.equals("")||pass.equals("")){
            resp.sendRedirect("login.jsp");
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);
        if (profile == null || !profile.getPass().equals(pass)){
            resp.sendRedirect("login.jsp");
            return;
        }


        accountService.addSession(req.getSession().getId(), profile);
        req.getSession().setAttribute("login", login);
        String path = accountService.getHomeDirectory() + '\\' + login;
        if(!path.endsWith(login))
            Files.createDirectory(Paths.get(accountService.getHomeDirectory() + '\\' + login));
        resp.sendRedirect("/");

    }
}
