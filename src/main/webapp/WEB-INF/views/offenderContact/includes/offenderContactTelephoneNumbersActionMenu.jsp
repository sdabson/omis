<%--
  - Action menu for offender contact telephone numbers.
  -
  - Author: Josh Divine
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.offendercontact.msgs.offenderContact">
	<ul>
		<li><a href="${pageContext.request.contextPath}/offenderContact/createTelephoneNumber.html" id="createOffenderContactTelephoneNumberLink" class="createLink"><fmt:message key="createOffenderContactTelephoneNumberLink"/></a></li>
	</ul>
</fmt:bundle>