<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.boardhearing.msgs.boardHearing">
	<ul>
		<sec:authorize access="hasRole('BOARD_HEARING_CREATE') or hasRole('BOARD_HEARING_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createBoardHearingNoteItemLink" class="createLink" href="${pageContext.request.contextPath}/boardHearing/createBoardHearingNoteItem.html?boardHearingNoteItemIndex=${boardHearingNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addBoardHearingNoteLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>