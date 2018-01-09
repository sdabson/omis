<%-- 
 - Author: Josh Divine
 - Version: 0.1.0 (Nov 9, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.paroleboardmember.msgs.paroleBoardMember">
	<ul>
		<sec:authorize access="hasRole('PAROLE_BOARD_MEMBER_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/paroleBoardMember/list.html">
				<span class="visibleLinkLabel"><fmt:message key="listParoleBoardMembersLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>