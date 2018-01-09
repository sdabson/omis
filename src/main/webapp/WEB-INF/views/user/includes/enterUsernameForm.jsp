<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.msgs.common">
<form action="${pageContext.request.contextPath}/user/userAccount/enterUserAccount.html" method="get">
	<p>
	<input type="hidden" name="redirectUrl" value="${redirectUrl}"/>
	</p>
	<p>
	<input type="text" name="username" id="username"/>
	<input type="submit" value="<fmt:message key='okLabel'/>"/>
	</p>
</form>
</fmt:bundle>