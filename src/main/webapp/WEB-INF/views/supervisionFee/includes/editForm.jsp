<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.supervisionfee.msgs.supervisionFee">
<form:form commandName="supervisionFeeForm" class="editForm">
	<fieldset>
	<span class="fieldGroup">
	<form:label path="startDate" class="fieldLabel">
		<fmt:message key="startDateLabel"/></form:label>
	<form:input path="startDate" class="date"/>
	<form:errors cssClass="error" path="startDate"/>
	</span>
	<span class="fieldGroup">
	<form:label path="endDate" class="fieldLabel">
		<fmt:message key="endDateLabel"/></form:label>
	<form:input path="endDate" class="date"/>
	<form:errors path="endDate" cssClass="error"/>
	</span>
	<span class="fieldGroup">
	<form:label path="authorityCategory" class="fieldLabel">
		<fmt:message key="authorityCategoryLabel"/></form:label>
	<form:select path="authorityCategory">	
	<c:choose>
		<c:when test="${not empty monthlySupervisionFee}">				
			<form:input path="authorityCategory" readonly="true"/>
		</c:when>	
		<c:otherwise>	
			<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>			
			<c:forEach var="authorityCategory" items="${authorityCategories}">	
				<form:option value="${authorityCategory}"><fmt:message key="authorityCategoryLabel.${authorityCategory.name}"/></form:option>		
			</c:forEach>		
		</c:otherwise>		
	</c:choose>	
	</form:select>		
	<form:errors path="authorityCategory" cssClass="error"/>
	</span>
	<span class="fieldGroup">
	<form:label path="fee" class="fieldLabel">
		<fmt:message key="feeLabel" /></form:label>
	<form:input path="fee"/>
	<form:errors path="fee" cssClass="error"/>
	</span>		
	<span class="fieldGroup">
	<form:label path="comment" class="fieldLabel">
		<fmt:message key="commentLabel"/>
	</form:label>
	<form:textarea path="comment"/>
	<form:errors path="comment" cssClass="error"/>
	</span>		
	</fieldset>
	<fieldset id="feeRequirementsHolder">
	<legend><fmt:message key="feeRequirementsTitle"/></legend>	
	<a href="${pageContext.request.contextPath}/supervisionFee/addOfficerFeeRequirement.html" class="createLink" id="addOfficerFeeRequirementLink">
	<span class="visibleLinkLabel"><fmt:message key="addOfficerFeeRequirementLink"/></span></a><br>
	<a href="${pageContext.request.contextPath}/supervisionFee/addCourtFeeRequirement.html" class="createLink" id="addCourtFeeRequirementLink">
	<span class="visibleLinkLabel"><fmt:message key="addCourtFeeRequirementLink"/></span></a>
	<jsp:include page="feeRequirementsContent.jsp"/>
	<form:errors path="feeRequirements" cssClass="error"/>
	</fieldset>
	<c:if test="${not empty monthlySupervisionFee}">
	<c:set var="updatable" value="${monthlySupervisionFee}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key="saveLabel" bundle="${commonBundle}"/>"/>
	</p>
</form:form>
</fmt:bundle>