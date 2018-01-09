<%--
 - Author: Jonny Santy
 - Version: 0.1.0 (Jul 12, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis"%>
<fmt:bundle basename="omis.condition.msgs.condition">
	<c:forEach var="agreement" items="${agreementSummaries}">
		<c:set var="newAgreementRow" value="true" />
		<c:set var="agreementRowSpan" value="${fn:length(agreement.conditionSummaries)+1}" />
		<c:forEach var="condition" items="${agreement.conditionSummaries}">
			<c:choose>
				<c:when test="${condition.getActive()}">
					<c:set var="activeClass" value="activeRecord" />
				</c:when>
				<c:otherwise>
					<c:set var="activeClass" value="inactiveRecord" />
				</c:otherwise>
			</c:choose>
			<tr class="${activeClass}">
			<c:choose>
				<c:when test="${newAgreementRow}">
					<td rowspan="${agreementRowSpan}" style="border-top-width: 1px">
						<fmt:formatDate	value="${agreement.startDate}" pattern="MM/dd/yyyy" /> - 
						<fmt:formatDate	value="${agreement.endDate}" pattern="MM/dd/yyyy" />
					</td>
					</tr>
					<tr  class="${activeClass}" style="border-top-width: 1px">
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
				<td><c:out value="${condition.conditionTitle}"  /></td>
				<td><c:out value="${condition.conditionClause}" /></td>
				<c:set var="newAgreementRow" value="false" />
			</tr>
		</c:forEach>
	</c:forEach>
</fmt:bundle>