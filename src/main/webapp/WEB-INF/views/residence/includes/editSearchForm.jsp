<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.residence.msgs.residence" var="residenceBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<form:form commandName="residenceSearchForm" method="post" id="searchForm" class="editForm">
	<fieldSet>
		<legend><fmt:message key="searchCriteria" bundle="${residenceBundle}"/></legend>
		<span class="fieldGroup formErrors">
			<form:errors cssClass="error"/>
		</span>
		<div class="searching">
		<span class="fieldGroup">
			<label for="searchByResidenceValue" class="fieldLabel"><fmt:message key="searchByAddressValueLabel" bundle="${residenceBundle}"/></label>
			<input id="searchByResidenceValue" name="value" value="${residenceSearchForm.value}" class="fieldValue"/>
		</span>
		<span class="fieldGroup">
			<label for="searchByState" class="fieldLabel"><fmt:message key="stateLabel" bundle="${residenceBundle}"/></label>
			<select class="fieldValue" name="state" id="state">
			<jsp:include page="../../includes/nullOption.jsp"/>
			<c:forEach var="state" items="${states}" varStatus="status">
				<c:choose>
					<c:when test="${residenceSearchForm.state eq state}">
						<option value="${state.id}" selected="selected"><c:out value="${state.name}"/></option>
					</c:when>
					<c:otherwise>				
						<option value="${state.id}"><c:out value="${state.name}"/></option>				
					</c:otherwise>	
				</c:choose>
			</c:forEach>
			</select>			
		</span>
		<span class="fieldGroup">
			<label for="searchByCity" class="fieldLabel"><fmt:message key="cityLabel" bundle="${residenceBundle}"/></label>
			<select class="fieldValue" name="city" id="city">
				<jsp:include page="searchCityOptions.jsp"/>			
			</select>			
		</span>
		<span class="fieldGroup">
			<label for="effectiveDate" class="fieldLabel"><fmt:message key="effectiveDateLabel" bundle="${commonBundle}"/></label>
			<input id="effectiveDate" name="effectiveDate" type="text" class="date" value="<fmt:formatDate value='${residenceSearchForm.effectiveDate}' pattern='MM/dd/YYYY'/>"/>
		</span>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='searchButton' bundle='${residenceBundle}'/>"/>
		</p>
		</div>
	</fieldSet>
		<h1>
			<a class="actionMenuItem" id="searchResultsActionMenu" href="${pageContext.request.contextPath}/residence/searchResultsActionMenu.html"></a>
			<fmt:message key="residenceSearchResultsTitle" bundle="${residenceBundle}"/>
		</h1>
		<jsp:include page="listTableBody.jsp"/>
</form:form>