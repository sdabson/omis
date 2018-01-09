<%-- Author: Ryan Johns
   - Version: 0.1.0 (Jul 12, 2017)
   - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.caseload.msgs.caseload">   
<ul>
	<li>
		<a class="listLink" href="${pageContext.request.contextPath}/caseload/create.html?caseWorker=${userAccount.id}">
			<fmt:message key="createCaseloadLabel"/>
		</a>
	</li>
</ul>
</fmt:bundle>