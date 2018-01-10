<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (Dec 15, 2017)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.trackeddocument.msgs.trackedDocument">
	<ul>
		<sec:authorize access="hasRole('TRACKED_DOCUMENT_VIEW') or hasRole('TRACKED_DOCUMENT_EDIT') or hasRole('ADMIN')">
		<li>
			<a class="viewEditLink" href="${pageContext.request.contextPath}/trackedDocumentManage/edit.html?docket=${docket.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditLink" /></span></a>
		</li>
		</sec:authorize> 
		<sec:authorize access="hasRole('TRACKED_DOCUMENT_REMOVE') or hasRole('ADMIN')">
		<li>
			<a class="removeLink" href="${pageContext.request.contextPath}/trackedDocumentReport/remove.html?docket=${docket.id}"><span class="visibleLinkLabel"><fmt:message key="removeLink" /></span></a>
		</li>
		</sec:authorize>	
	</ul>
</fmt:bundle>