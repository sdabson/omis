<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<tr id="labWorkRequirementRequestItems[${currentIndex}].row">
	<td>
		<a id="labWorkRequirementRequestItems[${currentIndex}].removeLink" class="removeLink" href="${pageContext.request.contextPath}/health/labWork/requirement/request/removeRequestItem.html?facility=${facility.id}" title="<fmt:message key='removeLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
		<input type="hidden" id="labWorkRequirementRequestItems[${currentIndex}].operation" name="labWorkRequirementRequestItems[${currentIndex}].operation" value="${requestLabWorkRequirementForm.labWorkRequirementRequestItems[currentIndex].operation.name}"/>
		<input type="hidden" id="labWorkRequirementRequestItems[${currentIndex}].request" name="labWorkRequirementRequestItems[${currentIndex}].request" value="${requestLabWorkRequirementForm.labWorkRequirementRequestItems[currentIndex].request.id}"/>
	</td>
	<td>
		<select name="labWorkRequirementRequestItems[${currentIndex}].sampleOrderedBy">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="providerAssignment" items="${providerAssignments}">
			<c:choose>
				<c:when test="${providerAssignment eq requestLabWorkRequirementForm.labWorkRequirementRequestItems[currentIndex].sampleOrderedBy}">
					<option value="${providerAssignment.id}" selected="selected">
						<c:out value="${providerAssignment.provider.name.lastName}"/>,
						<c:out value="${providerAssignment.provider.name.firstName}"/>
						<c:out value="${providerAssignment.title.abbreviation}"/>
					</option>
				</c:when>
				<c:otherwise>
					<option value="${providerAssignment.id}">
						<c:out value="${providerAssignment.provider.name.lastName}"/>,
						<c:out value="${providerAssignment.provider.name.firstName}"/>
						<c:out value="${providerAssignment.title.abbreviation}"/>
					</option>
				</c:otherwise>
			</c:choose>
			</c:forEach>
		</select>
		<form:errors path="labWorkRequirementRequestItems[${currentIndex}].sampleOrderedBy" cssClass="error"/>
	</td>
	<td>
		<input id="labWorkRequirementRequestItems[${currentIndex}].sampleOrderedDate" name="labWorkRequirementRequestItems[${currentIndex}].sampleOrderedDate" type="text" class="date" value="<fmt:formatDate value='${requestLabWorkRequirementForm.labWorkRequirementRequestItems[currentIndex].sampleOrderedDate}' pattern='MM/dd/yyyy'/>"/>
		<form:errors path="labWorkRequirementRequestItems[${currentIndex}].sampleOrderedDate" cssClass="error"/>
	</td>
	<td>
		<select id="labWorkRequirementRequestItems[${currentIndex}].category" name="labWorkRequirementRequestItems[${currentIndex}].category">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="category" items="${labWorkCategories}">
				<c:choose>
					<c:when test="${category eq requestLabWorkRequirementForm.labWorkRequirementRequestItems[currentIndex].category}">
						<option value="${category.id}" selected="selected"><c:out value="${category.name}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${category.id}"><c:out value="${category.name}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="labWorkRequirementRequestItems[${currentIndex}].category" cssClass="error"/>
	</td>
	<td>
		<input id="labWorkRequirementRequestItems[${currentIndex}].sampleDate" name="labWorkRequirementRequestItems[${currentIndex}].sampleDate" type="text" class="date" value="<fmt:formatDate value='${requestLabWorkRequirementForm.labWorkRequirementRequestItems[currentIndex].sampleDate}' pattern='MM/dd/yyyy'/>"/>
		<form:errors path="labWorkRequirementRequestItems[${currentIndex}].sampleDate" cssClass="error"/>
	</td>
	<td>		
		<select id="labWorkRequirementRequestItems[${currentIndex}].sampleLab" name="labWorkRequirementRequestItems[${currentIndex}].sampleLab">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="lab" items="${labs}">
				<c:choose>
					<c:when test="${lab eq requestLabWorkRequirementForm.labWorkRequirementRequestItems[currentIndex].sampleLab}">
						<option value="${lab.id}" selected="selected"><c:out value="${lab.name}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${lab.id}"><c:out value="${lab.name}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="labWorkRequirementRequestItems[${currentIndex}].sampleLab" cssClass="error"/>
	</td>
	<td>
		<select id="labWorkRequirementRequestItems[${currentIndex}].resultsLab" name="labWorkRequirementRequestItems[${currentIndex}].resultsLab">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="lab" items="${labs}">
				<c:choose>
					<c:when test="${lab eq requestLabWorkRequirementForm.labWorkRequirementRequestItems[currentIndex].resultsLab}">
						<option value="${lab.id}" selected="selected"><c:out value="${lab.name}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${lab.id}"><c:out value="${lab.name}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="labWorkRequirementRequestItems[${currentIndex}].resultsLab" cssClass="error"/>
	</td>
	<td>
		<span class="fieldGroup">
			<label for="labWorkRequirementRequestItems[${currentIndex}].noLeakySampleRestriction"><fmt:message key="noLeakySampleRestrictionLabel" bundle="${healthBundle}"/></label>
			<c:choose>
				<c:when test="${requestLabWorkRequirementForm.labWorkRequirementRequestItems[currentIndex].noLeakySampleRestriction}">
					<input id="labWorkRequirementRequestItems[${currentIndex}].noLeakySampleRestriction" name="labWorkRequirementRequestItems[${currentIndex}].noLeakySampleRestriction" type="checkbox" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input id="labWorkRequirementRequestItems[${currentIndex}].noLeakySampleRestriction" name="labWorkRequirementRequestItems[${currentIndex}].noLeakySampleRestriction" type="checkbox"/>
				</c:otherwise>
			</c:choose>
		</span>
		<span class="fieldGroup">
			<label for="labWorkRequirementRequestItems[${currentIndex}].nothingPerOralSampleRestriction"><fmt:message key="nothingPerOralSampleRestrictionLabel" bundle="${healthBundle}"/></label>
			<c:choose>
				<c:when test="${requestLabWorkRequirementForm.labWorkRequirementRequestItems[currentIndex].nothingPerOralSampleRestriction}">
					<input id="labWorkRequirementRequestItems[${currentIndex}].nothingPerOralSampleRestriction" name="labWorkRequirementRequestItems[${currentIndex}].nothingPerOralSampleRestriction" type="checkbox" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input id="labWorkRequirementRequestItems[${currentIndex}].nothingPerOralSampleRestriction" name="labWorkRequirementRequestItems[${currentIndex}].nothingPerOralSampleRestriction" type="checkbox"/>
				</c:otherwise>
			</c:choose>
		</span>
		<span class="fieldGroup">
			<label for="labWorkRequirementRequestItems[${currentIndex}].noMedsSampleRestriction"><fmt:message key="noMedsSampleRestrictionLabel" bundle="${healthBundle}"/></label>
			<c:choose>
				<c:when test="${requestLabWorkRequirementForm.labWorkRequirementRequestItems[currentIndex].noMedsSampleRestriction}">
					<input id="labWorkRequirementRequestItems[${currentIndex}].noMedsSampleRestriction" name="labWorkRequirementRequestItems[${currentIndex}].noMedsSampleRestriction" type="checkbox" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input id="labWorkRequirementRequestItems[${currentIndex}].noMedsSampleRestriction" name="labWorkRequirementRequestItems[${currentIndex}].noMedsSampleRestriction" type="checkbox"/>
				</c:otherwise>
			</c:choose>
		</span>
	</td>
	<td>
		<textarea id="labWorkRequirementRequestItems[${currentIndex}].schedulingNotes" name="labWorkRequirementRequestItems[${currentIndex}].schedulingNotes"><c:out value="${requestLabWorkRequirementForm.labWorkRequirementRequestItems[currentIndex].schedulingNotes}"/></textarea>
		<form:errors path="labWorkRequirementRequestItems[${currentIndex}].schedulingNotes" cssClass="error"/>
	</td>
	<td>
		<form:errors path="labWorkRequirementRequestItems[${currentIndex}]" cssClass="error"/>
	</td>
</tr>