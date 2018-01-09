<%--
 - Author: Ryan Johns
 - Version: 0.1.0 (Nov 21, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${not empty offenderSummary }">
	<c:out value="${offenderSummary.lastName}, ${offenderSummary.firstName} "/><c:if test="${not empty offenderSummary.middleName}"><c:out value="${offenderSummary.middleName} "/></c:if><c:if test="${not empty offenderSummary.suffix}"> <c:out value="${offenderSummary.suffix} "/></c:if> #<c:out value="${offenderSummary.offenderNumber}"/>
</c:if>