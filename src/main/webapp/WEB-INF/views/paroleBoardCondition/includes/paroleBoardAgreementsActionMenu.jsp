<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.paroleboardcondition.msgs.paroleBoardCondition">
	<ul>
		<sec:authorize access="hasRole('PAROLE_BOARD_CONDITION_CREATE') or hasRole('ADMIN')">
		<c:forEach var="category" items="${categories}">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/paroleBoardCondition/create.html?offender=${offender.id}&paroleBoardAgreementCategory=${category.id}">
				<span class="visibleLinkLabel">
					<fmt:message key="createAgreementLink">
						<fmt:param value="${category.name}" />
					</fmt:message>
				</span></a>
			</li>
		</c:forEach>
		</sec:authorize>
	</ul>
</fmt:bundle>