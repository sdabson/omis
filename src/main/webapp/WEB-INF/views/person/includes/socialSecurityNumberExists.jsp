<%--
 - OMIS - Offender Management Information System
 - Copyright (C) 2011 - 2017 State of Montana
 -
 - This program is free software: you can redistribute it and/or modify
 - it under the terms of the GNU General Public License as published by
 - the Free Software Foundation, either version 3 of the License, or
 - (at your option) any later version.
 -
 - This program is distributed in the hope that it will be useful,
 - but WITHOUT ANY WARRANTY; without even the implied warranty of
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 - GNU General Public License for more details.
 -
 - You should have received a copy of the GNU General Public License
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.
 --%>

<%--
  - View to report details of used social security number including social
  - security number value and person that uses it.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<fmt:setBundle basename="omis.person.msgs.person" var="personBundle"/>
<p>
	<c:choose>
		<c:when test="${not empty businessException.person}">
			<c:choose>
				<c:when test="${not empty businessException.socialSecurityNumber}">
					<fmt:formatNumber var="formattedSocialSecurityNumber" pattern="#" value="${businessException.socialSecurityNumber}"/>
					<fmt:message key="socialSecurityNumberUsedWithValueLabel" bundle="${personBundle}">
						<fmt:param value="${formattedSocialSecurityNumber}"/>
					</fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="socialSecurityNumberUsedByLabel"/>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${not empty businessException.person.name.middleName}">
					<fmt:message key="lastNameFirstNameMiddleInitialLabel" bundle="${personBundle}">
						<fmt:param value="${businessException.person.name.lastName}"/>
						<fmt:param value="${businessException.person.name.firstName}"/>
						<fmt:param value="${fn:substring(businessException.person.name.middleName, 0, 1)}"/>
					</fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="lastAndFirstNameLabel" bundle="${personBundle}">
						<fmt:param value="${businessException.person.name.lastName}"/>
						<fmt:param value="${businessException.person.name.firstName}"/>
					</fmt:message>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:if test="${not empty businessException.socialSecurityNumber}">
				<fmt:message key="socialSecurityNumberLabel" bundle="${personBundle}"/>
				<fmt:formatNumber pattern="#" value="${businessException.socialSecurityNumber}"/>
			</c:if>
		</c:otherwise>
	</c:choose>
</p>