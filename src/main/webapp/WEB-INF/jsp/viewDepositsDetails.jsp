<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>View Deposits Details</title>
</head>
<body>

    <h1>View Deposite Details</h1>

    <form:form commandName="deposit">
        <table>
           <tr>
              <td>Enter Deposit Id</td>
              <td><form:input path="depositId"/></td>
           </tr>
           <tr>
               <td colspan="2">
                   <input type="submit" value="View Deposit">
               </td>
          
            </tr>
            <tr>
               <td>Client Id</td>
               <td><form:input path="clientId" /></td>
            </tr>
            <tr>
               <td>Balance</td>            
               <td><form:input path="balance"/></td>
            </tr>
            <tr>
               <td>Deposit Type</td>
               <td><form:input path="depositType"/></td>
            </tr>
            <tr>
               <td>Estimated Balance</td>
               <td><form:input path="estimatedBalance"/></td>
            </tr>
            <tr>
               <td>Opening Date</td>
               <td><form:input path="openingDate"/></td>
            </tr>
            <tr>
               <td>Closing Date</td>
               <td><form:input path="closingDate"/></td>
            </tr>
        </table>
    </form:form>
    
    
<br>

        <div id="display">

        </div>

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