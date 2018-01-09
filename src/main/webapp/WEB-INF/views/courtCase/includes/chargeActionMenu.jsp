<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (November 17, 2014)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
	<ul>
		<sec:authorize access="hasRole('CHARGE_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/courtCase/listCharges.html?defendant=${defendant.id}">
					<fmt:message key="listChargesTitle"/>
				</a>
			</li>
		</sec:authorize>
		<li></li>
		<li></li>
	</ul>
</fmt:bundle>