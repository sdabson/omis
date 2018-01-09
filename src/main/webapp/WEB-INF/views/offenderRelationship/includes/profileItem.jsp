<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.offenderrelationship.msgs.offenderRelationship" var="offenderRelationships"/>
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/offenderRelationship/list.html?offender=${offenderSummary.id}">
			<span>
				<fmt:message key="relationshipsProfileTitleLabel" bundle="${offenderRelationships}">
					<fmt:param value="${relationshipsCount}"></fmt:param>
				</fmt:message>
			</span>
		</a>
	</div>