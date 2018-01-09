<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (November 17, 2014)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.alert.msgs.alert">
	<ul>
		<sec:authorize access="hasRole('ALERT_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/alert/list.html?offender=${offender.id}">
					<fmt:message key="alertsTitle"/>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>