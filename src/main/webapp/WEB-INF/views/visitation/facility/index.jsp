<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<label><fmt:message key="facilityLabel"/></label>
	<select id="facilitySelect">
		<option value="">...</option>
		<c:forEach var="facility" items="${facilities}" varStatus="status">
			<option value="${facility.id}"><c:out value="${facility.name}"/></option>
		</c:forEach>
	</select>
	<ul id="taskLinks" class="links taskLinks">
		<sec:authorize access="hasRole('VISITATION_LIST_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" id="facilityListLink" href="${pageContext.request.contextPath}/visitation/list.html">
					<fmt:message key="viewVisitsListLink"/>
				</a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('VISITATION_VISITORS') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" id="facilityLogLink" href="${pageContext.request.contextPath}/visitation/visitorLog.html?">
					<fmt:message key="viewVisitorLogLink"/>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>