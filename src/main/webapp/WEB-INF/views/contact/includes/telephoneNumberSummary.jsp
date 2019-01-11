<%--
  - Summary of telephone number.
  -
  - Author: Joel Norris
  - Author: Sheronda Vaughn
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="contact" uri="/WEB-INF/tld/contact.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<fmt:setBundle basename="omis.contact.msgs.telephoneNumber"	var="telephoneNumber"/>
<span class="subSectionLine">
	<c:if test="${not empty telephoneNumberSummarizable.telephoneNumberCategory}">
		<fmt:message key="telephoneNumber.${telephoneNumberSummarizable.telephoneNumberCategory}.category" bundle="${telephoneNumber}"/>
	</c:if>
	<c:if test="${empty hidePrimary or not hidePrimary}">
	<c:if test="${telephoneNumberSummarizable.telephoneNumberPrimary}">		
		<fmt:message key="primaryTelephoneNumber" bundle="${telephoneNumber}"/>
	</c:if>
	</c:if>
</span>
<span class="subSectionLine">
	<c:set var="teleNum">${telephoneNumberSummarizable.telephoneNumberValue}</c:set>
	<c:choose>
		<c:when test="${fn:length(teleNum) eq 10}">	
			<c:out value="(${fn:substring(telephoneNumberSummarizable.telephoneNumberValue, 0, 3)}) ${fn:substring(telephoneNumberSummarizable.telephoneNumberValue, 3, 6)}-${fn:substring(telephoneNumberSummarizable.telephoneNumberValue, 6, 10)}"/>
		</c:when>
		<c:otherwise>
			<c:out value="${fn:substring(telephoneNumberSummarizable.telephoneNumberValue, 0, 3)}-${fn:substring(telephoneNumberSummarizable.telephoneNumberValue, 3, 7)}"/>
		</c:otherwise>
	</c:choose>
</span>
<span class="subSectionLine">
	<c:if test="${not empty telephoneNumberSummarizable.telephoneNumberExtension}">
		<fmt:formatNumber pattern="####" value="${telephoneNumberSummarizable.telephoneNumberExtension}" var="telephoneNumberExtension"/>
		<fmt:message key="extLabel" bundle="${telephoneNumber}">
			<fmt:param value="${telephoneNumberExtension}"/>
		</fmt:message>
	</c:if>
</span>