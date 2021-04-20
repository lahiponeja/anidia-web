<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="blogsmodule.caption"/></b>
</p>

<portlet:actionURL name="prueba" var="pruebaUrl"/>

<aui:form action="${pruebaUrl}">
	<aui:input name="nombreEscritor" type="textarea" label="Escribe aquí el nombre del escritor:"/>
	<aui:button name="addEscritorButton" type="submit" value="Crear escritor"/>
</aui:form>



<portlet:actionURL name="prueba2" var="pruebaUrl2">
</portlet:actionURL>

<liferay-ui:icon-menu>
	<liferay-ui:icon image="edit" message="Editar" url="<%=pruebaUrl2%>"/>
</liferay-ui:icon-menu>
<p>
	<b>"${mensaje}"</b>
</p>

<p>
	<b>"${mensaje2}"</b>
</p>