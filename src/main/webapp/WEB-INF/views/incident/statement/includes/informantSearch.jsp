<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.incident.msgs.incident">
	<c:if test="${not empty informationSourceCategory}">
		<c:choose>
			<c:when test="${informationSourceCategory eq 'OTHER'}">
				<label class="fieldLabel"><fmt:message key="nameLabel"/></label>
				<c:choose>
					<c:when test='${not empty submissionDisabledAttribute and submissionDisabledAttribute eq "true"}'>
						<input type="text" name="informationSourceName" value="${incidentStatementForm.informationSourceName}" readonly="readonly"/>
					</c:when>
					<c:otherwise>
						<input type="text" name="informationSourceName" value="${incidentStatementForm.informationSourceName}"/>
					</c:otherwise>
				</c:choose>
				<form:errors cssClass="error" path="informationSourceName"/>
			</c:when>
			<c:otherwise>
				<c:if test="${informationSourceCategory ne 'ANONYMOUS'}">
					<label for="informant" class="fieldLabel"><fmt:message key="nameLabel"/></label>
					<input type="hidden" name="informant" id="informant" value="${incidentStatementForm.informant.id}"/>
					<c:choose>
						<c:when test='${not empty submissionDisabledAttribute and submissionDisabledAttribute eq "true"}'>
							<input type="text" id="informantInput" readonly="readonly"/>
						</c:when>
						<c:otherwise>
							<input type="text" id="informantInput"/>
						</c:otherwise>
					</c:choose>
					<c:if test="${informationSourceCategory eq 'STAFF'}">
						<a id="informantCurrent" class="currentUserAccountLink"></a>
					</c:if>
					<a id="informantClear" class="clearLink"></a>
					<span id="informantDisplay">
						<c:if test="${not empty incidentStatementForm.informant}">
							<fmt:message key="informantInformation">
								<fmt:param value="${incidentStatementForm.informant.name.lastName}"/>
								<fmt:param value="${incidentStatementForm.informant.name.firstName}"/>
							</fmt:message>
						</c:if>
					</span>
			</c:if>
			</c:otherwise>
		</c:choose>
	</c:if>
	<form:errors cssClass="error" path="informant"/>
</fmt:bundle>