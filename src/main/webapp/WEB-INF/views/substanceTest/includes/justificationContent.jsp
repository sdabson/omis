<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (March 17, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.substance.msgs.substance">
	<c:if test="${!privateLab or empty privateLab}">
		<c:set var="disabledValue" value='disabled="disabled"'/>
	</c:if>
	<span class="fieldGroup">
			<label for="authorizingStaff" class="fieldLabel"><fmt:message key="labAuthorizedByLabel"/></label>
			<input type="hidden" id="authorizingStaff" name="authorizingStaff" value="${substanceTestForm.authorizingStaff.id}"/>
			<input type="text" ${disabledValue} id="authorizingStaffInput"/>
			<a id="authorizingStaffCurrent" class="currentUserAccountLink"></a>
			<a id="authorizingStaffClear" class="clearLink"></a>
			<span id="authorizingStaffDisplay">
				<c:if test="${not empty substanceTestForm.authorizingStaff}">
					<fmt:message key="authorizingStaffInformation">
						<fmt:param value="${substanceTestForm.authorizingStaff.name.lastName}"/>
						<fmt:param value="${substanceTestForm.authorizingStaff.name.firstName}"/>
					</fmt:message>
				</c:if>
			</span>
			<form:errors cssClass="error" path="authorizingStaff"/>
	</span>
	<span class="fieldGroup">
			<label for="privateLabJustification" class="fieldLabel"><fmt:message key="privateLabJustificationLabel"/></label>
			<textarea ${disabledValue} id="privateLabJustification" name="privateLabJustification"><c:out value="${substanceTestForm.privateLabJustification}"/></textarea>
			<form:errors cssClass="error" path="privateLabJustification"/>
	</span>
</fmt:bundle>