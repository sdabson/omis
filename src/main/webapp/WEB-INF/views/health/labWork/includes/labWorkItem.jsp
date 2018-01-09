<%--
 - Author: Joel Norris
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<tr id="labWorkItem${labWorkItemIndex}" class="${cssClass}">
		<th class="operations">
			<input type="hidden" name="labWorkItems[${labWorkItemIndex}].process" id="labWorkItemProcess${labWorkItemIndex}" value="${labWorkItems[labWorkItemIndex].process}"/>
			<a href="#" id="deleteLabWorkItem${labWorkItemIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteLabWorkItemLink"/></span></a>
		</th>
		<td class="threeColumn">
			<span class="editTableFieldGroup">
				<label for="labWorkCategory" class="fieldLabel"><fmt:message key="labWorkCategoryLabel"/></label>
				<select name="labWorkItems[${labWorkItemIndex}].labWorkCategory" id="labWorkCategory${labWorkItemIndex}">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="labWorkCategory" items="${labWorkCategories}">
						<c:choose>
							<c:when test="${labWorkItems[labWorkItemIndex].labWorkCategory eq labWorkCategory}">
								<option value="${labWorkCategory.id}" selected="selected"><c:out value="${labWorkCategory.name}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${labWorkCategory.id}"><c:out value="${labWorkCategory.name}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<form:errors path="labWorkItems[${labWorkItemIndex}].labWorkCategory" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<fmt:formatDate value="${labWorkItems[labWorkItemIndex].orderDate}" type="date" pattern="MM/dd/yyyy" var="formattedOrderDate"/>
				<label for="labWorkItems[${labWorkItemIndex}].orderDate" class="fieldLabel"><fmt:message key="orderDateLabel"/></label>
					<c:choose>
						<c:when test="${empty labWorkItems[labWorkItemIndex].orderDate && not empty defaultOrderDate}">
							<fmt:formatDate value="${defaultOrderDate}" type="date" pattern="MM/dd/yyyy" var="formattedDefaultOrderDate"/>
							<input type="text" class="date" id="labWorkOrderDate${labWorkItemIndex}" name="labWorkItems[${labWorkItemIndex}].orderDate" value="${formattedDefaultOrderDate}"/>
						</c:when>
						<c:otherwise>
							<input type="text" class="date" id="labWorkOrderDate${labWorkItemIndex}" name="labWorkItems[${labWorkItemIndex}].orderDate" value="${formattedOrderDate}"/>
						</c:otherwise>
					</c:choose>
				<form:errors path="labWorkItems[${labWorkItemIndex}].orderDate" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="schedulingNotes" class="fieldLabel"><fmt:message key="schedulingNotesLabel"/></label>
				<textarea name="labWorkItems[${labWorkItemIndex}].schedulingNotes"><c:out value="${labWorkItems[labWorkItemIndex].schedulingNotes}"/></textarea>
				<form:errors path="labWorkItems[${labWorkItemIndex}].schedulingNotes" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="labWorkItems[${labWorkItemIndex}].byProvider" class="fieldLabel"><fmt:message key="labWorkSampleOrderedByLabel"/></label>
				<c:set var="provider" value="${labWorkItems[labWorkItemIndex].byProvider}" scope="request"/>
				<select name="labWorkItems[${labWorkItemIndex}].byProvider" id="labWorkByProvider${labWorkItemIndex}">
					<jsp:include page="/WEB-INF/views/health/labWork/includes/orderedByOptions.jsp"/>
				</select>
				<form:errors path="labWorkItems[${labWorkItemIndex}].byProvider" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label class="fieldLabel"><fmt:message key="labWorkSampleRestrictionsShortLabel"/></label>
				<label for="labWorkItems[${labWorkItemIndex}].nothingPerOral" class="fieldValueLabel"><fmt:message key="nothingPerOralSampleRestriction"/></label>
				<c:choose>
					<c:when test="${labWorkItems[labWorkItemIndex].nothingPerOral || defaultNothingPerOral}">
						<input id="labWorkItems[${labWorkItemIndex}].nothingPerOral" class="fieldValue" type="checkbox" value="true" name="labWorkItems[${labWorkItemIndex}].nothingPerOral" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="labWorkItems[${labWorkItemIndex}].nothingPerOral" class="fieldValue" type="checkbox" value="true" name="labWorkItems[${labWorkItemIndex}].nothingPerOral"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkItems[${labWorkItemIndex}].nothingPerOral" cssClass="error"/>
				<label for="labWorkItems[${labWorkItemIndex}].noLeaky" class="fieldValueLabel"><fmt:message key="noLeakySampleRestrictionLabel"/></label>
				<c:choose>
					<c:when test="${labWorkItems[labWorkItemIndex].noLeaky || defaultNoLeaky}">
						<input id="labWorkItems[${labWorkItemIndex}].noLeaky" class="fieldValue" type="checkbox" value="true" name="labWorkItems[${labWorkItemIndex}].noLeaky" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="labWorkItems[${labWorkItemIndex}].noLeaky" class="fieldValue" type="checkbox" value="true" name="labWorkItems[${labWorkItemIndex}].noLeaky"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkItems[${labWorkItemIndex}].noLeaky" cssClass="error"/>
				<label for="labWorkItems[${labWorkItemIndex}].noMeds" class="fieldValueLabel"><fmt:message key="noMedsSampleRestrictionLabel"/></label>
				<c:choose>
					<c:when test="${labWorkItems[labWorkItemIndex].noMeds || defaultNoMeds}">
						<input id="labWorkItems[${labWorkItemIndex}].noMeds" class="fieldValue" type="checkbox" value="true" name="labWorkItems[${labWorkItemIndex}].noMeds" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="labWorkItems[${labWorkItemIndex}].noMeds" class="fieldValue" type="checkbox" value="true" name="labWorkItems[${labWorkItemIndex}].noMeds"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkItems[${labWorkItemIndex}].noMeds" cssClass="error"/>
			</span>
		</td>
		<td class="threeColumn">
			<span class="editTableFieldGroup">
				<label for="labWorkItems[${labWorkItemIndex}].sampleLab" class="fieldLabel"><fmt:message key="labLabel"/></label>
				<select name="labWorkItems[${labWorkItemIndex}].sampleLab" id="labWorkSampleLab${labWorkItemIndex}">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="lab" items="${labs}">
						<c:choose>
							<c:when test="${labWorkItems[labWorkItemIndex].sampleLab eq lab}">
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
				<form:errors path="labWorkItems[${labWorkItemIndex}].sampleLab" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<fmt:formatDate value="${labWorkItems[labWorkItemIndex].sampleDate}" type="date" pattern="MM/dd/yyyy" var="formattedSampleDate"/>
				<label for="labWorkItems[${labWorkItemIndex}].sampleDate" class="fieldLabel"><fmt:message key="labWorkSampleDateLabel"/></label>
				<c:choose>
						<c:when test="${empty labWorkItems[labWorkItemIndex].sampleDate && not empty defaultSampleDate}">
							<fmt:formatDate value="${defaultSampleDate}" type="date" pattern="MM/dd/yyyy" var="formattedDefaultSampleDate"/>
							<input type="text" class="date" id="labWorkSampleDate${labWorkItemIndex}" name="labWorkItems[${labWorkItemIndex}].sampleDate" value="${formattedDefaultSampleDate}"/>
						</c:when>
						<c:otherwise>
							<input type="text" class="date" id="labWorkSampleDate${labWorkItemIndex}" name="labWorkItems[${labWorkItemIndex}].sampleDate" value="${formattedSampleDate}"/>
						</c:otherwise>
					</c:choose>
				<form:errors path="labWorkItems[${labWorkItemIndex}].sampleDate" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="sampleNotes" class="fieldLabel"><fmt:message key="sampleNotesLabel"/></label>
				<textarea name="labWorkItems[${labWorkItemIndex}].sampleNotes"><c:out value="${labWorkItems[labWorkItemIndex].sampleNotes}"/></textarea>
				<form:errors path="labWorkItems[${labWorkItemIndex}].sampleNotes" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="sampleTaken" class="fieldLabel"><fmt:message key="sampleTakenLabel"/></label>
				<c:choose>
					<c:when test="${labWorkItems[labWorkItemIndex].sampleTaken}">
						<input id="labWorkItems[${labWorkItemIndex}].sampleTaken" class="fieldValue" type="checkbox" value="true" name="labWorkItems[${labWorkItemIndex}].sampleTaken" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="labWorkItems[${labWorkItemIndex}].sampleTaken" class="fieldValue" type="checkbox" value="true" name="labWorkItems[${labWorkItemIndex}].sampleTaken"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkItems[${labWorkItemIndex}].sampleTaken" cssClass="error"/>
			</span>
		</td>
		<td class="threeColumn">
			<span class="editTableFieldGroup">
				<label for="labWorkItems[${labWorkItemIndex}].resultsLab" class="fieldLabel"><fmt:message key="labLabel"/></label>
				<select name="labWorkItems[${labWorkItemIndex}].resultsLab" id="labWork[${labWorkItemIndex}].resultsLab">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="lab" items="${labs}">
						<c:choose>
							<c:when test="${labWorkItems[labWorkItemIndex].resultsLab eq lab}">
								<option value="${lab.id}" selected="selected"><c:out value="${lab.name}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${lab.id}"><c:out value="${lab.name}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<form:errors path="labWorkItems[${labWorkItemIndex}].resultsLab" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<fmt:formatDate value="${labWorkItems[labWorkItemIndex].resultsDate}" type="date" pattern="MM/dd/yyyy" var="formattedResultsDate"/>
				<label for="labWorkItems[${labWorkItemIndex}].resultsDate" class="fieldLabel"><fmt:message key="resultsDateLabel"/></label>
				<input type="text" class="date" id="labWorkResultsDate${labWorkItemIndex}" name="labWorkItems[${labWorkItemIndex}].resultsDate" value="${formattedResultsDate}"/>
				<form:errors path="labWorkItems[${labWorkItemIndex}].resultsDate" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="resultsNotes" class="fieldLabel"><fmt:message key="resultsNotesLabel"/></label>
				<textarea name="labWorkItems[${labWorkItemIndex}].resultsNotes"><c:out value="${labWorkItems[labWorkItemIndex].resultsNotes}"/></textarea>
				<form:errors path="labWorkItems[${labWorkItemIndex}].resultsNotes" cssClass="error"/>
			</span>
		</td>
	</tr>
</fmt:bundle>