<%--
 - Author: Josh Divine
 - Version: 0.1.0 (May 15, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.conviction.msgs.conviction">
	<ul>
		<li><a id="createConvictionLink" class="createLink" href="${pageContext.request.contextPath}/conviction/addConviction.html?convictionIndex=${convictionIndex}">
			<span class="visibleLinkLabel">
				<fmt:message key="createConvictionLink"/>
			</span></a>
		</li>
	</ul>
</fmt:bundle>