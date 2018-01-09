<%--
 - Author: Joel Norris
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<tr id="labWorkAssessmentItem${labWorkAssessmentItemIndex}" class="${cssClass}">
		<th class="operations">
			<input type="hidden" name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].process" id="labWorkAssessmentItemProcess${labWorkAssessmentItemIndex}" value="${labWorkAssessmentItems[labWorkAssessmentItemIndex].process}"/>
			<input type="hidden" name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].labWork" id="labWorkId${labWorkAssessmentItemIndex}" value="${labWorkAssessmentItems[labWorkAssessmentItemIndex].labWork.id}"/>
			<a href="#" id="deleteLabWorkAssessmentItem${labWorkAssessmentItemIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteLabWorkItemLink"/></span></a>
		</th>
		<td class="threeColumn">
			<span class="editTableFieldGroup">
				<label for="labWorkCategory" class="fieldLabel"><fmt:message key="labWorkCategoryLabel"/></label>
				<input type="text" value="${labWorkAssessmentItems[labWorkAssessmentItemIndex].labWorkCategory.name}" name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].labWorkCategory" disabled="disabled"/>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].labWorkCategory" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<fmt:formatDate value="${labWorkAssessmentItems[labWorkAssessmentItemIndex].orderDate}" type="date" pattern="MM/dd/yyyy" var="formattedOrderDate"/>
				<label for="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].orderDate" class="fieldLabel"><fmt:message key="orderDateLabel"/></label>
					<input type="text" class="date" id="labWorkOrderDate${labWorkAssessmentItemIndex}" name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].orderDate" value="${formattedOrderDate}" disabled="disabled"/>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].orderDate" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="schedulingNotes" class="fieldLabel"><fmt:message key="schedulingNotesLabel"/></label>
				<textarea name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].schedulingNotes" disabled><c:out value="${labWorkAssessmentItems[labWorkAssessmentItemIndex].schedulingNotes}"/></textarea>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].schedulingNotes" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].byProvider" class="fieldLabel"><fmt:message key="labWorkSampleOrderedByLabel"/></label>
				<input type="text" name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].byProvider" id="labWorkByProvider${labWorkAssessmentItemIndex}" value="${labWorkAssessmentItems[labWorkAssessmentItemIndex].byProvider.provider.name.lastName}, ${labWorkAssessmentItems[labWorkAssessmentItemIndex].byProvider.provider.name.firstName}" disabled="disabled" />
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].byProvider" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label class="fieldLabel"><fmt:message key="labWorkSampleRestrictionsShortLabel"/></label>
				<label for="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].nothingPerOral" class="fieldValueLabel"><fmt:message key="nothingPerOralSampleRestriction"/></label>
				<c:choose>
					<c:when test="${labWorkAssessmentItems[labWorkAssessmentItemIndex].nothingPerOral}">
						<input id="nothingPerOral${labWorkAssessmentItemIndex}" class="fieldValue" type="checkbox" value="true" name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].nothingPerOral" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="nothingPerOral${labWorkAssessmentItemIndex}" class="fieldValue" type="checkbox" value="true" name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].nothingPerOral"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].nothingPerOral" cssClass="error"/>
				<label for="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].noLeaky" class="fieldValueLabel"><fmt:message key="noLeakySampleRestrictionLabel"/></label>
				<c:choose>
					<c:when test="${labWorkAssessmentItems[labWorkAssessmentItemIndex].noLeaky}">
						<input id="noLeaky${labWorkAssessmentItemIndex}" class="fieldValue" type="checkbox" value="true" name="labWorkAssessmentItems[${labWorkIndex}].noLeaky" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="noLeaky${labWorkAssessmentItemIndex}" class="fieldValue" type="checkbox" value="true" name="labWorkAssessmentItems[${labWorkIndex}].noLeaky"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].noLeaky" cssClass="error"/>
				<label for="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].noMeds" class="fieldValueLabel"><fmt:message key="noMedsSampleRestrictionLabel"/></label>
				<c:choose>
					<c:when test="${labWorkAssessmentItems[labWorkAssessmentItemIndex].noMeds}">
						<input id="noMeds${labWorkAssessmentItemIndex}" class="fieldValue" type="checkbox" value="true" name="labWorkAssessmentItems[${labWorkIndex}].noMeds" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="noMeds${labWorkAssessmentItemIndex}" class="fieldValue" type="checkbox" value="true" name="labWorkAssessmentItems[${labWorkIndex}].noMeds"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].noMeds" cssClass="error"/>
			</span>
		</td>
		<td class="threeColumn">
			<span class="editTableFieldGroup">
				<label for="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].sampleLab" class="fieldLabel"><fmt:message key="labLabel"/></label>
				<input type="text" name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].sampleLab" id="labWorkSampleLab${labWorkAssessmentItemIndex}" value="${labWorkAssessmentItems[labWorkAssessmentItemIndex].sampleLab.name}" disabled="disabled"/>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].sampleLab" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<fmt:formatDate value="${labWorkAssessmentItems[labWorkAssessmentItemIndex].sampleDate}" type="date" pattern="MM/dd/yyyy" var="formattedSampleDate"/>
				<label for="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].sampleDate" class="fieldLabel"><fmt:message key="labWorkSampleDateLabel"/></label>
					<input type="text" class="date" id="labWorkSampleDate${labWorkAssessmentItemIndex}" name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].sampleDate" value="${formattedSampleDate}" disabled="disabled"/>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].sampleDate" cssClass="error"/>					
			</span>
			<span class="editTableFieldGroup">
				<label for="sampleNotes" class="fieldLabel"><fmt:message key="sampleNotesLabel"/></label>
				<textarea name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].sampleNotes" disabled><c:out value="${labWorkItems[labWorkIndex].sampleNotes}"/></textarea>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].sampleNotes" cssClass="error"/>
			</span>
		</td>
		<td class="threeColumn">
			<span class="editTableFieldGroup">
				<label for="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].resultsLab" class="fieldLabel"><fmt:message key="labLabel"/></label>
				<select name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].resultsLab" id="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].resultsLab">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="lab" items="${labs}">
						<c:choose>
							<c:when test="${labWorkAssessmentItems[labWorkAssessmentItemIndex].resultsLab eq lab}">
								<option value="${lab.id}" selected="selected"><c:out value="${lab.name}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${lab.id}"><c:out value="${lab.name}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].resultsLab" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<fmt:formatDate value="${labWorkAssessmentItems[labWorkAssessmentItemIndex].resultsDate}" type="date" pattern="MM/dd/yyyy" var="formattedResultsDate"/>
				<label for="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].resultsDate" class="fieldLabel"><fmt:message key="resultsDateLabel"/></label>
				<input type="text" class="date" id="labWorkResultsDate${labWorkAssessmentItemIndex}" name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].resultsDate" value="${formattedResultsDate}"/>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].resultsDate" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="resultsNotes" class="fieldLabel"><fmt:message key="resultsNotesLabel"/></label>
				<textarea name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].resultsNotes"><c:out value="${labWorkAssessmentItems[labWorkAssessmentItemIndex].resultsNotes}"/></textarea>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].resultsNotes" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="sampleTaken" class="fieldLabel"><fmt:message key="sampleTakenLabel"/></label>
				<c:choose>
					<c:when test="${labWorkAssessmentItems[labWorkAssessmentItemIndex].sampleTaken}">
						<input id="sampleTaken${labWorkAssessmentItemIndex}" class="fieldValue" type="checkbox" value="true" name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].sampleTaken" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="sampleTaken${labWorkAssessmentItemIndex}" class="fieldValue" type="checkbox" value="true" name="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].sampleTaken"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkAssessmentItems[${labWorkAssessmentItemIndex}].sampleTaken" cssClass="error"/>
			</span>
		</td>
	</tr>
</fmt:bundle>