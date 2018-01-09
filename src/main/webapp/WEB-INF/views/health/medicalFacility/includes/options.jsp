<%--
 - Options for medical facilities.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
<c:forEach var="medicalFacility" items="${medicalFacilities}">
	<option value="${medicalFacility.id}"><c:out value="${medicalFacility.name}"/></option>
</c:forEach>