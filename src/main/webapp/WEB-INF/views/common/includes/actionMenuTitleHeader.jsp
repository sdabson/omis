<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="titleHeader">
	<a href="${param.actionMenuLink}" id="${param.id}" class="actionMenuItem"></a>
	<c:out value="${param.title}"></c:out>
</div>
