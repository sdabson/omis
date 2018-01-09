<%--
  - Identification number action menu.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.identificationnumber.msgs.identificationNumber" var="identificationNumberBundle"/>
<ul>
	<sec:authorize access="hasRole('IDENTIFICATION_NUMBER_LIST') or hasRole('ADMIN')">
		<li>
			<a href="${pageContext.request.contextPath}/identificationNumber/list.html?offender=${offender.id}" title="<fmt:message key='listIdentificationNumbersLink' bundle='${identificationNumberBundle}'/>" class="listLink"><fmt:message key="listIdentificationNumbersLink" bundle="${identificationNumberBundle}"/></a>
		</li>
	</sec:authorize>
</ul>