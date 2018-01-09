<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (Feb 9, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:bundle basename="omis.employment.msgs.employment">
	<ul>
		<sec:authorize access="hasRole('EMPLOYMENT_TERM_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/employment/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listEmploymentLink"/></span>
				</a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('EMPLOYMENT_TERM_EDIT') or hasRole('ADMIN')">
			<c:if test="${not empty employmentTerm and employerStatus==true}">
				<li>
					<a class="listLink" href="${pageContext.request.contextPath}/employment/changeEmployer.html?offender=${offender.id}&employmentTerm=${employmentTerm.id}"><span class="visibleLinkLabel"><fmt:message key="changeEmployerLink"/></span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
	</ul>
</fmt:bundle>