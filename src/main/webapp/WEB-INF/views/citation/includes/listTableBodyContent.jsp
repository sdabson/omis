<%--
 - Author: Trevor Isles
 - Version: 0.1.0 (Aug 22, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:bundle basename="omis.citation.msgs.citation">
<c:forEach var="citation" items="${citations}" varStatus="status">

	<tr class="${activeClass}">
		<td>
			<a class="actionMenuItem" id="rowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/citation/citationsActionMenu.html?citation=${citation.id}"></a>
		</td>
		<td>
			<c:out value="${citation.offense.name}"/>
		</td>
		<td>
			<c:out value="${citation.counts}"/>
		</td>
		<td>
			<c:if test="${not empty citation.month}">
			<fmt:message key="monthLabel.${citation.month}"/>
			</c:if>
		</td>
		<td>
			<c:out value="${citation.day}"/>
		</td>
		<td>
			<c:out value="${citation.year}"/>
		</td>
		<td>
			<c:out value="${citation.state.name}"/>
		</td>
		<td>
			<c:out value="${citation.city.name}"/>
		</td>
		<td>
			<c:if test="${not empty citation.disposition}">
			<fmt:message key="misdemeanorDispositionLabel.${citation.disposition}"/>
			</c:if>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>