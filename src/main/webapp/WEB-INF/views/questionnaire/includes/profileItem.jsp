<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/questionnaire/administeredQuestionnaireList.html?offender=${offenderSummary.id}&questionnaireType=">
			<span>
				<fmt:message key="administeredQuestionnaireCountLabel">
					<fmt:param><c:out value="${administeredQuestionnaireCount}"/></fmt:param>
				</fmt:message>
			</span>
		</a>
	</div>
</fmt:bundle>