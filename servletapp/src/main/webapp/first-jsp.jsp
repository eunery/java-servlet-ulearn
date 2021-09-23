<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.File" %>

<% List<File> filesList = (List) request.getAttribute("filesList"); %>

<!doctype html>
<html>
<head>
    <title>First JSP</title>
</head>
<body>
<br>
<%= LocalDateTime.now() %>
<h1><%= request.getParameter("path") %></h1>
<h2> <a href='?path=<%= request.getAttribute("parentPath") %>'> UP </a> </h2>
    
<table>
    <tr>
        <th>Name</th>
        <th>Size</th>
        <th>Date</th>
    </tr>
    <% for (File item : filesList) { %>
    <tr>
        <td> <a href="?path=<%=item.getAbsolutePath() %>"> <%= item.getName() %>
        </a>
        </td>
        <td><%= item.length() %>
        </td>
        <td><%= item.length() %>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
