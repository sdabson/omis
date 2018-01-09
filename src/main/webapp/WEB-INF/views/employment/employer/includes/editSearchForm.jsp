<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.employment.msgs.employment" var="employerBundle"/>
<form:form commandName="employerSearchForm" method="post" id="searchForm" class="editForm">
	<fieldSet>
		<legend><fmt:message key="searchCriteria" bundle="${employerBundle}"/></legend>
		<span class="fieldGroup formErrors">
			<form:errors cssClass="error"/>
		</span>
		<div class="searching">
		<span class="fieldGroup">
			<label for="searchByEmployerName" class="fieldLabel"><fmt:message key="searchByEmployerNameLabel" bundle="${employerBundle}"/></label>
			<input id="searchByEmployerName" name="employerName" value="${employerSearchForm.employerName}" class="fieldValue"/>			
		</span>		
		<span class="fieldGroup">
			<label for="searchByState" class="fieldLabel"><fmt:message key="searchByStateLabel" bundle="${employerBundle}"/></label>
			<select class="fieldValue" name="state" id="state">
			<jsp:include page="../../../includes/nullOption.jsp"/>
			<c:forEach var="state" items="${states}" varStatus="status">
				<c:choose>
					<c:when test="${employerSearchForm.state eq state}">
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
			<label for="searchByCity" class="fieldLabel"><fmt:message key="searchByCityLabel" bundle="${employerBundle}"/></label>
			<select class="fieldValue" name="city" id="city">
				<jsp:include page="cityOptions.jsp"/>			
			</select>			
		</span>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='searchButton' bundle='${employerBundle}'/>"/>
	</p>
	</div>
	</fieldSet>
		<h1>
			<a class="actionMenuItem" id="searchResultsActionMenu" href="${pageContext.request.contextPath}/employer/searchResultsActionMenu.html"></a>
			<fmt:message key="employerSearchResultsTitle" bundle="${employerBundle}"/>
		</h1>
		<jsp:include page="listTableBody.jsp"/>
</form:form>