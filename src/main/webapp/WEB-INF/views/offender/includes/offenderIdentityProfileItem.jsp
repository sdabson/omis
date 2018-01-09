<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.demographics.msgs.demographics" var="demographicsBundle"/>
<fmt:bundle basename="omis.offender.msgs.offenderPersonIdentity">
<div class="offenderProfileItem">
	<ul class="profileItemHeader header">
		<li><h2><fmt:message key="offenderIdentityHeader"/></h2></li>
	</ul>
	<ul class="profileItemToolbar">
	<sec:authorize access="hasRole('OFFENDER_EDIT') or hasRole('ADMIN')">
    	<li>
    		<a class="personalDetailsLink" href="${pageContext.request.contextPath}/offender/personalDetails/edit.html?offender=${offender.id}"><fmt:message key="editPersonLink"/></a>
    	</li>
    </sec:authorize>
	</ul>
	<div class="identityProfileItemContainer">
	<div id="identityPhoto">
		<img src="${pageContext.request.contextPath}/offenderPhoto/displayProfilePhoto.html?offender=${offender.id}&amp;contentType=image/jpg" alt="<fmt:message key='noPhotoFoundLabel'/>" height="120" width="180"/>
	</div>
	<div id="identityDetails">
		<p>
			<span class="fieldLabel"><fmt:message key="offenderNameLabel"/></span>
			<span class="fieldValue">
				<c:out value="${offender.name.lastName}, ${offender.name.firstName}"/>
			</span>
			<span class="fieldLabel"><fmt:message key="offenderNumberLabel"/></span>
			<span class="fieldValue"><c:out value="${offender.offenderNumber}"/></span>
			<c:if test="${not empty offender.identity.birthDate}">
				<span class="fieldLabel"><fmt:message key="offenderDateOfBirthLabel"/></span>
				<span class="fieldValue"><fmt:formatDate value="${offender.identity.birthDate}" pattern="MM/dd/yyyy"/></span>
			</c:if>
			<c:if test="${not empty offender.identity.sex}">
				<span class="fieldLabel"><fmt:message key="offenderSexLabel"/></span>
				<span class="fieldValue"><fmt:message key="sex${offender.identity.sex.name}Label" bundle="${demographicsBundle}"/></span>
			</c:if>
		</p>
	</div>
	</div>
</div>
</fmt:bundle>