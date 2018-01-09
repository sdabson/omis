<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleboardcondition.msgs.paroleBoardCondition">
	<ul>
		<li>
			<sec:authorize access="hasRole('PAROLE_BOARD_CONDITION_VIEW') or hasRole('ADMIN')">
				<a class="viewEditLink" href="${pageContext.request.contextPath}/paroleBoardCondition/edit.html?paroleBoardAgreement=${paroleBoardAgreement.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}" /></span></a>
			</sec:authorize>
		</li>
		<li>
			<sec:authorize access="hasRole('PAROLE_BOARD_CONDITION_REMOVE') or hasRole('ADMIN')">
				<a class="removeLink" href="${pageContext.request.contextPath}/paroleBoardCondition/remove.html?paroleBoardAgreement=${paroleBoardAgreement.id}"><span class="visibleLinkLabel"><fmt:message key="removeLink" bundle="${commonBundle}" /></span></a>
			</sec:authorize>
		</li>
		<li>
			<sec:authorize access="hasRole('PAROLE_BOARD_CONDITION_EDIT') or hasRole('ADMIN')">
				<a class="viewEditLink" href="${pageContext.request.contextPath}/paroleBoardCondition/addStandard.html?paroleBoardAgreement=${paroleBoardAgreement.id}"><span class="visibleLinkLabel"><fmt:message key="createEditStandardLink"/></span></a>
			</sec:authorize>
		</li>
		<c:forEach items="${conditionGroups}" var="conditionGroup">
			<li>
				<sec:authorize access="hasRole('PAROLE_BOARD_CONDITION_EDIT') or hasRole('ADMIN')">
					<a class="viewEditLink" href="${pageContext.request.contextPath}/paroleBoardCondition/addSpecialGroup.html?paroleBoardAgreement=${paroleBoardAgreement.id}&conditionGroup=${conditionGroup.id}"><span class="visibleLinkLabel"><fmt:message key="createSpecialLink"><fmt:param value="${conditionGroup.name}"/></fmt:message></span></a>
				</sec:authorize>
			</li>
		</c:forEach>
		<sec:authorize access="hasRole('PAROLE_BOARD_CONDITION_LIST') or hasRole('ADMIN')">
			<li>
				<a class="viewLink viewConditionLink" id="viewConditions"><fmt:message key="conditionListLabel"/>
					<input type="hidden" id="paroleBoardAgreementId" value="${paroleBoardAgreement.id}" />
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>