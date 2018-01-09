<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach items="${jurisdictions}" var="jur">
		<option value="${jur.id}" ${jur == jurisdiction ? 'selected="selected"' : ''} >
			<c:out value="${jur.name}"/>
		</option>
	</c:forEach>
</fmt:bundle>