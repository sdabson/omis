<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Apr 05, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.employment.msgs.employment">
	<ul>
	<li class="taskLinkItem">
		<a class="createLink" href="${pageContext.request.contextPath}/employerSearch/create.html">
			<fmt:message key="createEmployerLabel"/>
		</a>
	</li>
	<li>
		<a class="printLink">
			<span class="visibleLinkLabel">
				<fmt:message key="printResultsLabel"/>
			</span>
		</a>
	</li>
	<li>
		<a class="helpLink">
			<span class="visibleLinkLabel">
				<fmt:message key="searchHelpLabel"/>
			</span>
		</a>
	</li>	
</ul>
</fmt:bundle>