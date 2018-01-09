<%--
 - Author: Jonny Santy
 - Version: 0.1.0 (Nov 1, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle
	basename="omis.presentenceinvestigation.msgs.biographicandcontactsection">
	<sec:authorize var="editBiographicAndContactSection"
		access="hasRole('BIOGRAPHIC_AND_CONTACT_SECTION_EDIT') or hasRole('BIOGRAPHIC_AND_CONTACT_SECTION_CREATE') or hasRole('ADMIN')" />

	<form:form commandName="biographicAndContactSectionForm"
		class="editForm">
		<fieldset>
			<legend>
				<fmt:message key="biographicAndContactSectionHeader" />
			</legend>

			<span class="fieldGroup"> <form:label path="name"
					class="fieldLabel">
					<fmt:message key="name" />
				</form:label> 
				 	<a href="../offender/personalDetails/edit.html?offender=${biographicAndContactSectionForm.nameId}"><c:out value="${biographicAndContactSectionForm.name}" /> </a><form:errors path="aka" cssClass="error" /><br/>
				<%-- 		<form:input path="name" readonly="${not editCaution}" class="date"/> --%>
				<form:errors path="name" cssClass="error" />
			</span> 
			
			
		<span class="fieldGroup">
			<form:label path="dateOfSentence" class="fieldLabel">
				<fmt:message key="dateOfSentence"/></form:label>
			<form:input path="dateOfSentence" class="date"/>
			<form:errors path="dateOfSentence" cssClass="error"/>
		</span>
		
		
			<span class="fieldGroup"> <form:label path="address"  class="fieldLabel">
					<fmt:message key="address" />
				</form:label> 
						<c:choose>
							<c:when test="${not empty biographicAndContactSectionForm.addressId}">
						 		<a href="../residence/editResidenceTerm.html?residenceTerm=${biographicAndContactSectionForm.addressId}"><c:out value="${biographicAndContactSectionForm.address}" /> </a><form:errors path="aka" cssClass="error" /><br/>
							</c:when>
							<c:otherwise>
							 	<c:out value="${biographicAndContactSectionForm.address}" /> <br/>
						 	</c:otherwise>
						</c:choose>
<form:errors path="address"
					cssClass="error" />
			</span> 
			<span class="fieldGroup"> <form:label path="aka" class="fieldLabel">
					<fmt:message key="aka" />
				</form:label>
				<table>
				<c:forEach var="altName" items="${biographicAndContactSectionForm.alternativeNames }">
					<tr><td>
				 			<a href="../offender/name/alternative/edit.html?alternativeNameAssociation=${altName.key}"><c:out value="${altName.value}" /> </a><form:errors path="aka" cssClass="error" />
					</td></tr>
				</c:forEach>
				</table>
			</span> 
			<span class="fieldGroup"> <form:label path="phone" class="fieldLabel">
					<fmt:message key="phone" />
				</form:label> <form:input path="phone" type="tel"/> <form:errors path="phone"
					cssClass="error" />
			</span> 
			<span class="fieldGroup"> <form:label path="cellPhone" class="fieldLabel">
					<fmt:message key="cellPhone" />
				</form:label> <form:input path="cellPhone" type="tel"/> <form:errors path="cellPhone"
					cssClass="error" />
			</span>
		</fieldset>

		<p class="buttons">
			<input type="submit"
				value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>" />
		</p>
		
	</form:form>
</fmt:bundle>


