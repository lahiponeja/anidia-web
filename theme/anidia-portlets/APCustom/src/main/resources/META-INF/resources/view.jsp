<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />

<h3>This portlet whas created by the mighty Dani!</h3>


<portlet:actionURL name="actionMethod1" var="sampleActionMethodURL">
</portlet:actionURL>
<form action="${sampleActionMethodURL}" method="post">
	<input type="submit" value="Submit"> 
</form>
