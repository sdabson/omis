<%--
  - Profile item for offender alternative names.
  -
  - Author: Stephen Abson
  --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.offender.msgs.alternativeOffenderName">
  	<div class="profileItem">
  			<a href="${pageContext.request.contextPath}/offender/name/alternative/list.html?offender=${offenderSummary.id}">
  				<span>
    				<fmt:message key="alternativeNameAndCountLabel">
    					<fmt:param value="${alternativeOffenderNameCount}"/>
    				</fmt:message>
    			</span>
    		</a>
   	</div>
</fmt:bundle>