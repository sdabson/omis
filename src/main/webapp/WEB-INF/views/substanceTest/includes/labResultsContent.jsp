<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.substance.msgs.substance">
	<span class="fieldGroup">
			<label for="labRequestDate" class="fieldLabel"><fmt:message key="labRequestDateLabel"/></label>
			<input type="text" id="labRequestDate" name="labRequestDate" value="<fmt:formatDate value="${substanceTestForm.labRequestDate}" pattern="MM/dd/yyyy"/>"/>
			<form:errors cssClass="error" path="labRequestDate"/>
	</span>
	<span class="fieldGroup">
			<label for="lab" class="fieldLabel"><fmt:message key="labLabel"/></label>
			<select id="lab" name="lab">
				<option value="">...</option>
				<c:forEach items="${substanceLabs}" var="substanceLab" varStatus="status">
					<c:choose>
						<c:when test="${substanceTestForm.lab eq substanceLab}">
							<option value="${substanceLab.id}" selected="selected"><c:out value="${substanceLab.name}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${substanceLab.id}"><c:out value="${substanceLab.name}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<form:errors cssClass="error" path="lab"/>
	</span>
	<span id="justificationContainer">
		<c:set value="${substanceTestForm.lab.privateLab}" var="privateLab" scope="request"/>
		<jsp:include page="justificationContent.jsp"/>
	</span>
	<span class="fieldGroup">
			<label for="labResultDate" class="fieldLabel"><fmt:message key="labResultDateLabel"/></label>
			<input type="text" id="labResultDate" name="labResultDate" value="<fmt:formatDate value="${substanceTestForm.labResultDate}" pattern="MM/dd/yyyy"/>"/>
			<form:errors cssClass="error" path="labResultDate"/>
	</span>
	<jsp:include page="labResultsTable.jsp"/>
	<form:errors path="labResultItems" cssClass="error"/>
</fmt:bundle>