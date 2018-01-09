<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.substance.msgs.substance">
	<tr id="labResultRow${labResultIndex}" class="labResultRow">
		<td>
			<input type="hidden" name="labResultItems[${labResultIndex}].process" value="true"/>
			<a href="#" id="deleteLabResult${labResultIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteSubstanceTestResultLink"/></span></a>
		</td>
		<td>
			<select name="labResultItems[${labResultIndex}].substance" id="labResultItems[${labResultIndex}].substance">
			<option value="">...</option>
				<c:forEach var="substance" items="${substances}">
					<c:choose>
						<c:when test="${not empty substanceTestForm.labResultItems[labResultIndex].substance and substanceTestForm.labResultItems[labResultIndex].substance eq substance}">
							<option value="${substance.id}" selected="selected"><c:out value="${substance.name}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${substance.id}"><c:out value="${substance.name}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<form:errors path="labResultItems[${labResultIndex}].substance" cssClass="error"/>
		</td>
		<td>
			<select name="labResultItems[${labResultIndex}].substanceTestResultValue" id="resultItems[${labResultIndex}].substanceTestResultValue">
			<option value="">...</option>
				<c:forEach var="result" items="${results}">
					<c:choose>
						<c:when test="${not empty substanceTestForm.labResultItems[labResultIndex].substanceTestResultValue and substanceTestForm.labResultItems[labResultIndex].substanceTestResultValue eq result}">
							<option value="${result.id}" selected="selected"><c:out value="${result.name}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${result.id}"><c:out value="${result.name}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<form:errors path="labResultItems[${labResultIndex}].substanceTestResultValue" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>