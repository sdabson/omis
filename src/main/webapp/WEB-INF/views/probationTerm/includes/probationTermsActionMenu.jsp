<%-- 
 - Author: Josh Divine
 - Version: 0.1.0 (May 25, 2017)
 - Since: OMIS 3.0
 --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.probationterm.msgs.probationTerm">
	<ul>
		<sec:authorize access="hasRole('PROBATION_TERM_CREATE') or hasRole('ADMIN')">
			<c:if test="${not empty courtCase and empty probationTerm}">
				<li>
					<a class="createLink" href="${pageContext.request.contextPath}/probationTerm/create.html?courtCase=${courtCase.id}">
						<span class="visibleLinkLabel">
							<fmt:message key="createProbationTermLink"/></span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('PROBATION_TERM_VIEW') or hasRole('ADMIN')">
			<c:if test="${not empty probationTerm}">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/probationTerm/edit.html?probationTerm=${probationTerm.id}&courtCase=${courtCase.id}">
						<span class="visibleLinkLabel">
							<fmt:message key="viewEditProbationTermLink"/>
						</span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('PROBATION_TERM_REMOVE') or hasRole('ADMIN')">
			<c:if test="${not empty probationTerm}">
				<li>
					<a class="removeLink" href="${pageContext.request.contextPath}/probationTerm/remove.html?probationTerm=${probationTerm.id}&amp;courtCase=${courtCase.id}">
						<span class="visibleLinkLabel">
							<fmt:message key="removeProbationTermLink"/>
						</span>
					</a>
				</li>
			</c:if>
		</sec:authorize>
	</ul>
</fmt:bundle>