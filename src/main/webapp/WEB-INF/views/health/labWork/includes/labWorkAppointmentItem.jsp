<%--
 - Author: Stephen Abson
 - Author: Joel Norris
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<tr id="labWorkAppointmentItem${labWorkIndex}" class="${cssClass}">
		<th class="operations">
			<input type="hidden" name="labWork[${labWorkIndex}].process" id="labWorkProcess${labWorkIndex}" value="${labWorkItems[labWorkIndex].process}"/>
			<input type="hidden" name="labWork[${labWorkIndex}].labWork" id="labWorkId${labWorkIndex}" value="${labWorkItems[labWorkIndex].labWork.id}"/>
			<input type="hidden" name="labWork[${labWorkIndex}].labWorkRequirementRequest" id="labWorkRequirementRequest${labWorkIndex}" 
				value="${labWorkItems[labWorkIndex].labWorkRequirementRequest.id}"/>
			<a href="#" id="deleteLabWorkItem${labWorkIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteLabWorkItemLink"/></span></a>
		</th>
		<td class="threeColumn">
			<span class="editTableFieldGroup">
				<label for="labWorkCategory" class="fieldLabel"><fmt:message key="labWorkCategoryLabel"/></label>
				<select name="labWork[${labWorkIndex}].labWorkCategory" id="labWorkCategory${labWorkIndex}">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="labWorkCategory" items="${labWorkCategories}">
						<c:choose>
							<c:when test="${labWorkItems[labWorkIndex].labWorkCategory eq labWorkCategory}">
								<option value="${labWorkCategory.id}" selected="selected"><c:out value="${labWorkCategory.name}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${labWorkCategory.id}"><c:out value="${labWorkCategory.name}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<form:errors path="labWork[${labWorkIndex}].labWorkCategory" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<fmt:formatDate value="${labWorkItems[labWorkIndex].orderDate}" type="date" pattern="MM/dd/yyyy" var="formattedOrderDate"/>
				<label for="labWork[${labWorkIndex}].orderDate" class="fieldLabel"><fmt:message key="orderDateLabel"/></label>
					<c:choose>
						<c:when test="${empty labWork[labWorkIndex].orderDate && not empty defaultOrderDate}">
							<fmt:formatDate value="${defaultOrderDate}" type="date" pattern="MM/dd/yyyy" var="formattedDefaultOrderDate"/>
							<input type="text" class="date" id="labWorkOrderDate${labWorkIndex}" name="labWork[${labWorkIndex}].orderDate" value="${formattedDefaultOrderDate}"/>
						</c:when>
						<c:otherwise>
							<input type="text" class="date" id="labWorkOrderDate${labWorkIndex}" name="labWork[${labWorkIndex}].orderDate" value="${formattedOrderDate}"/>
						</c:otherwise>
					</c:choose>
				<form:errors path="labWork[${labWorkIndex}].orderDate" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="schedulingNotes" class="fieldLabel"><fmt:message key="schedulingNotesLabel"/></label>
				<textarea name="labWork[${labWorkIndex}].schedulingNotes"><c:out value="${labWorkItems[labWorkIndex].schedulingNotes}"/></textarea>
				<form:errors path="labWork[${labWorkIndex}].schedulingNotes" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="labWork[${labWorkIndex}].byProvider" class="fieldLabel"><fmt:message key="labWorkSampleOrderedByLabel"/></label>
				<select name="labWork[${labWorkIndex}].byProvider" id="labWorkByProvider${labWorkIndex}">
					<jsp:include page="/WEB-INF/views/health/labWork/includes/orderedByOptions.jsp"/>
				</select>
				<form:errors path="labWork[${labWorkIndex}].byProvider" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label class="fieldLabel"><fmt:message key="labWorkSampleRestrictionsShortLabel"/></label>
				<label for="labWork[${labWorkIndex}].nothingPerOral" class="fieldValueLabel"><fmt:message key="nothingPerOralSampleRestriction"/></label>
				<c:choose>
					<c:when test="${labWorkItems[labWorkIndex].nothingPerOral || defaultNothingPerOral}">
						<input id="labWork[${labWorkIndex}].nothingPerOral" class="fieldValue" type="checkbox" value="true" name="labWork[${labWorkIndex}].nothingPerOral" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="labWork[${labWorkIndex}].nothingPerOral" class="fieldValue" type="checkbox" value="true" name="labWork[${labWorkIndex}].nothingPerOral"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWork[${labWorkIndex}].nothingPerOral" cssClass="error"/>
				<label for="labWork[${labWorkIndex}].noLeaky" class="fieldValueLabel"><fmt:message key="noLeakySampleRestrictionLabel"/></label>
				<c:choose>
					<c:when test="${labWorkItems[labWorkIndex].noLeaky || defaultNoLeaky}">
						<input id="labWork[${labWorkIndex}].noLeaky" class="fieldValue" type="checkbox" value="true" name="labWork[${labWorkIndex}].noLeaky" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="labWork[${labWorkIndex}].noLeaky" class="fieldValue" type="checkbox" value="true" name="labWork[${labWorkIndex}].noLeaky"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWork[${labWorkIndex}].noLeaky" cssClass="error"/>
				<label for="labWork[${labWorkIndex}].noMeds" class="fieldValueLabel"><fmt:message key="noMedsSampleRestrictionLabel"/></label>
				<c:choose>
					<c:when test="${labWorkItems[labWorkIndex].noMeds || defaultNoMeds}">
						<input id="labWork[${labWorkIndex}].noMeds" class="fieldValue" type="checkbox" value="true" name="labWork[${labWorkIndex}].noMeds" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="labWork[${labWorkIndex}].noMeds" class="fieldValue" type="checkbox" value="true" name="labWork[${labWorkIndex}].noMeds"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWork[${labWorkIndex}].noMeds" cssClass="error"/>
			</span>
		</td>
		<td class="threeColumn">
			<span class="editTableFieldGroup">
				<label for="labWork[${labWorkIndex}].sampleLab" class="fieldLabel"><fmt:message key="labLabel"/></label>
				<select name="labWork[${labWorkIndex}].sampleLab" id="labWorkSampleLab${labWorkIndex}">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="lab" items="${labs}">
						<c:choose>
							<c:when test="${labWorkItems[labWorkIndex].sampleLab eq lab}">
								<option value="${lab.id}" selected="selected"><c:out value="${lab.name}"/></option>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${defaultSampleLab eq lab}">
										<option value="${lab.id}" selected="selected"><c:out value="${lab.name}"/></option>
									</c:when>
									<c:otherwise>
										<option value="${lab.id}"><c:out value="${lab.name}"/></option>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<form:errors path="labWork[${labWorkIndex}].sampleLab" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<fmt:formatDate value="${labWorkItems[labWorkIndex].date}" type="date" pattern="MM/dd/yyyy" var="formattedSampleDate"/>
				<label for="labWork[${labWorkIndex}].date" class="fieldLabel"><fmt:message key="labWorkSampleDateLabel"/></label>
				<c:choose>
						<c:when test="${empty labWork[labWorkIndex].date && not empty defaultSampleDate}">
							<fmt:formatDate value="${defaultSampleDate}" type="date" pattern="MM/dd/yyyy" var="formattedDefaultSampleDate"/>
							<input type="text" class="date" id="labWorkSampleDate${labWorkIndex}" name="labWork[${labWorkIndex}].date" value="${formattedDefaultSampleDate}"/>
						</c:when>
						<c:otherwise>
							<input type="text" class="date" id="labWorkSampleDate${labWorkIndex}" name="labWork[${labWorkIndex}].date" value="${formattedSampleDate}"/>
						</c:otherwise>
					</c:choose>
				<form:errors path="labWork[${labWorkIndex}].date" cssClass="error"/>					
			</span>
			<span class="editTableFieldGroup">
				<label for="sampleNotes" class="fieldLabel"><fmt:message key="sampleNotesLabel"/></label>
				<textarea name="labWork[${labWorkIndex}].sampleNotes"><c:out value="${labWorkItems[labWorkIndex].sampleNotes}"/></textarea>
				<form:errors path="labWork[${labWorkIndex}].sampleNotes" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="sampleTaken" class="fieldLabel"><fmt:message key="sampleTakenLabel"/></label>
				<c:choose>
					<c:when test="${labWorkItems[labWorkIndex].sampleTaken}">
						<input id="labWork[${labWorkIndex}].sampleTaken" class="fieldValue" type="checkbox" value="true" name="labWork[${labWorkIndex}].sampleTaken" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="labWork[${labWorkIndex}].sampleTaken" class="fieldValue" type="checkbox" value="true" name="labWork[${labWorkIndex}].sampleTaken"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWork[${labWorkIndex}].sampleTaken" cssClass="error"/>
			</span>
		</td>
		<td class="threeColumn">
			<span class="editTableFieldGroup">
				<label for="labWork[${labWorkIndex}].resultsLab" class="fieldLabel"><fmt:message key="labLabel"/></label>
				<select name="labWork[${labWorkIndex}].resultsLab" id="labWork[${labWorkIndex}].resultsLab">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="lab" items="${labs}">
						<c:choose>
							<c:when test="${labWorkItems[labWorkIndex].resultsLab eq lab}">
								<option value="${lab.id}" selected="selected"><c:out value="${lab.name}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${lab.id}"><c:out value="${lab.name}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<form:errors path="labWork[${labWorkIndex}].resultsLab" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<fmt:formatDate value="${labWorkItems[labWorkIndex].resultsDate}" type="date" pattern="MM/dd/yyyy" var="formattedResultsDate"/>
				<label for="labWork[${labWorkIndex}].resultsDate" class="fieldLabel"><fmt:message key="resultsDateLabel"/></label>
				<input type="text" class="date" id="labWorkResultsDate${labWorkIndex}" name="labWork[${labWorkIndex}].resultsDate" value="${formattedResultsDate}"/>
				<form:errors path="labWork[${labWorkIndex}].resultsDate" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="resultsNotes" class="fieldLabel"><fmt:message key="resultsNotesLabel"/></label>
				<textarea name="labWork[${labWorkIndex}].resultsNotes"><c:out value="${labWorkItems[labWorkIndex].resultsNotes}"/></textarea>
				<form:errors path="labWork[${labWorkIndex}].resultsNotes" cssClass="error"/>
			</span>
		</td>
	</tr>
</fmt:bundle>