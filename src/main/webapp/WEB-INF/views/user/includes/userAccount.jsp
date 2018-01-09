<%--
 - Display of user account.
 -
 - Variable "userAccount" be in scope and hold a user account.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:out value="${userAccount.user.name.lastName}"/>, <c:out value="${userAccount.user.name.firstName}"/><c:if test="${not empty userAccount.user.name.middleName}"> <c:out value="${fn:substring(userAccount.user.name.middleName, 0, 1)}"/>.</c:if><c:if test="${not empty userAccount.user.name.suffix}"> <c:out value="${userAccount.user.name.suffix}"/></c:if> (<c:out value="${userAccount.username}"/>)