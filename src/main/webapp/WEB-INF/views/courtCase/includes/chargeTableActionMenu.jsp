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
			<a id="createChargeLink" class="createLink" href="${pageContext.request.contextPath}/courtCase/addCharge.html?chargeIndex=${chargeIndex}"><span class="visibleLinkLabel"><fmt:message key="addChargeLink"/></span></a>
		</li>
	</ul>
</fmt:bundle>