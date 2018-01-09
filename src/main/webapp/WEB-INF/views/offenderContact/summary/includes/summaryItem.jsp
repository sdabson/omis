<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.offendercontact.msgs.summary">
<c:if test="${not empty contactSummary}">
<div class="offenderHeaderDetailsSection">
	<c:if test="${contactSummary.address}">
		<div class="headerCell">
			<span class="offenderHeaderFieldLabel"><fmt:message key="mailingAddressLabel"/></span>
			<a href="${pageContext.request.contextPath}/offenderContact/edit.html?offender=${offenderSummary.id}">
				<span class="offenderHeaderFieldValue">
					<c:out value=" ${contactSummary.addressValue} ${contactSummary.addressCityName}, ${contactSummary.addressStateAbbreviation} ${contactSummary.addressZipCodeValue}" />
				</span>
			</a>
		</div>
	</c:if>
	
	<c:if test="${contactSummary.poBox}">
		<div class="headerCell">
			<span class="offenderHeaderFieldLabel"><fmt:message key="poBoxLabel"/></span>
			<a href="${pageContext.request.contextPath}/offenderContact/edit.html?offender=${offenderSummary.id}">
				<span class="offenderHeaderFieldValue"><c:out value=" ${contactSummary.poBoxValue} ${contactSummary.poBoxCityName}, ${contactSummary.poBoxStateAbbreviation} ${contactSummary.poBoxZipCodeValue}" /></span>
			</a>
		</div>
	</c:if>
	
	<c:if test="${contactSummary.telephoneNumber}">
		<div class="headerCell">
			<span class="offenderHeaderFieldLabel"><fmt:message key="telephoneNumberLabel"/></span>
			<a href="${pageContext.request.contextPath}/offenderContact/edit.html?offender=${offenderSummary.id}">
				<span class="offenderHeaderFieldValue"><c:out value="${contactSummary.telephoneNumberValue}" /></span>
			</a>
		</div>
	</c:if>
	<c:if test="${contactSummary.onlineAccount}">
		<div class="headerCell">
			<span class="offenderHeaderFieldLabel"><fmt:message key="onlineAccountLabel"/></span>
			<a href="${pageContext.request.contextPath}/offenderContact/edit.html?offender=${offenderSummary.id}">
				<span class="offenderHeaderFieldValue"><c:out value="${contactSummary.onlineAccountName}" /></span>
			</a>
		</div>
	</c:if>
</div>
</c:if>
</fmt:bundle>