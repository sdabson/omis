<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationRequest">
  	<div class="profileItem">
  			<a href="${pageContext.request.contextPath}/presentenceInvestigation/request/list.html?offender=${offenderSummary.id}">
  				<span>
    				<fmt:message key="presentenceInvestigationRequestAndCountLabel">
    					<fmt:param value="${presentenceInvestigationRequestCount}"/>
    				</fmt:message>
    			</span>
    		</a>
   	</div>
</fmt:bundle>