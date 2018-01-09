<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.caseload.msgs.caseload" var="caseloadBundle"/>
<form:form commandName="supervisoryCaseloadForm" method="post" id="searchForm" class="editForm">
	<fieldSet>	
	<legend><c:out value="${caseloadSearchForm.worker.name.firstName}, ${caseloadSearchForm.worker.name.lastName}'s "/><fmt:message key="supervisoryCaseload" bundle="${caseloadBundle}"/></legend>
<!-- 	these values are passed in to form automatically when searching for current users caseload -->
<!-- 		this passes the caseloadName to top of form -->
		<span class="fieldGroup">
			<label for="searchByCaseloadName" class="fieldLabel">
			<fmt:message key="caseloadName" bundle="${caseloadBundle}"/></label>
			<c:out value="${caseloadSearchForm.caseloadName}"/>
		</span>
<!-- 		passes the start date if available, otherwise pass effective date(today) -->
		<span class="fieldGroup">
			<label for="searchByStartDate" class="fieldLabel">
			<fmt:message key="startDate" bundle="${caseloadBundle}"/></label>			
				<c:out value="${caseloadSearchForm.startDate}"/>
		</span>
<!-- 		passes the end date -->
		<span class="fieldGroup">
			<label for="searchByEndDate" class="fieldLabel">
			<fmt:message key="endDate" bundle="${caseloadBundle}"/></label>			
				<c:out value="${caseloadSearchForm.endDate}"/>
		</span>
	</fieldSet>
	<fieldSet>		
	<legend class="accentUltraLight"><fmt:message key="searchCriteria" bundle="${caseloadBundle}"/></legend>
		<span class="fieldGroup formErrors">
			<form:errors cssClass="error"/>
		</span>
		<div class="searching">			
		<span class="fieldGroup">
			<label for="searchByStartDate" class="fieldLabel"><fmt:message key="startDate" bundle="${caseloadBundle}"/></label>
			<input id="searchByStartDate" name="startDate" class="date" value="<fmt:formatDate value='${caseloadSearchForm.startDate}' pattern='MM/dd/yyyy'/>" class="fieldValue"/>			
			<form:errors cssClass="error" path="startDate"/>	
		</span>
		<span class="fieldGroup">
			<label for="searchByEndDate" class="fieldLabel"><fmt:message key="endDate" bundle="${caseloadBundle}"/></label>
			<input id="searchByEndDate" name="endDate" class="date" value="<fmt:formatDate value='${caseloadSearchForm.endDate}' pattern='MM/dd/yyyy'/>" class="fieldValue"/>			
			<form:errors cssClass="error" path="endDate"/>	
		</span>	
		<span class="fieldGroup">
			<label for="searchByCaseloadName" class="fieldLabel"><fmt:message key="caseloadName" bundle="${caseloadBundle}"/></label>
			<input id="searchByCaseloadName" name="caseloadName" value="${caseloadSearchForm.caseloadName}" class="fieldValue"/>			
			<form:errors cssClass="error" path="caseloadName"/>	
		</span>
		<span class="fieldGroup">
			<label for="searchByCategory" class="fieldLabel" id="searchByCategory"><fmt:message key="searchByCaseWorkCategoryLabel" bundle="${caseloadBundle}"/></label>
			<select name="category">
			<jsp:include page="../../includes/nullOption.jsp"/>
					<c:forEach items="${categories}" var="category">
						<c:choose>
							<c:when test="${caseWorkCategory eq caseloadSearchForm.caseWorkCategory}">
								<option value="${caseWorkCategory.name}" selected="selected"><fmt:message key="category${caseWorkCategory.name}Label" bundle="${caseloadBundle}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${caseWorkCategory.name}"><fmt:message key="category${caseWorkCategory.name}Label" bundle="${caseloadBundle}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select>				
			<form:errors cssClass="error" path="caseWorkCategory"/>		
		</span>		
	<p class="buttons">
		<input type="submit" value="<fmt:message key='searchLabel' bundle='${commonBundle}'/>"/>
	</p>
	</div>
	</fieldSet>
		<h1>
			<a class="actionMenuItem" id="caseloadSearchResultsActionMenu" href="${pageContext.request.contextPath}/caseloadSearch/searchResultsActionMenu.html"></a>
			<fmt:message key="caseloadSearchResultsTitle" bundle="${caseloadBundle}"/>
		</h1>
		<jsp:include page="listTableBody.jsp"/>
</form:form>
</body>
</html>