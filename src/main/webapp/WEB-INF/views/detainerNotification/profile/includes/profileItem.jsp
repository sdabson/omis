<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
  	<div class="profileItem">
  			<a href="${pageContext.request.contextPath}/detainerNotification/list.html?offender=${offenderSummary.id}">
  				<span>
    				<fmt:message key="detainerNotificationAndCountLabel">
    					<fmt:param value="${detainerNotificationCount}"/>
    				</fmt:message>
    			</span>
    		</a>
   	</div>
</fmt:bundle>