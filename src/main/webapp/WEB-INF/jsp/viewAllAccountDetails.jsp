<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>View All Account Details</title>
</head>
<body>
    <h1>View All Account Details</h1>
      <table border="1">

            <th>Account Id</th>
            <th>Balance</th>
             <th>Client Id</th>
            <th>Comment</th>
           <th>Credit Limit</th>
            <c:forEach items="${accountList}" var="account">
                <tr>
                    <td>${account.accountId}</td>
                    <td>${account.balance}</td>
                    <td>${account.clientId}</td>
                    <td>${account.comment}</td>
                    <td>${account.creditLimit}</td>
                </tr>
            </c:forEach>
        </table>

</body>
</html>