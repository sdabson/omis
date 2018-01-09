<%--
 - Author: Stephen Abson
 - Author: Joel Norris
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<tr id="labWorkRow${labWorkIndex}">
	<td>
		<input type="hidden" name="labWork[${labWorkIndex}].process" id="labWorkProcess${labWorkIndex}" value="true"/>
		<input type="hidden" name="labWork[${labWorkIndex}].labWork" id="labWorkId${labWorkIndex}" value="${labWorkItems[labWorkIndex].labWork.id}"/>
		<a href="#" id="deleteLabWorkItem${labWorkIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteLabWorkItemLink"/></span></a>
	</td>
	<td>
		<fmt:formatDate pattern="MM/dd/yyyy" value="${labWorkItems[labWorkIndex].date}" type="date" var="labWorkDate"/>
		<input name="labWork[${labWorkIndex}].date" class="date" id="labWorkDate${labWorkIndex}" type="text" value="${labWorkDate}"/>
		<form:errors path="labWork[${labWorkIndex}].date" cssClass="error"/>
	</td>
	<td>
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
	</td>
	<td>
		<select name="labWork[${labWorkIndex}].sampleLab" id="labWork[${labWorkIndex}].sampleLab">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="lab" items="${labs}">
				<c:choose>
					<c:when test="${labWorkItems[labWorkIndex].sampleLab eq lab}">
						<option value="${lab.id}" selected="selected"><c:out value="${lab.name}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${lab.id}"><c:out value="${lab.name}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="labWork[${labWorkIndex}].sampleLab" cssClass="error"/>
	</td>
	<td>
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
	</td>
	<td>
		<textarea name="labWork[${labWorkIndex}].schedulingNotes" id="labWorkNotes${labWorkIndex}"><c:out value="${labWorkItems[labWorkIndex].schedulingNotes}"/></textarea>
	</td>
</tr>