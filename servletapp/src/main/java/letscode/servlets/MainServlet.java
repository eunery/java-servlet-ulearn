package letscode.servlets;

import letscode.accounts.UserProfile;
import letscode.accounts.AccountService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(urlPatterns = "/")
public class MainServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserProfile userProfile = accountService.getUserBySessionId(req.getSession().getId());
        String path = req.getParameter("path");
        if (userProfile == null || userProfile.equals(null)){
            resp.sendRedirect("/login");
        }
        if (path == null || path.equals("")) {
            path = accountService.getHomeDirectory() + '\\' + userProfile.getLogin();
        }
        if (!path.startsWith(accountService.getHomeDirectory() + '\\' + userProfile.getLogin())){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String parentPath = new File(path).getAbsoluteFile().getParent();
        if (req.getParameter("download") == null) {
            List<File> filesList = Stream.of(new File(path).listFiles())
                    .map(File::getAbsoluteFile)
                    .collect(Collectors.toList());

            Map<File, FileTime> filesDate = new HashMap<File, FileTime>() {};
            for (File item : filesList) {
                filesDate.put(item, getCreationDate(item.getAbsolutePath()));
            }
            req.setAttribute("filesDate", filesDate);
            req.setAttribute("filesList", filesList);
            req.setAttribute("parentPath", parentPath);
            req.setAttribute("currentDir", path);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/download");
        }
    }

    private FileTime getCreationDate(String absolutePath) {

        Path file = Paths.get(absolutePath);
        try {
            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
            return attr.creationTime();
        } catch (IOException e) {
            return FileTime.fromMillis(0);
        }

    }



}
