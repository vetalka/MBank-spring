<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Account</title>
</head>
<body>

   <h1>Add Account</h1>
   <form:form commandName="account">
         <table>
            <tr>
               <td>Enter Balance</td>
               <td><form:input path="balance" /></td>
            </tr>
            <tr>
               <td colspan="2">
                   <input type="submit" value="Add Account">
               </td>
          
            </tr>
        </table>
   </form:form>
   
  
</body>
</html>