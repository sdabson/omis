<%--
 - Table for parole board members.
 -
 - Author: Josh Divine
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleboardmember.msgs.paroleBoardMember">
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="startDateLabel"/></th>
			<th><fmt:message key="endDateLabel"/></th>
			<th><fmt:message key="nameLabel"/></th>
			<th><fmt:message key="titleLabel"/></th>
		</tr>
	</thead>
	<tbody id="paroleBoardMembers">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>