<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>View All Activity Details</title>
</head>
<body>
<h1>View All Activities Details</h1>
       <table border="1">
            <th>Activity Id</th>
            <th>Amount</th>
            <th>Activity Date</th>
            <th>Commission</th>
            <th>Description</th>
            <th>Client Id</th>
            <c:forEach items="${activityList}" var="activity">
                <tr>
                    <td>${activity.activityId}</td>
                    <td>${activity.amount}</td>
                    <td>${activity.activityDate}</td>
                    <td>${activity.commission}</td>
                    <td>${activity.description}</td>
                    <td>${activity.clientId}</td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>