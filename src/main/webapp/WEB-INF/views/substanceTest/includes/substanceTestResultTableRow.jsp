<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.substance.msgs.substance">
	<tr id="substanceTestResultRow${substanceTestResultIndex}" class="substanceTestResultRow">
		<td>
			<input type="hidden" name="resultItems[${substanceTestResultIndex}].process" value="true"/>
			<a href="#" id="deleteSubstanceTestResult${substanceTestResultIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteSubstanceTestResultLink"/></span></a>
		</td>
		<td>
			<select name="resultItems[${substanceTestResultIndex}].substance" id="resultItems[${substanceTestResultIndex}].substance">
			<option value="">...</option>
				<c:forEach var="substance" items="${substances}">
					<c:choose>
						<c:when test="${not empty substanceTestForm.resultItems[substanceTestResultIndex].substance and substanceTestForm.resultItems[substanceTestResultIndex].substance eq substance}">
							<option value="${substance.id}" selected="selected"><c:out value="${substance.name}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${substance.id}"><c:out value="${substance.name}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<form:errors path="resultItems[${substanceTestResultIndex}].substance" cssClass="error"/>
		</td>
		<td>
			<select name="resultItems[${substanceTestResultIndex}].substanceTestResultValue" id="resultItems[${substanceTestResultIndex}].substanceTestResultValue">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach var="result" items="${results}">
					<c:choose>
						<c:when test="${not empty substanceTestForm.resultItems[substanceTestResultIndex].substanceTestResultValue and substanceTestForm.resultItems[substanceTestResultIndex].substanceTestResultValue eq result or result eq substanceTestForm.resultItems[substanceTestResultIndex].defaultResultValue}">
							<option value="${result.id}" selected="selected"><c:out value="${result.name}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${result.id}"><c:out value="${result.name}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<form:errors path="resultItems[${substanceTestResultIndex}].substanceTestResultValue" cssClass="error"/>
		</td>
		<td>	
			<fmt:message key="admissionLabel"/>
			<c:choose>
				<c:when test="${substanceTestForm.resultItems[substanceTestResultIndex].admit}">
					<input type="checkbox" name="resultItems[${substanceTestResultIndex}].admit" id="admit${substanceTestResultIndex}" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input type="checkbox" name="resultItems[${substanceTestResultIndex}].admit" id="admit${substanceTestResultIndex}"/>
				</c:otherwise>
			</c:choose>
			<input type="hidden" value="on" name="_resultItems[${substanceTestResultIndex}].admit">
			<form:errors path="resultItems[${substanceTestResultIndex}].admit" cssClass="error"/>
		</td>
		<td class="admitArea" id="admitArea${substanceTestResultIndex}">
				<c:choose>
					<c:when test="${substanceTestForm.resultItems[substanceTestResultIndex].admit}">
						<jsp:include page="admitBeforeTest.jsp"/>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
		</td>
	</tr>
</fmt:bundle>