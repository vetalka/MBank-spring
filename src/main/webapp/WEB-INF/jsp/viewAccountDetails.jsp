<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>View Acount Details</title>
</head>
<body>
    <form:form commandName="account">
        <table>
           <tr>
              <td>Enter Account Id</td>
              <td><form:input path="accountId"/></td>
           </tr>
           <tr>
               <td colspan="2">
                   <input type="submit" value="View Acccount">
               </td>
          
            </tr>
            <tr>
               <td>Balance </td>
               <td><form:input path="balance" /></td>
            </tr>
            <tr>
               <td>Credit Limit</td>            
               <td><form:input path="creditLimit"/></td>
            </tr>
            <tr>
               <td>Comment</td>
               <td><form:input path="comment"/></td>
            </tr>
            <tr>
               <td>Client Id</td>
               <td><form:input path="clientId"/></td>
            </tr>
        </table>
    </form:form>
    
    
<br>

        <div id="display">

        </div>

        <table border="2">
            <th>Account Id</th>
            <th>Balance</th>
            <th>Credit Limit</th>
            <th>Comment</th>
            <th>Client Id</th>
            <c:forEach items="${accountList}" var="account">
                <tr>
                    <td>${account.accountId}</td>
                    <td>${account.balance}</td>
                    <td>${account.creditLimit}</td>
                    <td>${account.comment}</td>
                    <td>${account.clientId}</td>
                </tr>
            </c:forEach>
        </table>
    
</body>
</html>