<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<form:form commandName="healthRequestForm" class="editForm">
		<fieldset>
			<legend><fmt:message key="healthRequestLabel" bundle="${healthBundle}"/></legend>
			<c:if test="${not empty facility}">
				<span class="fieldGroup">
					<label for="facilityName" class="fieldLabel"><fmt:message key="healthRequestFacilityLabel" bundle="${healthBundle}"/></label>
					<input id="facilityName" name="facilityName" type="text" readonly="readonly" value="<c:out value='${facility.name}'/>" class="small"/>
				</span>
			</c:if>
			<c:if test="${not empty request}">
				<span class="fieldGroup">
					<label for="date" class="fieldLabel"><fmt:message key="healthRequestDateLabel" bundle="${healthBundle}"/></label>
					<input name="date" type="text" readonly="readonly" value="<fmt:formatDate value='${request.date}' pattern='MM/dd/yyyy'/>" class="date"/>
				</span>
			</c:if>
			<form:hidden path="offenderRequired"/>
			<c:if test="${healthRequestForm.offenderRequired}">
				<span class="fieldGroup">
					<form:label path="offender" class="fieldLabel"><fmt:message key="offenderLabel" bundle="${healthBundle}"/></form:label>
					<c:choose>
						<c:when test="${not empty scheduleForm.offender}">
							<input id="offenderName" type="text" value="<c:out value='${scheduleForm.offender.name.firstName}'/> <c:out value='${scheduleForm.offender.name.lastName}'/>"/>
						</c:when>
						<c:otherwise>
							<input id="offenderName" type="text"/>
						</c:otherwise>
					</c:choose>
					<form:input id="offender" path="offender" type="hidden"/>
					<form:errors cssClass="error" path="offender"/>
				</span>
			</c:if>
			<span class="fieldGroup">
				<form:label path="category" class="fieldLabel">
					<fmt:message key="healthRequestCategoryLabel" bundle="${healthBundle}"/></form:label>
				<form:select path="category">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<c:forEach var="category" items="${categories}">
						<c:choose>
							<c:when test="${healthRequestForm.category eq category}">
								<form:option value="${category.name}" selected="true"><fmt:message key="healthRequestCategoryLabel.${category.name}" bundle="${healthBundle}"/></form:option>
							</c:when>
							<c:otherwise>
								<form:option value="${category.name}"><fmt:message key="healthRequestCategoryLabel.${category.name}" bundle="${healthBundle}"/></form:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
				<form:errors path="category" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="asap" class="fieldLabel">
					<fmt:message key="healthRequestAsapLabel" bundle="${healthBundle}"/></form:label>
				<form:checkbox path="asap" class="fieldLabelValue"/>
				<form:errors path="asap" cssClass="error"/>
			</span>
			<sec:authorize access="hasRole('DISABLED')">
				<span class="fieldGroup">
					<form:label path="labsRequired" class="fieldLabel">
						<fmt:message key="healthRequestLabsRequiredLabel" bundle="${healthBundle}"/></form:label>
					<form:checkbox path="labsRequired" class="fieldLabelValue"/>
					<form:errors path="labsRequired" cssClass="error"/>
				</span>
			</sec:authorize>
			<span class="fieldGroup">
				<form:label path="providerLevel" class="fieldLabel">
					<fmt:message key="healthRequestProviderLevelLabel" bundle="${healthBundle}"/>
				</form:label>
				<form:select path="providerLevel">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options itemLabel="name" itemValue="id" items="${providerLevels}"/>
				</form:select>
				<form:errors cssClass="error" path="providerLevel"/>
			</span>
			<span class="fieldGroup">
				<form:label path="notes" class="fieldLabel">
					<fmt:message key="healthRequestNotesLabel" bundle="${healthBundle}"/>
				</form:label>
				<form:textarea path="notes" />
				<form:errors cssClass="error" path="notes"/>
			</span>
		</fieldset>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
</form:form>