<%--
 - Author: Trevor Isles
 - Version: 0.1.0 (Aug 22, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.citation.msgs.citation">
<sec:authorize var="editCitation" access="hasRole('MISDEMEANOR_CITATION_EDIT') or hasRole('MISDEMEANOR_CITATION_CREATE') or hasRole('ADMIN')"/>
<form:form commandName="citationForm" class="editForm">
<fieldset>
	<legend><fmt:message key="citationDetailsTitle"/></legend>

	<span class="fieldGroup">
	<form:label path="offense" class="fieldLabel">
		<fmt:message key="offenseLabel"/></form:label>
	<form:select path="offense" readonly="${not editCitation}">
		<jsp:include page="../../includes/nullOption.jsp"/>
		<form:options items="${offenses}" itemLabel="name" itemValue="id"/>
	</form:select>
	<form:errors path="offense" cssClass="error"/>
	<form:input id="offenseName" path="offenseName"/>
	<form:errors path="offenseName" cssClass="error"/>
	<label for="createNewOffense" class="fieldValueLabel"><fmt:message key="createNewOffenseLabel"/></label>
	<form:checkbox id="createNewOffense" path="createNewOffense"/>
	</span>

	<span class="fieldGroup">
	<form:label path="state" class="fieldLabel">
		<fmt:message key="stateLabel"/></form:label>
	<form:select path="state" readonly="${not editCitation}">
		<jsp:include page="../../includes/nullOption.jsp"/>
		<form:options items="${states}" itemLabel="name" itemValue="id"/>
	</form:select>
	<form:errors path="state" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
	<form:label path="city" class="fieldLabel">
		<fmt:message key="cityLabel"/></form:label>
	<form:select path="city" readonly="${not editCitation}">
		<jsp:include page="../../includes/nullOption.jsp"/>
		<form:options items="${cities}" itemLabel="name" itemValue="id"/>
	</form:select>
	<form:errors path="city" cssClass="error"/>
	</span>
	
	<c:choose>
		<c:when test="${citationForm.partialDate}">
			<c:set var="dateContainerDisplayClass" value="hidden"/>
			<c:set var="partialDateContainerDisplayClass" value=""/>
		</c:when>
		<c:otherwise>
			<c:set var="dateContainerDisplayClass" value=""/>
			<c:set var="partialDateContainerDisplayClass" value="hidden"/>
		</c:otherwise>
	</c:choose>
	
	<span class="fieldGroup">
		<label for="partialDate" class="fieldLabel"><fmt:message key="partialDateLabel"/></label>
		<form:checkbox id="partialDate" path="partialDate"/>
	</span>
	
	<span id="dateContainer" class="${dateContainerDisplayClass}">
		<span class="fieldGroup">
			<form:label for="date" path="date" class="fieldLabel">
				<fmt:message key="dateLabel"/></form:label>
			<form:input id="date" path="date" class="date"/>
			<form:errors path="date" cssClass="error"/>
		</span>
	</span>
		
	<span id="partialDateContainer" class="${partialDateContainerDisplayClass}">
		<span class="fieldGroup">
			<form:label for="month" path="month" class="fieldLabel">
				<fmt:message key="monthLabel"/></form:label>
			<form:select path="month" readonly="${not editCitation}">
			<jsp:include page="../../includes/nullOption.jsp"/>
			<c:forEach items="${months}" var="month">
				<form:option value="${month.name}">
				<fmt:message key="monthLabel.${month.name}" />
				</form:option>
			</c:forEach>
			</form:select>
			<form:errors path="month" cssClass="error"/>
		</span>	
		
		<span class="fieldGroup">
			<form:label path="year" class="fieldLabel">
				<fmt:message key="yearLabel"/></form:label>
			<form:input path="year" readonly="${not editCitation}"/>
			<form:errors path="year" cssClass="error"/>
		</span>
	</span>	
		
	<span class="fieldGroup">
	<form:label path="counts" class="fieldLabel">
		<fmt:message key="citationCountsLabel"/></form:label>
	<form:input path="counts" class="veryShortNumber" readonly="${counts}"/>
	<form:errors path="counts" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
	<form:label path="disposition" class="fieldLabel">
		<fmt:message key="dispositionLabel"/></form:label>
	<form:select path="disposition" readonly="${not editCitation}">
		<jsp:include page="../../includes/nullOption.jsp"/>
		<c:forEach items="${dispositions}" var="disposition">
			<form:option value="${disposition.name}">
				<fmt:message key="misdemeanorDispositionLabel.${disposition.name}"/>
			</form:option>
		</c:forEach>
	</form:select>
	<form:errors path="disposition" cssClass="error"/>
	</span>
	
</fieldset>
<c:if test="${not empty citation}">
<c:set var="updatable" value="${citation}" scope="request"/>
<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
</c:if>
<c:if test="${editCitation}">
<p class="buttons">
	<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
</p>
</c:if>
</form:form>
</fmt:bundle>