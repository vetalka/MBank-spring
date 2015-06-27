<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Client</title>
</head>
<body>

     <h1>Update Client</h1>
     <form:form commandName="client">
        <table>
            <tr>
              <td>Enter Client Id</td>
              <td><form:input path="clientId"/></td>
            </tr>
            <tr>
               <td>Update Address</td>
               <td><form:input path="address"/></td>
            </tr>
            <tr>
               <td>Update Email</td>
               <td><form:input path="email"/></td>
            </tr>
            <tr>
               <td>Update Phone</td>
               <td><form:input path="phone"/></td>
            </tr>
            <tr>
               <td colspan="5">
                   <input type="submit" value="Update Client">
               </td> 
             </tr>   
        </table>   
    </form:form>

</body>
</html>