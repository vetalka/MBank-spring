<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>View All Deposits Details</title>
</head>
<body>

      <table border="2">
            <th>Deposit Id</th>
            <th>Client Id</th>
            <th>Balance</th>
            <th>Deposit Type</th>
            <th>Estimated Balance</th>
            <th>Opening Date</th>
            <th>Closing Date</th>
            <c:forEach items="${depositList}" var="deposit">
                <tr>
                    <td>${deposit.depositId}</td>
                    <td>${deposit.clientId}</td>
                    <td>${deposit.balance}</td>
                    <td>${deposit.depositType}</td>
                    <td>${deposit.estimatedBalance}</td>
                    <td>${deposit.openingDate}</td>
                    <td>${deposit.closingDate}</td>
                </tr>
            </c:forEach>
        </table>


</body>
</html>