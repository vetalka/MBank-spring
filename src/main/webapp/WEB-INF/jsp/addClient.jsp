<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>        
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Client</title>

<script type="text/javascript" src="jquery-1.8.3.js"></script>

<script type="text/javascript">
	$(document).ready(
		function() {
			$.getJSON('<spring:url value="clientType.json"/>', {
				ajax : 'true'
			}, function(data){
				var html = '<option value="">--Please select one--</option>';
				var len = data.length;
				for (var i = 0; i < len; i++) {
					html += '<option value="' + data[i].desc + '">'
							+ data[i].desc + '</option>';
				}
				html += '</option>';
				
				$('#clientType').html(html);
			});
			
		});
	
</script>

</head>
<body>

   <form:form commandName="client">
        <table>
            <tr>
               <td>Enter Client Name </td>
               <td><form:input path="clientName" /></td>
            </tr>
            <tr>
               <td>Enter Password</td>            
               <td><form:input path="password"/></td>
            </tr>
            <tr>
               <td>Enter Client Type</td>
               <td>
               <form:select id="clientType" path="clientType"></form:select>
               </td>
            </tr>
            <tr>
               <td>Enter Address</td>
               <td><form:input path="address"/></td>
            </tr>
            <tr>
               <td>Enter Email</td>
               <td><form:input path="email"/></td>
            </tr>
            <tr>
               <td>Enter Phone Namber</td>
               <td><form:input path="phone"/></td>
            </tr>
            <tr>
               <td>Enter Comment</td>
               <td><form:input path="comment"/></td>
            </tr>
            <tr>
               <td colspan="8">
                   <input type="submit" value="Add Client">
               </td>
          
            </tr>
        </table>
        
    </form:form>
    
    
   
</body>
</html>