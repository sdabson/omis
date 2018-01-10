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
		<sec:authorize access="hasRole('TRACKED_DOCUMENT_CREATE') or hasRole('ADMIN')">
		<li>
			<a class="actionMenuItem" id="createTrackedDocumentLink" href="${pageContext.request.contextPath}/trackedDocument/addTrackedDocumentItem.html?offender=${offender.id}">
				<span class="visibleLinkLabel">
					<fmt:message key="createTrackDocumentLabel"/>
				</span>
			</a>
		</li>
		</sec:authorize> 
	</ul>
</fmt:bundle>