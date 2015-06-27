<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>View System Property</title>
</head>
<body>
<h1>View System Property</h1>
      <table border="1">

            <th>Property Key</th>
            <th>Property Value</th>
            <c:forEach items="${propertiesList}" var="properties">
                <tr>
                    <td>${properties.propKey}</td>
                    <td>${properties.propValue}</td>
                </tr>
            </c:forEach>
        </table>
   
</body>
</html>