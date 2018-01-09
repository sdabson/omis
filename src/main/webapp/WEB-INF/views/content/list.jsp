<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.content.msgs.content">
<head>
	<title><fmt:message key="contentListingTitle"/></title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/listCSS.jsp"/>
</head>
<body>
	<h1><fmt:message key="contentListingTitle"/></h1>
	<table class="listTable">
		<thead>
			<tr>
			<th><fmt:message key="urlHeader"/></th>
			<th><fmt:message key="nameHeader"/></th>
			<th><fmt:message key="descriptionHeader"/></th>
			<th><fmt:message key="requiredAuthorizationHeader"/></th>
			<th><fmt:message key="requestContentTypeHeader"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="content" items="${contents}">
			<fmt:setBundle basename="${content.messageBundle}" var="messageBundle" />
			<tr>
			<td rowspan="2"><a href="${pageContext.request.contextPath}${content.url}">
					<c:out value="${content.url}"/></a></td>
			<td rowspan="2">
				<fmt:message key="${content.nameKey}" bundle="${messageBundle}" />
			</td>
			<td rowspan="2">
				<fmt:message key="${content.descriptionKey}" bundle="${messageBundle}" />
			</td>
			<td><c:out value="${content.requiredAuthorization}"/></td>
			<td><c:out value="${content.type.name}"/></td>
			</tr>
			<tr>
			<td><c:out value="${content.className}"/></td>
			<td><c:out value="${content.methodName}"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</fmt:bundle>
</html>