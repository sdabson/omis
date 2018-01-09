<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.staff.msgs.staff" var="staffBundle"/>
<fmt:setBundle basename="omis.person.msgs.person" var="personBundle"/>
<fmt:setBundle basename="omis.supervision.msgs.supervisoryOrganizationTerm" var="supervisoryOrganizationBundle"/>
<form:form commandName="staffSearchForm" method="post" id="searchForm" class="editForm">
	<fieldSet>
		<legend class="accentUltraLight"><fmt:message key="searchCriteria" bundle="${staffBundle}"/></legend>
		<%-- <span class="fieldGroup">
			<form:errors cssClass="error" path="formErrors"/>
		</span> --%>
		<div class="searching">			
		<span class="fieldGroup">
			<label for="searchByFirstName" class="fieldLabel"><fmt:message key="firstName" bundle="${personBundle}"/></label>
			<input id="searchByFirstName" name="firstName" value="${staffSearchForm.firstName}" class="fieldValue"/>			
			<form:errors cssClass="error" path="firstName"/>	
		</span>
		<span class="fieldGroup">
			<label for="searchByLastName" class="fieldLabel"><fmt:message key="lastName" bundle="${personBundle}"/></label>
			<input id="searchByLastName" name="lastName" value="${staffSearchForm.lastName}" class="fieldValue"/>			
			<form:errors cssClass="error" path="lastName"/>	
		</span>	
<!-- 		Code to be used after need items are in production	 -->
		<span class="fieldGroup">
			<label for="searchByDivision" class="fieldLabel" id="searchByDivision"><fmt:message key="searchByDivisionLabel" bundle="${staffBundle}"/></label>
			<select name="division">
			<jsp:include page="../../includes/nullOption.jsp"/>
					<c:forEach items="${divisions}" var="organizationDivision">
						<c:choose>
							<c:when test="${organizationDivision eq staffSearchForm.division}">
								<option value="${organizationDivision.id}" selected="selected"><c:out value="${organizationDivision.name}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${organizationDivision.id}"><c:out value="${organizationDivision.name}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select>				
			<form:errors cssClass="error" path="division"/>		
		</span>
		<span class="fieldGroup">
			<label for="searchByLocation" id="searchByLocation" class="fieldLabel"><fmt:message key="supervisoryOrganizationLabel" bundle="${supervisoryOrganizationBundle}"/></label>
			<select name="workLocation">
				<jsp:include page="../../includes/nullOption.jsp"/>
					<c:forEach items="${organizations}" var="organization">
						<c:choose>
							<c:when test="${organization eq staffSearchForm.workLocation}">
								<option value="${organization.id}" selected="selected"><c:out value="${organization.name}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${organization.id}"><c:out value="${organization.name}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select>
			<form:errors cssClass="error" path="workLocation"/>	
		</span>
<%-- 		<span class="fieldGroup">
			<label for="searchByEmployeeId" class="fieldLabel"><fmt:message key="searchByEmployeeIdLabel" bundle="${staffBundle}"/></label>
			<input id="searchByEmployeeId" name="employeeId" value="${staffSearchForm.employeeId}" class="fieldValue"/>					
			<form:errors cssClass="error" path="employeeId"/>	
		</span> --%>
		<%-- <span class="fieldGroup">
			<label for="searchByActiveRadio" class="fieldLabel"><fmt:message key="searchByActiveStaffLabel" bundle="${staffBundle}"/></label>
			<input type="radio" id="searchByActiveRadioYes" name="searchActiveStaff" value="${true}" checked="checked" class="fieldValue"/>
			<label for="searchByActiveRadio" class="fieldValueLabel"><fmt:message key="yesLabel" bundle="${commonBundle}"/></label>
			<input type="radio" id="searchByActiveRadioNo" name="searchActiveStaff" value="${false}" class="fieldValue"/>
			<label for="searchByActiveRadio" class="fieldValueLabel"><fmt:message key="noLabel" bundle="${commonBundle}"/></label>
			<form:errors cssClass="error" path="searchActiveStaff"/>
		</span> --%>
		<%-- <span class="fieldGroup">
			<label for="displayStaffPhotoRadio" class="fieldLabel"><fmt:message key="displayStaffPhotoLabel" bundle="${staffBundle}"/></label>
			<input type="radio" id="displayStaffPhotoRadio" name="displayStaffPhoto" value="${staffSearchForm.displayStaffPhoto}" class="fieldValue"/>
			<form:errors cssClass="error" path="displayStaffPhoto"/>	
		</span> --%>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='searchLabel' bundle='${commonBundle}'/>"/>
	</p>
	</div>
	</fieldSet>
		<h1>
			<a class="actionMenuItem" id="staffSearchResultsActionMenu" href="${pageContext.request.contextPath}/staffSearch/searchResultsActionMenu.html"></a>
			<fmt:message key="staffSearchResultsTitle" bundle="${staffBundle}"/>
		</h1>
		<jsp:include page="listTableBody.jsp"/>
</form:form>