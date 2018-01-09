<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (June 6, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.offenderphoto.msgs.offenderPhoto">
	<ul>
		<sec:authorize access="hasRole('ADMIN') or hasRole('OFFENDER_PHOTO_LIST')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/offenderPhoto/list.html?offender=${offender.id}">
					<span class="visibleLinkLabel"><fmt:message key="listOffenderPhotosLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>