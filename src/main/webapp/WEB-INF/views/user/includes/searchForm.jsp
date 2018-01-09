<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.user.msgs.userSearch" var="userSearchBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<form:form commandName="userSearchForm" class="searchForm">
	<p>
		<label><fmt:message key="userSearchTypeLabel" bundle="${userSearchBundle}"/></label>
	</p>
	<p>
		<c:choose>
			<c:when test="${'NAME' eq userSearchForm.type}">
				<input name="type" type="radio" id="searchTypeName" value="NAME" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input name="type" type="radio" id="searchTypeName" value="NAME"/>
			</c:otherwise>
		</c:choose>
		<form:label path="lastName" id="lastNameLabel"><fmt:message key="nameSearchTypeLabel" bundle="${userSearchBundle}"/></form:label>
		<form:input path="lastName" id="lastName"/>
		<form:label path="firstName" id="firstNameLabel"><fmt:message key="firstNameLabel" bundle="${userSearchBundle}"/></form:label>
		<form:input path="firstName" id="firstName"/>
		<form:errors path="lastName" cssClass="error"/>
	</p>
	<p>
		<c:choose>
			<c:when test="${'USERNAME' eq userSearchForm.type}">
				<input name="type" type="radio" id="searchTypeUsername" value="USERNAME" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input name="type" type="radio" id="searchTypeUsername" value="USERNAME"/>
			</c:otherwise>
		</c:choose>
		<form:label path="username" id="usernameLabel"><fmt:message key="usernameSearchTypeLabel" bundle="${userSearchBundle}"/></form:label>
		<form:input path="username" id="username"/>
		<form:errors path="username" cssClass="error"/>
		<form:errors path="type" cssClass="error"/>
	</p>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='searchLabel' bundle='${userSearchBundle}'/>"/>
	</p>
</form:form>