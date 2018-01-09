<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<c:choose>
		<c:when test="${empty visitationAssociations}">
			<option value=""><fmt:message key="noneApprovedLabel"/></option>
		</c:when>
		<c:otherwise>
				<jsp:include page="../../../includes/nullOption.jsp"/>
				<option value="" disabled="disabled"><fmt:message key="approvedLabel"/></option>
				<c:forEach var="visitationAssociation" items="${visitationAssociations}">
					<c:if test="${not empty visitationAssociation.approval and visitationAssociation.approval.approved}">
					<c:choose>
						<c:when test="${visitationAssociation.id eq visitor.id}">
							<option value="${visitationAssociation.id}" selected="selected"><c:out value="${visitationAssociation.relationship.secondPerson.name.firstName} ${visitationAssociation.relationship.secondPerson.name.lastName}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${visitationAssociation.id}"><c:out value="${visitationAssociation.relationship.secondPerson.name.firstName} ${visitationAssociation.relationship.secondPerson.name.lastName}"/></option>
						</c:otherwise>
					</c:choose>
					</c:if>
				</c:forEach>
				<option value="" disabled="disabled"><fmt:message key="specialVisitLabel"/></option>
				<c:forEach var="visitationAssociation" items="${visitationAssociations}">
					<c:if test="${visitationAssociation.flags.specialVisit and not visitationAssociation.approval.approved}">
						<c:choose>
							<c:when test="${visitationAssociation.id eq visitor.id}">
								<option value="${visitationAssociation.id}" selected="selected"><c:out value="${visitationAssociation.relationship.secondPerson.name.firstName} ${visitationAssociation.relationship.secondPerson.name.lastName}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${visitationAssociation.id}"><c:out value="${visitationAssociation.relationship.secondPerson.name.firstName} ${visitationAssociation.relationship.secondPerson.name.lastName}"/></option>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
		</c:otherwise>
	</c:choose>
</fmt:bundle>