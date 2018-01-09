<%--
  - Action menu for offender contact online accounts.
  -
  - Author: Josh Divine
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.offendercontact.msgs.offenderContact">
	<ul>
		<li><a href="${pageContext.request.contextPath}/offenderContact/createOnlineAccount.html" id="createOffenderContactOnlineAccountLink" class="createLink"><fmt:message key="createOffenderContactOnlineAccountLink"/></a></li>
	</ul>
</fmt:bundle>