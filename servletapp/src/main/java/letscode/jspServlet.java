package letscode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/")
public class jspServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path") == null ? "../" : req.getParameter("path");
        String parentPath = new File(path).getAbsoluteFile().getParent();

        List<File> filesList = Files.walk(Paths.get(path))
                .map(Path::toFile)
                .collect(Collectors.toList());

        Map<File, FileTime> filesDate = new HashMap<File, FileTime>() {};
        for (File item: filesList) {
            filesDate.put(item,getCreationDate(item.getAbsolutePath()));
        }

        req.setAttribute( "filesDate", filesDate);
        req.setAttribute("filesList", filesList);
        req.setAttribute("parentPath", parentPath);
        getServletContext().getRequestDispatcher("/first-jsp.jsp").forward(req, resp);
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
