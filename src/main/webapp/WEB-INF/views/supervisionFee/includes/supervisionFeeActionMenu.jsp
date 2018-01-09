<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (December 8, 2014)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.supervisionfee.msgs.supervisionFee">
	<ul>
		<li>
			<a class="listLink" href="${pageContext.request.contextPath}/supervisionFee/list.html?offender=${offender.id}">
				<fmt:message key="supervisionFeesTitle"/>
			</a>
		</li>
	</ul>
</fmt:bundle>