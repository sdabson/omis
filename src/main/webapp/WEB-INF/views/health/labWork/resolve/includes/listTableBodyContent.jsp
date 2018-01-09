<%--
 - Author: Yidong Li
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Nov 25, 2014)
 - Since: OMIS 3.0
 --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.health.msgs.health">
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
	<table id="labWork" class="editTable">
		<thead>
			<tr>
			<th class="operations"/>
			<th><fmt:message key="offenderLabel"/></th>
			<th><fmt:message key="typeLabel"/></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="takenLabel"/></th>
			<th><fmt:message key="sampleNotesLabel"/></th>
			<th><fmt:message key="resultsLabLabel"/></th>
			<th><fmt:message key="resultsDateLabel"/></th>
			<th><fmt:message key="resultNotesLabel"/></th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${resolveLabWorkForm!=null}">
				<c:forEach var="resolveLabWorkItem" items="${resolveLabWorkForm.labWorkItems}" varStatus="status">
					<c:set var="resolveLabWorkItemIndex" value="${status.index}" scope="request"/>
					<tr id="labWorkItems[${resolveLabWorkItemIndex}]">
						<td>
							<sec:authorize access="hasRole('LAB_WORK_REMOVE') or hasRole('HEALTH_ADMIN') or hasRole('ADMIN')">
								<a id="resolveLabWorkItems[${resolveLabWorkItemIndex}].removeLink" class="removeLink" title="<fmt:message key='removeLink' bundle='${commonBundle}'/>">
								<span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span>
								
								</a>
								<input type="hidden" id="resolveLabWorkForm.labWorkItems[${resolveLabWorkItemIndex}].operation" name="labWorkItems[${resolveLabWorkItemIndex}].operation" value="${resolveLabWorkItem.operation}"/>
								<input type="hidden" id="resolveLabWorkForm.labWorkItems[${resolveLabWorkItemIndex}].labWork" name="labWorkItems[${resolveLabWorkItemIndex}].labWork" value="${resolveLabWorkItem.labWork.id}"/>
							</sec:authorize>		
						</td>
						<td><c:out value="${resolveLabWorkItem.labWork.offenderAppointmentAssociation.offender.name.firstName}"/> <c:out value="${resolveLabWorkItem.labWork.offenderAppointmentAssociation.offender.name.lastName}"/></td>
						 
						<td><c:out value="${resolveLabWorkItem.labWork.labWorkCategory.name}" /></td>
						
						<td><fmt:formatDate value="${resolveLabWorkItem.labWork.offenderAppointmentAssociation.appointment.date}" pattern="MM/dd/yyyy" /></td>
						<td>
							<c:choose> 
				            	<c:when test="${resolveLabWorkItem.taken==true}">
				            		<input id="resolveLabWorkItems[${resolveLabWorkItemIndex}].sampleTaken"  type="checkbox" name="labWorkItems[${resolveLabWorkItemIndex}].taken" value="true" checked="checked"/>
				            	</c:when>
				                <c:otherwise>
				                	<input id="resolveLabWorkItems[${resolveLabWorkItemIndex}].sampleTaken" type="checkbox" name="labWorkItems[${resolveLabWorkItemIndex}].taken" value="true"/>
				                </c:otherwise>
				            </c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${not resolveLabWorkItem.taken}">
							<textarea id="labWorkItems[${resolveLabWorkItemIndex}].sampleNotes" rows="2" class="fieldValue" name="labWorkItems[${resolveLabWorkItemIndex}].sampleNotes" disabled="disabled" ><c:out value="${resolveLabWorkItem.sampleNotes}"/></textarea>
							</c:when>
							<c:otherwise>
							<textarea id="labWorkItems[${resolveLabWorkItemIndex}].sampleNotes" rows="2" class="fieldValue" name="labWorkItems[${resolveLabWorkItemIndex}].sampleNotes"><c:out value="${resolveLabWorkItem.sampleNotes}"/></textarea>
							</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${not resolveLabWorkItem.taken}">
							<form:select path="labWorkItems[${resolveLabWorkItemIndex}].resultsLab" id="labWorkItems[${resolveLabWorkItemIndex}].resultsLab" disabled="true">
								<form:option value="" ><fmt:message key="nullLabel" bundle="${commonBundle}" /></form:option>
								<form:options items="${resultsLab}" itemValue="id" itemLabel="name"/>
							</form:select>		
							</c:when>
							<c:otherwise>
							<form:select path="labWorkItems[${resolveLabWorkItemIndex}].resultsLab" id="labWorkItems[${resolveLabWorkItemIndex}].resultsLab">
								<form:option value="" ><fmt:message key="nullLabel" bundle="${commonBundle}" /></form:option>
								<form:options items="${resultsLab}" itemValue="id" itemLabel="name" />
							</form:select>
							</c:otherwise>
							</c:choose>
						</td>
						<td>
							<input id="resolveLabWorkItems[${resolveLabWorkItemIndex}].resultsDate" name="labWorkItems[${resolveLabWorkItemIndex}].resultsDate" class="date" type="text"  value="<fmt:formatDate value="${resolveLabWorkItem.resultsDate}" pattern="MM/dd/yyyy"/>" <c:if test="${not resolveLabWorkItem.taken}">disabled="disabled"</c:if>/>
							<form:errors cssClass="error" path="labWorkItems[${resolveLabWorkItemIndex}].resultsDate"/>
						</td>
						<td>
							<c:choose>
							<c:when test="${not resolveLabWorkItem.taken}">
							<textarea id="resolveLabWorkItems[${resolveLabWorkItemIndex}].resultNotes" rows="2" class="fieldValue" name="labWorkItems[${resolveLabWorkItemIndex}].resultNotes" disabled="disabled"><c:out value="${resolveLabWorkItem.resultNotes}"/></textarea>
							</c:when>
							<c:otherwise>
							<textarea id="resolveLabWorkItems[${resolveLabWorkItemIndex}].resultNotes" rows="2" class="fieldValue" name="labWorkItems[${resolveLabWorkItemIndex}].resultNotes"><c:out value="${resolveLabWorkItem.resultNotes}"/></textarea>
							</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		</tbody>
	</table>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle="${commonBundle}"/>"/>
	</p>
</fmt:bundle>

