<%--
  - Identification number profile item.
  -
  - Author: Stephen Abson
  --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.identificationnumber.msgs.identificationNumber">
  	<div class="profileItem">
  			<a href="${pageContext.request.contextPath}/identificationNumber/list.html?offender=${offenderSummary.id}">
  				<span>
    				<fmt:message key="identificationNumberAndCountLabel">
    					<fmt:param value="${identificationNumberCount}"/>
    				</fmt:message>
    			</span>
    		</a>
   	</div>
</fmt:bundle>