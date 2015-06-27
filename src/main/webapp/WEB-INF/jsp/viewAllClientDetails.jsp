<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>View All Clients</title>
</head>
<body>
<h1>View All Clients</h1>

   
        <table border="1">
            <th>Client Id</th>
            <th>Client Name</th>
            <th>Password</th>
            <th>Client Type</th>
            <th>Address</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Comment</th>
            <c:forEach items="${clientList}" var="client">
                <tr>
                    <td>${client.clientId}</td>
                    <td>${client.clientName}</td>
                    <td>${client.password}</td>
                    <td>${client.clientType}</td>
                    <td>${client.address}</td>
                    <td>${client.email}</td>
                    <td>${client.phone}</td>
                    <td>${client.comment}</td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>