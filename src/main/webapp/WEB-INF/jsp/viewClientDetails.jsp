<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>View Client Details</title>
</head>
<body>
 <h1>View Client Details</h1>

    <form:form commandName="client">
        <table>
           <tr>
              <td>Enter Client Id</td>
              <td><form:input path="clientId"/></td>
           </tr>
           <tr>
               <td colspan="2">
                   <input type="submit" value="View Client">
               </td>
          
            </tr>
            <tr>
               <td>Client Name </td>
               <td><form:input path="clientName" /></td>
            </tr>
            <tr>
               <td>Password</td>            
               <td><form:input path="password"/></td>
            </tr>
            <tr>
               <td>Client Type</td>
               <td>
               <form:input path="clientType"/>
               </td>
            </tr>
            <tr>
               <td>Address</td>
               <td><form:input path="address"/></td>
            </tr>
            <tr>
               <td>Email</td>
               <td><form:input path="email"/></td>
            </tr>
            <tr>
               <td>Phone Namber</td>
               <td><form:input path="phone"/></td>
            </tr>
            <tr>
               <td>Comment</td>
               <td><form:input path="comment"/></td>
            </tr>
           
        </table>
        
    </form:form>
    
    <br>

        <div id="display">

        </div>

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