<%-- Author: Ryan Johns
 - Version: 0.1.0 (Apr 12, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.offender.msgs.alternativeOffenderIdentity">
  	<div class="profileItem">
  			<a href="${pageContext.request.contextPath}/offender/identity/alternative/list.html?offender=${offenderSummary.id}">
  				<span>
    				<fmt:message key="alternativeIdentityAndCountLabel">
    					<fmt:param value="${alternativeOffenderIdentityCount}"/>
    				</fmt:message>
    			</span>
    		</a>
   	</div>
</fmt:bundle>