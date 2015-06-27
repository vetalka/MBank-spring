<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Update System Property</title>
</head>
<body>

<h1>Update System Property</h1>
        <form:form commandName="properties">
        <table>
            <tr>
              <td>Enter Property Key</td>
              <td><form:input path="propKey"/></td>
            </tr>
            <tr>
               <td>Update Property Value</td>
               <td><form:input path="propValue"/></td>
            </tr>
            <tr>
               <td colspan="3">
                   <input type="submit" value="Update Property">
               </td> 
             </tr>   
        </table>   
    </form:form>
  
</body>
</html>