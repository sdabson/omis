<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Aug 27, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
	<ul>			 
		<li>
			<a class="createLink" id="createAccommodationNoteLink" href="${pageContext.request.contextPath}/adaAccommodation/addAccommodationNote.html"><span class="visibleLinkLabel"><fmt:message key="accommodationNoteCreateLabel"/></span></a>					
		</li>
	</ul>
</fmt:bundle>