<%--
  - Summary of online account.
  -
  - Author: Joel Norris
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="contact" uri="/WEB-INF/tld/contact.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.contact.msgs.onlineAccount"	var="onlineAccount"/>
<span class="subSectionLine">
	<c:if test="${not empty onlineAccountSummarizable.onlineAccountCategory}">
		<label><fmt:message key="onlineAccount.${onlineAccountSummarizable.onlineAccountCategory}.categoryLabel" bundle="${onlineAccount}"/></label>
	</c:if>
	<c:if test="${onlineAccountSummarizable.onlineAccountActive}">
		<label><fmt:message key="activeOnlineAccount" bundle="${onlineAccount}"/></label>
	</c:if>
	<c:if test="${onlineAccountSummarizable.onlineAccountPrimary}">
		<label><fmt:message key="primaryOnlineAccount" bundle="${onlineAccount}"/></label>
	</c:if>
</span>
<span class="subSectionLine">
	<c:out value="${onlineAccountSummarizable.onlineAccountName}"/>
</span>