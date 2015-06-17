<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>View Activity Details</title>
</head>
<body>
    <form:form commandName="activity">
        <table>
           <tr>
              <td>Enter Activity Id</td>
              <td><form:input path="activityId"/></td>
           </tr>
           <tr>
               <td colspan="2">
                   <input type="submit" value="View Activity">
               </td>
            </tr>
            
            <tr>
               <td>Amount </td>
               <td><form:input path="amount" /></td>
            </tr>
            <tr>
               <td>Activity Date</td>            
               <td><form:input path="activityDate"/></td>
            </tr>
            <tr>
               <td>Commission</td>
               <td><form:input path="commission"/></td>
            </tr>
            <tr>
               <td>Description</td>
               <td><form:input path="description"/></td>
            </tr>
            <tr>
               <td>Client_Id</td>
               <td><form:input path="clientId"/></td>
            </tr>
        </table>
    </form:form>
    
    <br>

        <div id="display">

        </div>

        <table border="2">
            <th>Activity Id</th>
            <th>Amount</th>
            <th>Activity Date</th>
            <th>Commission</th>
            <th>Description</th>
            <th>Client Id</th>
            <c:forEach items="${activityList}" var="activity">
                <tr>
                    <td>${activity.activityId}</td>
                    <td>${activity.amount}</td>
                    <td>${activity.activityDate}</td>
                    <td>${activity.commission}</td>
                    <td>${activity.description}</td>
                    <td>${activity.clientId}</td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>