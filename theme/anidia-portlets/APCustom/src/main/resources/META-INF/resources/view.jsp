<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />
<%
String contentJson = (String)renderRequest.getAttribute("contentJson");

%>
<h3><%= contentJson %></h3>

<h3><%request.getAttribute("contentJson");%></h3>
<portlet:actionURL name="actionMethod1" var="sampleActionMethodURL">
</portlet:actionURL>
<form action="${sampleActionMethodURL}" method="post">
	<input type="submit" value="Get FAQS"> 
</form>
