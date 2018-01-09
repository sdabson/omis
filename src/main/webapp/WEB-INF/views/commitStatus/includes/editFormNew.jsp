<%--
 - Author: Yidong Li
 - Version: 0.1.0 (June 6, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.commitstatus.msgs.commitStatus">
<form:form commandName="commitStatusForm" class="editForm">
	<fieldset>
		<span class="fieldGroup" >
			<form:label path="commitStatus" class="fieldLabel">
				<fmt:message key="statusLabel" />
			</form:label>					
			<form:select path="commitStatus" id="commitStatus">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${commitStatuses}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="commitStatus" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel">
				<fmt:message key="startDateLabel" />
			</form:label>
			<form:input path="startDate" class="date"/>
			<form:errors path="startDate"  cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel">
				<fmt:message key="endDateLabel" />
			</form:label>
			<form:input path="endDate" class="date"/>
			<form:errors path="endDate"  cssClass="error"/>
		</span>
	</fieldset>
	<c:if test="${not empty commitStatusTerm}">
	<c:set var="updatable" value="${commitStatusTerm}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveFamilyLabel' />"/>
	</p>
</form:form>
</fmt:bundle>