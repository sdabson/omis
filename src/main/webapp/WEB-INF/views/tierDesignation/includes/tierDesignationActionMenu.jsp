<%-- Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:bundle basename="omis.tierdesignation.msgs.tierDesignation">
	<ul>
		<sec:authorize access="hasRole('TIER_DESIGNATION_LIST') or hasRole('ADMIN')">
    		<c:if test="${not empty offender}">
    		<li>
    			<a class="listLink" href="${pageContext.request.contextPath}/tierDesignation/list.html?offender=${offender.id}"><fmt:message key="listTierDesignationsLink"/></a>
    		</li>
    		</c:if>
    	</sec:authorize>
    </ul>
</fmt:bundle>