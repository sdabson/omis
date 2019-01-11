<?xml version="1.0" encoding="UTF-8"?>

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
  - Screen to create/edit staff assignments.
  -
  - Author: Stephen Abson
  - Author: Yidong Li
  - Author: Joel Norris
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.staff.msgs.staff" var="staffBundle"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>
			<c:choose>
				<c:when test="${not empty staffAssignment}">
					<fmt:message key="editStaffAssignmentTitle" bundle="${staffBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="createStaffAssignmentTitle" bundle="${staffBundle}"/>
				</c:otherwise>
			</c:choose>
		</title>
		<script type="text/javascript">
				/* <![CDATA[ */
					<c:choose>
						<c:when test="${allowEnhancedImageEditor}">
							var allowEnhancedImageEditor = ${allowEnhancedImageEditor};
						</c:when>
						<c:otherwise>
							var allowEnhancedImageEditor = false;
						</c:otherwise>
					</c:choose>
				/* ]]> */
		</script>
		<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/formResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/interactiveImageResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/enhancedImageUploaderResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/mediaResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/staff/scripts/staffAssignment.js"> </script>
	</head>
	<body>
		<h1>
			<a class="actionMenuItem" id="staffAssignmentCreateEditScreenActionMenuLink" href="${pageContext.request.contextPath}/staffAssignment/staffAssignmentCreateEditScreenActionMenu.html"></a>
			<c:choose>
				<c:when test="${not empty staffAssignment}">
					<fmt:message key="editStaffAssignmentTitle" bundle="${staffBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="createStaffAssignmentTitle" bundle="${staffBundle}"/>
				</c:otherwise>
			</c:choose>
		</h1>
		<jsp:include page="includes/editForm.jsp"/>
	</body>
</html>