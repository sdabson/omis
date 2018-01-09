<!-- 
 - Author: Josh Divine
 - Version: 0.1.0 (May 25, 2017)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.probationterm.msgs.probationTerm">
	<ul>
		<sec:authorize access="hasRole('PROBATION_TERM_LIST') or hasRole('ADMIN')">
			<li>
				<c:choose>
					<c:when test="${not empty courtCase}">
						<a class="listLink" href="${pageContext.request.contextPath}/probationTerm/list.html?courtCase=${courtCase.id}">
						<span class="visibleLinkLabel"><fmt:message key="listProbationTermsLink"/></span></a>
					</c:when>
					<c:otherwise>
						<a class="listLink" href="${pageContext.request.contextPath}/probationTerm/list.html?offender=${offender.id}">
						<span class="visibleLinkLabel"><fmt:message key="listProbationTermsLink"/></span></a>
					</c:otherwise>
				</c:choose>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>