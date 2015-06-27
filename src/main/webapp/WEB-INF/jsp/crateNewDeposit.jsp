<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>        
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Crate New Deposite</title>
</head>
<body>

    <h1>Create New Deposite</h1>
    <form:form commandName="deposit">
         <table>
            <tr>
               <td>Enter ClientId</td>
               <td><form:input path="clientId" /></td>
            </tr>
            <tr>
               <td>Enter Balance</td>
               <td><form:input path="balance"/></td>
            </tr>
            <tr>
               <td>Enter Opening Date</td>
               <td>
                 <p><input name="openingDate" type="date" id="openingDate"></p>
               </td>             
            </tr>
            <tr>
               <td>Enter Closing Date</td>
               <td>
                  <p><input name="closingDate" type="date" id="closingDate"></p>
               </td>
            </tr>
            <tr>
               <td colspan="3">
                   <input type="submit" value="Create New Deposit">
               </td>
          
            </tr>
        </table>
   </form:form>
   
</body>
</html>