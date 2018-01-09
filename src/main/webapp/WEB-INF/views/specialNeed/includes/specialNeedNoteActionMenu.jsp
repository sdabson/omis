<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Aug 31, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.specialneed.msgs.specialNeed">
	<ul>
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/specialNeed/list.html?specialNeed=${specialNeed.id}">
					<fmt:message key="specialNeedNotesTitle"/>
				</a>
			</li>
	</ul>
</fmt:bundle>