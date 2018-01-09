<!-- 
 - Author: Josh Divine
 - Version: 0.1.0 (Jan 19, 2017)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
	<ul>
		<li>
			<a id="createCourtCaseNoteLink" class="createLink" href="${pageContext.request.contextPath}/courtCase/addCourtCaseNote.html?courtCaseNoteIndex=${courtCaseNoteIndex}"><span class="visibleLinkLabel"><fmt:message key="addCourtCaseNoteLink"/></span></a>
		</li>
	</ul>
</fmt:bundle>