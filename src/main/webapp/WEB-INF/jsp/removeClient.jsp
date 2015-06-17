<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>        
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remove Client</title>
</head>
<body>

 <form:form commandName="client">
        <table>
            <tr>
               <td>Enter Client Id</td>
               <td><form:input path="clientId"/></td>
            </tr>
            <tr>
               <td colspan="2">
                   <input type="submit" value="Remove Client">
               </td> 
        </table>
        
    </form:form>
</body>
</html>