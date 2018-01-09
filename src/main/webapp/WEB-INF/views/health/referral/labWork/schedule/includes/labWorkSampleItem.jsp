<%--
 - Author: Joel Norris
 - Version: 0.1.0 (July 17, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<tr id="labWorkSampleItemRow${labWorkSampleItemIndex}" class="${cssClass}">
		<td class="operations">
			<input type="hidden" name="labWorkSampleItems[${labWorkSampleItemIndex}].labWork" id="labWorkId${labWorkSampleItemIndex}" value="${labWorkSampleItems[labWorkSampleItemIndex].labWork.id}"/>
			<input type="hidden" name="labWorkSampleItems[${labWorkSampleItemIndex}].process" id="labWorkSampleItemProcess${labWorkSampleItemIndex}" value="${labWorkSampleItems[labWorkSampleItemIndex].process}"/>
			<a href="#" id="deleteLabWorkSampleItem${labWorkSampleItemIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteLabWorkItemLink"/></span></a>
		</td>
		<td>
			<span class="editTableFieldGroup">
				<label for="labWorkSampleItems[${labWorkSampleItemIndex}].labWorkCategory" class="fieldLabel"><fmt:message key="labWorkCategoryLabel"/></label>
				<select name="labWorkSampleItems[${labWorkSampleItemIndex}].labWorkCategory" id="labWorkCategory${labWorkSampleItemIndex}">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="labWorkCategory" items="${labWorkCategories}">
						<c:choose>
							<c:when test="${labWorkSampleItems[labWorkSampleItemIndex].labWorkCategory eq labWorkCategory}">
								<option value="${labWorkCategory.id}" selected="selected"><c:out value="${labWorkCategory.name}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${labWorkCategory.id}"><c:out value="${labWorkCategory.name}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<form:errors path="labWorkSampleItems[${labWorkSampleItemIndex}].labWorkCategory" cssClass="error"/>
			</span>
		</td>
		<td>
			<span class="editTableFieldGroup">
				<label for="labWorkSampleItems[${labWorkSampleItemIndex}].sampleLab" class="fieldLabel"><fmt:message key="labWorkSampleLabLabel"/></label>
				<select name="labWorkSampleItems[${labWorkSampleItemIndex}].sampleLab" id="labWorkSampleItems[${labWorkSampleItemIndex}].sampleLab">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:forEach var="lab" items="${labs}">
						<c:choose>
							<c:when test="${labWorkSampleItems[labWorkSampleItemIndex].sampleLab eq lab || not empty defaultSampleLab && defaultSampleLab eq lab}">
								<option value="${lab.id}" selected="selected"><c:out value="${lab.name}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${lab.id}"><c:out value="${lab.name}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<form:errors path="labWorkSampleItems[${labWorkSampleItemIndex}].sampleLab" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="labWorkSampleItems[${labWorkSampleItemIndex}].sampleDate" class="fieldLabel"><fmt:message key="labWorkSampleDateLabel"/></label>
				<c:choose>
					<c:when test="${not empty labWorkSampleItems[labWorkSampleItemIndex].sampleDate}">
						<fmt:formatDate value="${labWorkSampleItems[labWorkSampleItemIndex].sampleDate}" pattern="MM/dd/yyyy" var="sampleDate"/>
						<input name="labWorkSampleItems[${labWorkSampleItemIndex}].sampleDate" id="labWorkSampleItemSampleDate${labWorkSampleItemIndex}" class="date" value="${sampleDate}"/>
					</c:when>
					<c:when test="${not empty defaultSampleDate}">
						<fmt:formatDate value="${defaultSampleDate}" pattern="MM/dd/yyyy" var="defaultSampleDateSelection"/>
						<input name="labWorkSampleItems[${labWorkSampleItemIndex}].sampleDate" id="labWorkSampleItemSampleDate${labWorkSampleItemIndex}" class="date" value="${defaultSampleDateSelection}"/>
					</c:when>
					<c:otherwise>
						<input name="labWorkSampleItems[${labWorkSampleItemIndex}].sampleDate" id="labWorkSampleItemSampleDate${labWorkSampleItemIndex}"class="date"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkSampleItems[${labWorkSampleItemIndex}].sampleDate" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<label for="sampleTaken" class="fieldLabel"><fmt:message key="sampleTakenLabel"/></label>
				<c:choose>
					<c:when test="${labWorkSampleItems[labWorkSampleItemIndex].sampleTaken}">
						<input id="sampleTaken${labWorkSampleItemIndex}" class="fieldValue" type="checkbox" value="true" name="labWorkSampleItems[${labWorkSampleItemIndex}].sampleTaken" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="sampleTaken${labWorkSampleItemIndex}" class="fieldValue" type="checkbox" value="true" name="labWorkSampleItems[${labWorkSampleItemIndex}].sampleTaken"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkSampleItems[${labWorkSampleItemIndex}].sampleTaken" cssClass="error"/>
			</span>
		</td>
		<td>
			<span class="editTableFieldGroup">
				<label for="labWorkSampleItems[${labWorkSampleItemIndex}].sampleNotes" class="fieldLabel"><fmt:message key="sampleNotesLabel"/></label>
				<textarea name="labWorkSampleItems[${labWorkSampleItemIndex}].sampleNotes"><c:out value="${labWorkSampleItems[labWorkSampleItemIndex].sampleNotes}"/></textarea>
				<form:errors path="labWorkSampleItems[${labWorkSampleItemIndex}].sampleNotes" cssClass="error"/>
			</span>
		</td>
		<td>
			<span class="editTableFieldGroup">
				<label for="labWorkSampleItems[${labWorkSampleItemIndex}].nothingPerOral" class="fieldValueLabel"><fmt:message key="nothingPerOralSampleRestriction"/></label>
				<c:choose>
					<c:when test="${labWorkSampleItems[labWorkSampleItemIndex].nothingPerOral || defaultNothingPerOral}">
						<input id="labWorkSampleItems[${labWorkSampleItemIndex}].nothingPerOral" class="fieldValue" type="checkbox" value="true" name="labWorkSampleItems[${labWorkSampleItemIndex}].nothingPerOral" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="labWorkSampleItems[${labWorkSampleItemIndex}].nothingPerOral" class="fieldValue" type="checkbox" value="true" name="labWorkSampleItems[${labWorkSampleItemIndex}].nothingPerOral"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkSampleItems[${labWorkSampleItemIndex}].nothingPerOral" cssClass="error"/>
				<label for="labWorkSampleItems[${labWorkSampleItemIndex}].noLeaky" class="fieldValueLabel"><fmt:message key="noLeakySampleRestrictionLabel"/></label>
				<c:choose>
					<c:when test="${labWorkSampleItems[labWorkSampleItemIndex].noLeaky || defaultNoLeaky}">
						<input id="labWorkSampleItems[${labWorkSampleItemIndex}].noLeaky" class="fieldValue" type="checkbox" value="true" name="labWorkSampleItems[${labWorkSampleItemIndex}].noLeaky" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="labWorkSampleItems[${labWorkSampleItemIndex}].noLeaky" class="fieldValue" type="checkbox" value="true" name="labWorkSampleItems[${labWorkSampleItemIndex}].noLeaky"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkSampleItems[${labWorkSampleItemIndex}].noLeaky" cssClass="error"/>
				<label for="labWorkSampleItems[${labWorkSampleItemIndex}].noMeds" class="fieldValueLabel"><fmt:message key="noMedsSampleRestrictionLabel"/></label>
				<c:choose>
					<c:when test="${labWorkSampleItems[labWorkSampleItemIndex].noMeds || defaultNoMeds}">
						<input id="labWorkSampleItems[${labWorkSampleItemIndex}].noMeds" class="fieldValue" type="checkbox" value="true" name="labWorkSampleItems[${labWorkSampleItemIndex}].noMeds" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="labWorkSampleItems[${labWorkSampleItemIndex}].noMeds" class="fieldValue" type="checkbox" value="true" name="labWorkSampleItems[${labWorkSampleItemIndex}].noMeds"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkSampleItems[${labWorkSampleItemIndex}].noMeds" cssClass="error"/>
			</span>
		</td>
		<td>
			<span class="editTableFieldGroup">
				<label for="labWorkSampleItems[${labWorkSampleItemIndex}].orderDate" class="fieldLabel"><fmt:message key="orderDateLabel"/></label>
				<c:choose>	
					<c:when test="${not empty labWorkSampleItems[labWorkSampleItemIndex].orderDate}">
						<fmt:formatDate value="${labWorkSampleItems[labWorkSampleItemIndex].orderDate}" pattern="MM/dd/yyyy" var="orderDate"/>
						<input name="labWorkSampleItems[${labWorkSampleItemIndex}].orderDate" id="labWorkSampleItemOrderDate${labWorkSampleItemIndex}" class="date" value="${orderDate}"/>
					</c:when>
					<c:when test="${not empty defaultOrderDate}">
						<fmt:formatDate value="${defaultOrderDate}" pattern="MM/dd/yyyy" var="defaultOrderDateSelection"/>
						<input name="labWorkSampleItems[${labWorkSampleItemIndex}].orderDate" id="labWorkSampleItemOrderDate${labWorkSampleItemIndex}" class="date" value="${defaultOrderDateSelection}"/>
					</c:when>
					<c:otherwise>
						<input name="labWorkSampleItems[${labWorkSampleItemIndex}].orderDate" id="labWorkSampleItemOrderDate${labWorkSampleItemIndex}" class="date"/>
					</c:otherwise>
				</c:choose>
				<form:errors path="labWorkSampleItems[${labWorkSampleItemIndex}].orderDate" cssClass="error"/>
			</span>
			<span class="editTableFieldGroup">
				<c:if test="${not empty labWorkSampleItems[labWorkSampleItemIndex].byProvider}">
					<c:set var="labWorkReferralByProvider" value="${labWorkSampleItems[labWorkSampleItemIndex].byProvider}" scope="request"/>
				</c:if>
				<label for="labWorkSampleItems[${labWorkSampleItemIndex}].byProvider" class="fieldLabel"><fmt:message key="labWorkSampleOrderedByLabel"/></label>
				<select name="labWorkSampleItems[${labWorkSampleItemIndex}].byProvider" id="labWorkSampleItemByProvider${labWorkSampleItemIndex}">
					<jsp:include page="/WEB-INF/views/health/referral/labWork/includes/byProviderOptions.jsp"/>
				</select>
				<form:errors path="labWorkSampleItems[${labWorkSampleItemIndex}].byProvider" cssClass="error"/>
			</span>
		</td>
	</tr>
</fmt:bundle>