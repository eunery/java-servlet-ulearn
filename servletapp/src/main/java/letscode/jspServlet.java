package letscode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@WebServlet("/")
public class jspServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<File> filesList = Files.walk(Paths.get(req.getParameter("path") == null ? "src/main" : req.getParameter("path")))
                .map(Path::toFile)
                .collect(Collectors.toList());
        req.setAttribute("filesList", filesList);
        getServletContext().getRequestDispatcher("/first-jsp.jsp").forward(req, resp);
    }
}
