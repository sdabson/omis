<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="offenderFlagBundle" basename="omis.offenderflag.msgs.offenderFlag"/>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<form:form commandName="offenderFlagForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="offenderFlagsLabel" bundle="${offenderFlagBundle}"/></legend>
		<c:choose>
			<c:when test="${fn:length(offenderFlagForm.flagItems) eq 0}">
				<p class="message">
				<fmt:message key="noFlagsMessage" bundle="${offenderFlagBundle}"/>
				</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="flagItem" items="${offenderFlagForm.flagItems}" varStatus="status">
				<span class="fieldGroup">
					<label class="fieldLabel"><c:out value="${flagItem.category.name}"/></label>
					<input type="hidden" name="flagItems[${status.index}].category" value="${flagItem.category.id}"/>
					<c:choose>
						<c:when test="${offenderFlagForm.flagItems[status.index].value.name eq 'YES'}">
							<input class="fieldValue" type="radio" id="flagItems[${status.index}].yesValue" name="flagItems[${status.index}].value" value="YES" checked="checked"/>
						</c:when>
						<c:otherwise>
							<input class="fieldValue" type="radio" id="flagItems[${status.index}].yesValue" name="flagItems[${status.index}].value" value="YES"/>
						</c:otherwise>
					</c:choose>
					<label class="fieldLabelValue" for="flagItems[${status.index}].yesValue"><fmt:message key="yesLabel" bundle="${commonBundle}"/></label>
					<c:choose>
						<c:when test="${offenderFlagForm.flagItems[status.index].value.name eq 'NO'}">
							<input class="fieldValue" type="radio" id="flagItems[${status.index}].noValue" name="flagItems[${status.index}].value" value="NO" checked="checked"/>
						</c:when>
						<c:otherwise>
							<input class="fieldValue" type="radio" id="flagItems[${status.index}].noValue" name="flagItems[${status.index}].value" value="NO"/>
						</c:otherwise>
					</c:choose>
					<label class="fieldLabelValue" for="flagItems[${status.index}].noValue"><fmt:message key="noLabel" bundle="${commonBundle}"/></label>
					<c:choose>
						<c:when test="${offenderFlagForm.flagItems[status.index].value.name eq 'UNKNOWN'}">
							<input class="fieldValue" type="radio" id="flagItems[${status.index}].unknownValue" name="flagItems[${status.index}].value" value="UNKNOWN" checked="checked"/>
						</c:when>
						<c:otherwise>
							<input class="fieldValue" type="radio" id="flagItems[${status.index}].unknownValue" name="flagItems[${status.index}].value" value="UNKNOWN"/>
						</c:otherwise>
					</c:choose>
					<label class="fieldLabelValue" for="flagItems[${status.index}].unknownValue"><fmt:message key="unknownLabel" bundle="${commonBundle}"/></label>
					<c:if test="${not flagItem.category.required}">
						<c:choose>
							<c:when test="${offenderFlagForm.flagItems[status.index].value.name eq 'NOT_SET'}">
								<input class="fieldValue" type="radio" id="flagItems[${status.index}].notSetValue" name="flagItems[${status.index}].value" value="NOT_SET" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input class="fieldValue" type="radio" id="flagItems[${status.index}].notSetValue" name="flagItems[${status.index}].value" value="NOT_SET"/>
							</c:otherwise>
						</c:choose>
						<label class="fieldLabelValue" for="flagItems[${status.index}].notSetValue"><fmt:message key="notSetLabel" bundle="${commonBundle}"/></label>
					</c:if>
					<form:errors path="flagItems[${status.index}]" cssClass="error"/>
				</span>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>