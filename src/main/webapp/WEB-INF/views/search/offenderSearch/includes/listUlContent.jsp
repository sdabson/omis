<%--
 - Author: Ryan Johns
 - Author: Sheronda Vaughn
 - Version: 0.1.1 (Feb 26, 2014)
 - Since: OMIS 3.0
 --%>
<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:bundle basename="omis.search.msgs.search">
<c:forEach var="offender" items="${offenders}">
	<li>
		<fmt:message var="noPhotoLabel" key="noPhotoFoundLabel"/>
		<div class="inlineBlock"><img src="#" data-src="${pageContext.request.contextPath}/offenderPhoto/displayProfilePhoto.html?offender=${offender.personId}&amp;contentType=image/jpeg" class="profileImage viewable" alt="${noPhotoLabel}"/></div>
		<div class="inlineBlock">
		<c:out value="${offender.lastName},"/><c:out value=" ${offender.firstName} "/><c:if test="${not empty offender.middleName}"><c:out value="${offender.middleName} "/></c:if><c:if test="${not empty offender.suffixName}"> <c:out value="${offender.suffixName} "/></c:if><c:out value="#${offender.offenderNumber}"/>
		<div>
		<a class="offenderProfileLink newTab" href="${pageContext.request.contextPath}/offender/profile.html?offender=${offender.personId}"></a>
		</div>
		</div>
	</li>
</c:forEach>
</fmt:bundle>