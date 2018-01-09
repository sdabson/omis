<%--
  - Incident search form.
  -
  - Author: Yidong Li
  - Author: Joel Norris
  - Version: 0.1.1 (Oct 16, 2015)
  - Since: OMIS 3.0
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.incident.msgs.incident">
<form:form commandName="searchIncidentReportForm" >
<div class="centeredFieldGroup">
	<span class="fieldGroup">
		<form:label path="startDate" class="fieldLabel">
			<fmt:message key="startDateLabel" />
		</form:label>
		<form:input path="startDate" class="date"/>
		<form:errors path="startDate"  cssClass="error"/>
	</span>
</div>
<div class="centeredFieldGroup">
	<span class="fieldGroup">
		<form:label path="endDate" class="fieldLabel">
			<fmt:message key="endDateLabel" />
		</form:label>
		<form:input path="endDate" class="date"/>
		<form:errors path="endDate"  cssClass="error"/>
	</span>
</div>
<div class="centeredFieldGroup">
	<span class="fieldGroup">		
		<form:label path="jurisdiction" class="fieldLabel">
			<fmt:message key="jurisdictionLabel"/>
		</form:label>
		<form:select path="jurisdiction">
			<form:option value=""><jsp:include page="../../includes/nullOption.jsp"/></form:option>
			<c:forEach items="${jurisdictions}" var="jurisdiction" varStatus="status">
				<form:option value="${jurisdiction.id}"><c:out value="${jurisdiction.organization.name}"/></form:option>
			</c:forEach>
		</form:select>
	</span>
</div>

<div class="centeredFieldGroup">
	<a class="addPersonLink actionMenuItem" id="addInvolvedPersonLink" href="${pageContext.request.contextPath}/incident/report/addInvolvedPersonMenu.html"></a><span class="visibleLinkLabel"><fmt:message key="addInvolvedPersonLabel"/></span>
</div>
<div id="involvedPersonItemsContainer" class="centeredFieldGroup">
	<c:forEach items="${searchIncidentReportForm.items}" var="item" varStatus="status">
		<c:set value="${item}" var="involvedPersonItem" scope="request"/>
		<c:set value="${status.index}" var="involvedPersonItemIndex" scope="request"/>
		<c:if test="${not empty item.person}">
			<jsp:include page="../statement/includes/involvedPersonItem.jsp"/>
		</c:if>
	</c:forEach>
</div>
<p class="buttons">
	<input type="submit" value="<fmt:message key='searchIncidentsLabel'/>"/>
</p>
</form:form>
</fmt:bundle>