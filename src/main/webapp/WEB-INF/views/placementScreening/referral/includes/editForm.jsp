<%-- Author: Ryan Johns
 - Version: 0.1.0 (Feb 4, 2015)
 - Since: OMIS 3.0 --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <fmt:setBundle basename="omis.msgs.common" var="common"/>
 <fmt:bundle basename="omis.placementscreening.msgs.form">
 <form:form id="form" commandName="placementReferralForm" class="editForm">
 	<span class="fieldGroup">
 		<form:label path="referralDate" class="fieldLabel">
 			<fmt:message key="referralDateLabel"/>
 		</form:label>
 		<form:input id="referralDate" path="referralDate" class="date"/>
 		<form:errors path="referralDate" cssClass="error"/>
 	</span>
 	<fieldset>
 		<legend><fmt:message key="referralSourceLabel"/></legend>
 		<span class="fieldGroup">
 			<form:label path="referringUser" class="fieldLabel">
 				<fmt:message key="referringUserLabel"/>
 			</form:label>
 			<input id="referringUserInput"/>
 			<form:hidden path="referringUser" id="referringUserId" />
 			<a id="currentUser" class="currentUserAccountLink"></a>
 			<a id="clearUser" class="clearLink"></a>
 			<span id="userLabel">
 				<c:if test="${not empty placementReferralForm.referringUser}">
 				<c:out value="${placementReferralForm.referringUser.user.name.lastName}, ${placementReferralForm.referringUser.user.name.firstName}"/>
 				</c:if>
 			</span>
 			<form:errors path="referringUser" cssClass="error"/>
 		</span>
 		<span class="fieldGroup">
 			<form:label path="referringFacility" class="fieldLabel">
 				<fmt:message key="referringFacilityLabel"/>
 			</form:label>
 			<form:select path="referringFacility">
 				<option value=""><fmt:message key="nullLabel" bundle="${common}"/></option>
 				<c:forEach items="${referringFacilities}" var="referringFacility">
	 				<c:choose>
		 				<c:when test="${referringFacility eq programReferralForm.referringFacility}"> 
			 				<option value="${referringFacility.id}" selected="selected">
								<c:out value="${referringFacility.name}"/>
							</option>
		 				</c:when>
	 					<c:otherwise>
	 						<option value="${referringFacility.id}">
								<c:out value="${referringFacility.name}"/>
							</option>
	 					</c:otherwise>  
	 				</c:choose>
 				</c:forEach>
 			</form:select>
 			<form:errors path="referringFacility" cssClass="error"/>
 		</span>
 	</fieldset>
 	<span class="fieldGroup">
 		<form:label path="programCategory" class="fieldLabel">
 			<fmt:message key="programCategoryLabel"/>
 		</form:label>
 		<form:select id="programCategory" path="programCategory">
 				<option value=""><fmt:message key="nullLabel" bundle="${common}"/></option>
 				<c:forEach items="${programCategories}" var="programCategory">
 					<form:option value="${programCategory}"><fmt:message key="ProgramCategoryLabel.${programCategory}"/></form:option>
 				</c:forEach>
 		</form:select>
 		<form:errors path="programCategory" cssClass="error"/>
 	</span>
 	<span class="fieldGroup">
 		<form:label path="referralScreeningCenter" class="fieldLabel">
 			<fmt:message key="facilityLabel"/>
 		</form:label>
 		<form:select id="facility" path="referralScreeningCenter">
 			<option value=""><fmt:message key="nullLabel" bundle="${common}"/></option>
 			<c:forEach items="${referralFacilities}" var="facility">
				<c:choose>
					<c:when test="${facility eq programReferralForm.referralScreeningCenter}"> 
						<option value="${facility.id}" label="${facility.name}"selected="selected">
 							<c:out value="${facility.name}"/>
 						</option>
					</c:when>
					<c:otherwise>
						<option value="${facility.id}" label="${facility.name}">
 						<c:out value="${facility.name}"/>
 						</option>
					</c:otherwise>
				</c:choose>
 			</c:forEach>
 		</form:select>
 	</span> 
 	<div id="screeningItems">
 		<form:errors path="screeningItems" cssClass="error" element="div"/>
		<fieldset>
			<legend>
				<fmt:message key="preReleaseScreeningLabel"/>
			</legend>
			<form:errors path="prereleaseScreeningItems" cssClass="error"/>
			<jsp:include page="preReleaseTable.jsp"></jsp:include>
		</fieldset>	
		
	 	<fieldset>
	 		<legend>
	 			<fmt:message key="treatmentScreeningLabel"/>
	 		</legend>
	 		<form:errors path="treatmentScreeningItems"/>
	 		<jsp:include page="treatmentTable.jsp"></jsp:include>
	 	</fieldset>
 	</div>
	
	
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle="${common}"/>"/>
	</p>
 </form:form>
 </fmt:bundle>