<%--
 - Author: Joel Norris
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<tr id="labWorkResultsItem${labWorkResultsIndex}" class="${cssClass}">
		<th class="operations">
			<input type="hidden" name="labWorkResultsItems[${labWorkResultsIndex}].process" id="labWorkProcess${labWorkIndex}" value="${labWorkItems[labWorkIndex].process}"/>
			<input type="hidden" name="labWorkResultsItems[${labWorkResultsIndex}].labWork" id="labWorkId${labWorkIndex}" value="${labWorkItems[labWorkIndex].labWork.id}"/>
			<input type="hidden" name="labWorkResultsItems[${labWorkResultsIndex}].labWorkRequirementRequest" id="labWorkRequirementRequest${labWorkIndex}" 
				value="${labWorkResultsItems[labWorkResultsIndex].labWorkRequirementRequest.id}"/>
			<a href="#" id="deleteLabWorkItem${labWorkIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteLabWorkItemLink"/></span></a>
		</th>
		<td>
			<span class="editTableFieldGroup">
				<label for="labWorkCategory" class="fieldLabel"><fmt:message key="labWorkCategoryLabel"/></label>
				<select name="labWorkResultsItems[${labWorkResultsIndex}].labWorkCategory" id="labWorkCategory${labWorkIndex}">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="labWorkCategory" items="${labWorkCategories}">
						<c:choose>
							<c:when test="${labWorkResultsItems[labWorkResultsIndex].labWorkCategory eq labWorkCategory}">
								<option value="${labWorkCategory.id}" selected="selected"><c:out value="${labWorkCategory.name}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${labWorkCategory.id}"><c:out value="${labWorkCategory.name}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<form:errors path="labWorkResultsItems[${labWorkResultsIndex}].labWorkCategory" cssClass="error"/>
			</span>
		</td>
		<td>
			<span class="editTableFieldGroup">
				<label for="labWorkResultsItems[${labWorkResultsIndex}].resultsLab" class="fieldLabel"><fmt:message key="labLabel"/></label>
				<select name="labWorkResultsItems[${labWorkResultsIndex}].resultsLab" id="labWork[${labWorkIndex}].resultsLab">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="lab" items="${labs}">
						<c:choose>
							<c:when test="${labWorkResultsItems[labWorkResultsIndex].resultsLab eq lab}">
								<option value="${lab.id}" selected="selected"><c:out value="${lab.name}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${lab.id}"><c:out value="${lab.name}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<form:errors path="labWorkResultsItems[${labWorkResultsIndex}].resultsLab" cssClass="error"/>
			</span>
		</td>
		<td>
			<span class="editTableFieldGroup">
				<fmt:formatDate value="${labWorkResultsItems[labWorkResultsIndex].resultsDate}" type="date" pattern="MM/dd/yyyy" var="formattedResultsDate"/>
				<label for="labWorkResultsItems[${labWorkResultsIndex}].resultsDate" class="fieldLabel"><fmt:message key="resultsDateLabel"/></label>
				<input type="text" class="date" id="labWorkResultsDate${labWorkResultsIndex}" name="labWorkResultsItems[${labWorkResultsIndex}].resultsDate" value="${formattedResultsDate}"/>
				<form:errors path="labWorkResultsItems[${labWorkResultsIndex}].resultsDate" cssClass="error"/>
			</span>
		</td>
		<td>
			<span class="editTableFieldGroup">
				<label for="resultsNotes" class="fieldLabel"><fmt:message key="resultsNotesLabel"/></label>
				<textarea name="labWorkResultsItems[${labWorkResultsIndex}].resultsNotes"><c:out value="${labWorkResultsItems[labWorkResultsIndex].resultsNotes}"/></textarea>
				<form:errors path="labWorkResultsItems[${labWorkResultsIndex}].resultsNotes" cssClass="error"/>
			</span>
		</td>
	</tr>
</fmt:bundle>