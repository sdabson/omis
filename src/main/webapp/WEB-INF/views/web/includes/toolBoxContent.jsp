<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.web.msgs.toolBox" var="toolBoxBundle"/>
<ul id="toolBoxItems">
	<li><a id="toolBoxSearchItemLink" class="toolBoxItemLink">
		<fmt:message key="searchLabel" bundle="${toolBoxBundle}"/></a>
		<div id="toolBoxSearchItemContent" class="toolBoxItemContent"></div>
	</li>
	
</ul>