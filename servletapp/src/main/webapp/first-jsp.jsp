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
<h1><%= request.getRequestURI() %>
</h1>

<table>
    <tr>
        <th>Name</th>
        <th>Size</th>
        <th>Date</th>
    </tr>
    <% for (File item : filesList) { %>
    <tr>
        <td> <a href="<%  %>"> <%= item.getName() %>
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