<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Aug 27, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
<ul>
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/adaAccommodation/list.html?accommodation=${accommodation.id}">
					<fmt:message key="adaAccommodationNotesTitle"/>
				</a>
			</li>
</ul>
</fmt:bundle>