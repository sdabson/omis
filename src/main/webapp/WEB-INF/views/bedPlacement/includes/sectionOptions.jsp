<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.bedplacement.msgs.bedPlacement">
	<jsp:include page="../../includes/nullOption.jsp"/>
	<c:forEach var="section" items="${matchingSections}" varStatus="status">
		<c:set var="sectionName" value="${section.name}"/>
		<c:if test="${not empty section.level}">
			<c:set var="sectionName" value="${section.level.name} - ${sectionName}"/>
		</c:if>
		<c:if test="${not empty section.unit}">
			<c:set var="sectionName" value="${section.unit.name} - ${sectionName}"/>
		</c:if>
		<c:if test="${not empty section.complex}">
			<c:set var="sectionName" value="${section.complex.name} - ${sectionName}"/>
		</c:if>
		<c:choose>
			<c:when test="${not empty bedPlacementForm.section and bedPlacementForm.section eq section}">
				<option value="${section.id}" selected="selected"><c:out value="${sectionName}"/></option>
			</c:when>
			<c:otherwise>
				<option value="${section.id}"><c:out value="${sectionName}"/></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</fmt:bundle>