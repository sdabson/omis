<%--
  - Citation profile item.
  -
  - Author: Stephen Abson
  --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.citation.msgs.citation">
  	<div class="profileItem">
  			<a href="${pageContext.request.contextPath}/citation/list.html?offender=${offenderSummary.id}">
  				<span>
    				<fmt:message key="citationAndCountLabel">
    					<fmt:param value="${citationCount}"/>
    				</fmt:message>
    			</span>
    		</a>
   	</div>
</fmt:bundle>