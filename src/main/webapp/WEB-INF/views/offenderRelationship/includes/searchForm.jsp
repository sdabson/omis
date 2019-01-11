<%--
  - Offender relation search form.
  -
  - Used to query people that can be related to an offender.
  -
  - Author: Stephen Abson
  - Author: Joel Norris
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="offenderRelationshipBundle" basename="omis.offenderrelationship.msgs.offenderRelationship"/>
<c:forEach var="option" items="${options}" varStatus="status">
	<c:set var="optionParams" value="${status.first ? '' : optionParams}&option=${option}"/>
</c:forEach>
<form id="offenderRelationshipSearchForm" class="searchForm" method="POST" action="${pageContext.request.contextPath}/offenderRelationship/search.html?offender=${offenderSummary.id}&amp;effectiveDate=<fmt:formatDate value='${effectiveDate}' pattern='MM/dd/yyyy'/><c:if test='${not empty optionParams}'><c:out value='${optionParams}'/></c:if>">
	<fieldset>
		<legend><fmt:message key="searchByLabel" bundle="${offenderRelationshipBundle}"/></legend>
	
	<span class="fieldGroup">
		<c:choose>
			<c:when test="${offenderRelationshipSearchForm.type.name eq 'NAME'}">
				<input type="radio" id="searchTypeNameRadio" name="type" value="NAME" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="radio" id="searchTypeNameRadio" name="type" value="NAME"/>
			</c:otherwise>
		</c:choose>
		<label class="fieldLabel" for="searchTypeName" id="searchTypeNameLabel"><fmt:message key="searchByNameLabel" bundle="${offenderRelationshipBundle}"/></label>
		<input type="text" id="searchTypeName" name="lastName" value="${offenderRelationshipSearchForm.lastName}"/>
		<label class="" for="searchTypeFirstName"><fmt:message key="searchByFirstNameLabel" bundle="${offenderRelationshipBundle}"/></label>
		<input type="text" id="searchTypeFirstName" name="firstName" value="${offenderRelationshipSearchForm.firstName}"/>
		<form:errors path="offenderRelationshipSearchForm.lastName" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<c:choose>
			<c:when test="${offenderRelationshipSearchForm.type.name eq 'OFFENDER_NUMBER'}">
				<input type="radio" id="searchTypeOffenderNumberRadio" name="type" value="OFFENDER_NUMBER" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="radio" id="searchTypeOffenderNumberRadio" name="type" value="OFFENDER_NUMBER"/>
			</c:otherwise>
		</c:choose>
		<label class="fieldLabel" for="searchTypeOffenderNumber" id="searchTypeOffenderNumberLabel"><fmt:message key="searchByOffenderNumberLabel" bundle="${offenderRelationshipBundle}"/></label>
		<input type="text" id="searchOffenderNumber" name="offenderNumber" value="${offenderRelationshipSearchForm.offenderNumber}"/>
		<form:errors path="offenderRelationshipSearchForm.offenderNumber" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<c:choose>
			<c:when test="${offenderRelationshipSearchForm.type.name eq 'SOCIAL_SECURITY_NUMBER'}">
				<input type="radio" id="searchTypeSocialSecurityNumberRadio" name="type" value="SOCIAL_SECURITY_NUMBER" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="radio" id="searchTypeSocialSecurityNumberRadio" name="type" value="SOCIAL_SECURITY_NUMBER"/>
			</c:otherwise>
		</c:choose>
		<label class="fieldLabel" for="searchTypeSocialSecurityNumber" id="searchTypeSocialSecurityNumberLabel"><fmt:message key="searchBySocialSecurityNumber" bundle="${offenderRelationshipBundle}"/></label>
		<input type="text" id="searchSocialSecurityNumber" name="socialSecurityNumber" value="${offenderRelationshipSearchForm.socialSecurityNumber}"/>
		<form:errors path="offenderRelationshipSearchForm.socialSecurityNumber" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<c:choose>
			<c:when test="${offenderRelationshipSearchForm.type.name eq 'BIRTH_DATE'}">
				<input type="radio" id="searchTypeBirthDateRadio" name="type" value="BIRTH_DATE" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="radio" id="searchTypeBirthDateRadio" name="type" value="BIRTH_DATE"/>
			</c:otherwise>
		</c:choose>
		<label class="fieldLabel" for="searchTypeBirthDate" id="searchTypeBirthDateLabel"><fmt:message key="searchByBirthDateLabel" bundle="${offenderRelationshipBundle}"/></label>
		<input type="text" id="searchBirthDate" name="birthDate" class="date" value="<fmt:formatDate value='${offenderRelationshipSearchForm.birthDate}' pattern='MM/dd/yyyy'/>"/>
		<form:errors path="offenderRelationshipSearchForm.birthDate" cssClass="error"/>
	</span>
	<c:if test="${allowAddressSearch}">
	
<!-- 		  - Enable with something like this: -->
		  <span class="fieldGroup">
		 	<c:choose>
		  		<c:when test="${offenderRelationshipSearchForm.type.name eq 'ADDRESS'}">
		 			<input type="radio" id="searchTypeAddressRadio" name="type" value="ADDRESS" checked="checked"/>
		  		</c:when>
		  		<c:otherwise>
		 			<input type="radio" id="searchTypeAddressRadio" name="type" value="ADDRESS"/>		 			
		  		</c:otherwise>
		  </c:choose>
		
		 <label class="fieldLabel" for="searchTypeAddress" id="searchTypeAddressLabel"><fmt:message key="searchByAddressLabel" bundle="${offenderRelationshipBundle}"/></label>
		 <input type="text" id="searchAddress" name="addressValue" value="${offenderRelationshipSearchForm.addressValue}"/>	
<%-- 		<form:hidden path="offenderRelationshipSearchForm.addressValue" id="hiddenAddress"/> --%>
<%-- 			<label for="searchTypeState" class="" id="searchTypeStateLabel"><fmt:message key="stateLabel" bundle="${offenderRelationshipBundle}"/></label> --%>
<%-- 			 <input type="text" id="searchState" name="stateValue" value="${offenderRelationshipSearchForm.stateName}"/>	 --%>
			<%-- 		<select class="fieldValue" name="state" id="state">
	<jsp:include page="../../includes/nullOption.jsp"/>
			<c:forEach var="state" items="${states}" varStatus="status">
				<c:choose>
					<c:when test="${offenderRelationshipSearchForm.state eq state}">
						<option value="${state.id}" selected="selected"><c:out value="${state.name}"/></option>
					</c:when>
					<c:otherwise>				
						<option value="${state.id}"><c:out value="${state.name}"/></option>				
					</c:otherwise>	
				</c:choose>
			</c:forEach> 
			</select>			--%>
		
<%-- 			<label for="searchTypeCity" class="" id="searchTypeCityLabel"><fmt:message key="cityLabel" bundle="${offenderRelationshipBundle}"/></label> --%>
<%-- 			<input  type="text" id="searchCity" name="cityValue" value="${offenderRelationshipSearchForm.cityName}"/> --%>
		<%-- 	<select class="fieldValue" name="city" id="city">
				<jsp:include page="searchCityOptions.jsp"/>			
			</select>	 --%>		
	
<%-- 	     <label class="" for="searchTypeZipCode" id="searchTypeZipCodeLabel"><fmt:message key="searchByZipCodeLabel" bundle="${offenderRelationshipBundle}"/></label> --%>
<%-- 	     <input type="text" id="searchZipCode" name="zipCodeValue" value="${offenderRelationshipSearchForm.zipCodeValue}"/>	 --%>
		
		 <form:errors path="offenderRelationshipSearchForm.addressValue" cssClass="error"/>
<!-- 		  - 	***also show components for country, State, city ZIP code look up (possibly)*** -->
		 </span>
		 <!-- ZIP CODE LOOK UP -->
		 <%-- 	  <span class="fieldGroup">
		 	<c:choose>
		  		<c:when test="${offenderRelationshipSearchForm.type.name eq 'ZIPCODE'}">
		 			<input type="radio" id="searchTypeZipCodeRadio" name="type" value="ZIPCODE" checked="checked"/>
		  		</c:when>
		  		<c:otherwise>
		 			<input type="radio" id="searchTypeZipCodeRadio" name="type" value="ZIPCODE"/>
		  		</c:otherwise>
		  </c:choose>
		 <label class="fieldLabel" for="searchTypeZipCode" id="searchTypeZipCodeLabel"><fmt:message key="searchByZipCodeLabel" bundle="${offenderRelationshipBundle}"/></label>
		  <input type="text" id="searchZipCode" name="zipCodeValue" value="${offenderRelationshipSearchForm.zipCodeValue}"/>		  
		 <form:errors path="offenderRelationshipSearchForm.zipCodeValue" cssClass="error"/>
		 </span> --%>
<!-- 		<span style="color: red">ERROR: Search by address not yet implemented</span> -->
	</c:if>
	<c:if test="${allowTelephoneNumberSearch}">
		<span class="fieldGroup">
			<c:choose>
				<c:when test="${offenderRelationshipSearchForm.type.name eq 'TELEPHONE_NUMBER'}">
					<input type="radio" id="searchTypeTelephoneNumberRadio" name="type" value="TELEPHONE_NUMBER" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input type="radio" id="searchTypeTelephoneNumberRadio" name="type" value="TELEPHONE_NUMBER"/>
				</c:otherwise>
			</c:choose>
			<label class="fieldLabel" for="searchTypeTelephoneNumber" id="searchTypeTelephoneNumberLabel"><fmt:message key="searchByTelephoneNumberLabel" bundle="${offenderRelationshipBundle}"/></label>
			<input type="text" id="searchTelephoneNumber" name="telephoneNumberValue" value="${offenderRelationshipSearchForm.telephoneNumberValue}"/>
			<form:errors path="offenderRelationshipSearchForm.telephoneNumberValue" cssClass="error"/>
		</span>
	</c:if>
	<c:if test="${allowOnlineAccountSearch}">
		<span class="fieldGroup">
			<c:choose>
				<c:when test="${offenderRelationshipSearchForm.type.name eq 'ONLINE_ACCOUNT'}">
					<input type="radio" id="searchTypeOnlineAccountRadio" name="type" value="ONLINE_ACCOUNT" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input type="radio" id="searchTypeOnlineAccountRadio" name="type" value="ONLINE_ACCOUNT"/>
				</c:otherwise>
			</c:choose>
			<label class="fieldLabel" for="searchTypeOnlineAccount" id="searchTypeOnlineAccountLabel"><fmt:message key="searchByOnlineAccountLabel" bundle="${offenderRelationshipBundle}"/></label>
			<input type="text" id="searchOnlineAccount" name="onlineAccountName" value="${offenderRelationshipSearchForm.onlineAccountName}"/>
			<form:errors path="offenderRelationshipSearchForm.onlineAccountName" cssClass="error"/>
		</span>
	</c:if>
	<p class="buttons">
		<c:if test="${showAllResultsOptions}">
			<fmt:message key="warningMessageLabel" bundle="${offenderRelationshipBundle}">
				<fmt:param value="${maximumResults}"/>
			</fmt:message>
			<button name="searchOperation" type="submit" value="SEARCH_ALL_RESULTS"><fmt:message key="showAllResultsLabel" bundle="${offenderRelationshipBundle}"/></button>
		</c:if>
		<button name="searchOperation" type="submit" value="SEARCH"><fmt:message key="searchLabel" bundle="${offenderRelationshipBundle}"/></button>
	</p>
	</fieldset>
</form>