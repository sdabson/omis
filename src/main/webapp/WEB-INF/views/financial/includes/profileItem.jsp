<%-- Author: Ryan Johns
 - Version: 0.1.0 (Apr 18, 2017)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.financial.msgs.financial">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/financial/edit.html?offender=${offenderSummary.id}">
			<span>
				<c:choose>
				<c:when test="${not empty financialProfileItemSummary && not empty financialProfileItemSummary.assetCount}">
					<c:set var="assetCount" value="${financialProfileItemSummary.assetCount}"/>
				</c:when>
				<c:otherwise>
					<c:set var="assetCount" value="0"/>
				</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty financialProfileItemSummary && not empty financialProfileItemSummary.liabilityCount}">
						<c:set var="liabilityCount" value="${financialProfileItemSummary.liabilityCount}"/>
					</c:when>
					<c:otherwise>
					<c:set var="liabilityCount" value="0"/>
					</c:otherwise>
				</c:choose>
				<fmt:message key="financialProfileCountLabel">
					<fmt:param value="${assetCount}"/>
					<fmt:param value="${liabilityCount}"/>
				</fmt:message>
			</span>
		</a>
	</div>
</fmt:bundle>