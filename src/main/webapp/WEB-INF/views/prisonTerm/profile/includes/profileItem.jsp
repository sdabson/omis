<%--
  - Prison term profile item.
  -
  - Author: Trevor Isles
  --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.prisonterm.msgs.prisonTerm">
  	<div class="profileItem">
  			<a href="${pageContext.request.contextPath}/prisonTerm/list.html?offender=${offenderSummary.id}">
  				<span>
    				<fmt:message key="prisonTermAndCountLabel">
    					<fmt:param value="${prisonTermCount}"/>
    				</fmt:message>
    			</span>
    		</a>
   	</div>
</fmt:bundle>