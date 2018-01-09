<%--
  - Profile item for location term.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.locationterm.msgs.locationTerm">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/locationTerm/list.html?offender=${offenderSummary.id}">
			<span>
				<c:choose>
		    		<c:when test="${locationTermExists}">
		    			<fmt:message key="currentLocationTermExistsLabel"/>
		    		</c:when>
		    		<c:otherwise>
		    			<fmt:message key="noCurrentLocationTermExistsLabel"/>
		    	</c:otherwise>
	    	</c:choose>
			</span>
    	</a>
    </div>
</fmt:bundle>