<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Deposit To Account</title>
</head>
<body>

     <form:form commandName="activity">
        <table>
            <tr>
               <td>Enter Client Id</td>
               <td><form:input path="clientId"/></td>
            </tr>    
            <tr>
               <td>Enter Amount</td>
               <td><form:input path="amount"/></td>
            </tr>
            <tr>
               <td colspan="3">
                   <input type="submit" value="Deposit">
               </td>
          
            </tr>
        </table>
     </form:form>

</body>
</html>