<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 17, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.substancetest.msgs.substanceTest">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/substanceTest/list.html?offender=${offenderSummary.id}">
			<span>
				<c:choose>
					<c:when test="${not empty lastSubstanceTest}">
    					<fmt:message key="substanceLinkLabel">
    						<fmt:param>
    							<fmt:message key="lastTestedLabel">
    								<fmt:param>
    									<fmt:formatDate value="${lastSubstanceTest}" pattern="MM/dd/YYYY"/>
    								</fmt:param>
    							</fmt:message>
    						</fmt:param>
    					</fmt:message>
    				</c:when>
    				<c:otherwise>
    					<fmt:message key="substanceLinkLabel">
    						<fmt:param>
    							<fmt:message key="noTestLabel"/>
    						</fmt:param>
    					</fmt:message>
    				</c:otherwise>
    			</c:choose>
    		</span>
    	</a>
    </div>
</fmt:bundle>