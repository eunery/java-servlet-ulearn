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
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String email = req.getParameter("email");
        if (    login == null || login.equals("") ||
                pass  == null || pass.equals("") ||
                email == null || email.equals("")){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.sendRedirect("/registration.jsp");
            return;
        }
        Path userDirectoryPath = Paths.get(accountService.getHomeDirectory() + '\\' + login);
        if (Files.exists(userDirectoryPath)){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Files.createDirectory(userDirectoryPath);
        UserProfile profile = new UserProfile(login, pass, email);
        accountService.addNewUser(profile);
        accountService.addSession(req.getSession().getId(), profile);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/");
    }
}