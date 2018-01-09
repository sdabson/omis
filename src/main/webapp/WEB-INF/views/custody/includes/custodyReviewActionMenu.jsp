<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Nov 06, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.custody.msgs.custodyReview">
<ul>
	<sec:authorize access="hasRole('CUSTODY_REVIEW_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" id="actionMenuLink" href="${pageContext.request.contextPath}/custody/list.html?offender=${offender.id}">
					<fmt:message key="listCustodyReviews"/>
				</a>
			</li>
	</sec:authorize>
</ul>
</fmt:bundle>