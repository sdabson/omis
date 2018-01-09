<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.audit.msgs.audit">
<p class="updateSignature">
	<fmt:message key="updateSignatureMessage">
		<fmt:param>
			<c:out value="${updatable.updateSignature.userAccount.user.name.lastName}"/>,
			<c:out value="${updatable.updateSignature.userAccount.user.name.firstName}"/>
			(<c:out value="${updatable.updateSignature.userAccount.username}"/>)
		</fmt:param>
		<fmt:param>
			<fmt:formatDate value="${updatable.updateSignature.date}" pattern="MM/dd/yyyy"/>
		</fmt:param>
	</fmt:message>
</p>
</fmt:bundle>