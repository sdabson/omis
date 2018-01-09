<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Dec 6, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<span class="lastUpdatedBy">
	<c:out value="${updatable.updateSignature.userAccount.user.name.lastName}"/>,
	<c:out value="${updatable.updateSignature.userAccount.user.name.firstName}"/>
	(<c:out value="${updatable.updateSignature.userAccount.username}"/>)
</span>