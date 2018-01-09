<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="common"/>
<fmt:bundle basename="omis.user.msgs.userGroup">
<form:form commandName="userGroupForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
		<form:label path="name" class="fieldLabel">
			<fmt:message key="nameLabel"/></form:label>
		<form:input path="name" class="medium"/>
		<form:errors path="name"/>
		</span>
		<span class="fieldGroup">
		<form:label path="description" class="fieldLabel">
			<fmt:message key="descriptionLabel"/></form:label>
		<form:input path="description" class="large"/>
		<form:errors path="description"/>
		</span>
		<span class="fieldGroup">
		<form:label path="sortOrder" class="fieldLabel">
			<fmt:message key="sortOrderLabel"></fmt:message>
		</form:label>
		<form:input path="sortOrder" class="veryShortNumber"/>
		<form:errors path="sortOrder"/>
		</span>
		<span class="fieldGroup">
		<form:label path="valid" class="fieldLabel">
			<fmt:message key="validLabel"></fmt:message>
		</form:label>
		<form:checkbox path="valid"/>
		<form:errors path="valid"/>
		</span>
		<span class="fieldGroup">
		<form:label path="roles" class="fieldLabel">
			<fmt:message key="rolesLabel"/></form:label>
		<span id="userRoles">
		<form:checkboxes items="${roles}" path="roles"
		                 itemValue="id" itemLabel="description"
		                 class="fieldValue"/>
		</span>
		</span>
		<sec:authorize access="hasRole('ADMIN') or hasRole('USER_ACCOUNT_ADMIN')">
			<c:set var="canEditUserAccount"	value="${true}"/>	
		</sec:authorize>
		<span id="groupMembers" class="fieldGroup">
		<label class="fieldLabel"><fmt:message key="membersLabel"/></label>
		<c:forEach var="member" items="${userGroupForm.members}">
			<span class="member">
				<c:out value="${member.user.name.lastName}, ${member.user.name.firstName}"/>
					(<c:choose>
						<c:when test="${canEditUserAccount}"><a href="${pageContext.request.contextPath}/user/admin/userAccount/edit.html?account=${member.id}">${member.username}</a></c:when>
						<c:otherwise>${member.username}</c:otherwise>
					</c:choose>)
			</span>
		</c:forEach>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${common}'/>"/>
	</p>
</form:form>
</fmt:bundle>