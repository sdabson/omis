<%--
  - Profile item for commit status term.
  -
  - Author: Yidong Li
  --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.commitstatus.msgs.commitStatus">
  	<div class="profileItem">
  			<a href="${pageContext.request.contextPath}/commitStatus/list.html?offender=${offenderSummary.id}">
  				<span>
    				<c:choose>
						<c:when test="${not empty commitStatusTerm}">
							<fmt:message key="commitStatusTermCountLabel">
								<fmt:param value="${commitStatusTerm.status.name}"/>
							</fmt:message>
						</c:when>
						<c:otherwise>
							<fmt:message key="commitStatusTermCountLabel">
								<fmt:param value="None"/>
							</fmt:message>
						</c:otherwise>
					</c:choose>
    			</span>
    		</a>
   	</div>
</fmt:bundle>