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
 - Shows information about product.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.msgs.general" var="generalBundle"/>
<fmt:bundle basename="omis.msgs.about">
<head>
	<title><fmt:message key="aboutTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/about.css"/>
</head>
<body>
	<div id="productInformation" class="information">
		<span id="productName"><fmt:message key="productName" bundle="${generalBundle}"/></span>
		<span id="productFullName"><fmt:message key="productFullName" bundle="${generalBundle}"/></span>
		<span id="productVersion">
			<fmt:message key="productVersionLabel">
				<fmt:param><fmt:message key="productVersion" bundle="${generalBundle}"/></fmt:param>
			</fmt:message>
		</span>
		<span id="productAuthor">
			<fmt:message key="productAuthorLabel">
				<fmt:param><fmt:message key="productAuthor" bundle="${generalBundle}"/></fmt:param>
			</fmt:message>
		</span>
		<span id="productCopyright"><fmt:message key="productCopyright" bundle="${generalBundle}"/></span>
		<span id="productWarrantyAndRedistribution"><fmt:message key="productWarrantyAndRedistribution" bundle="${generalBundle}"/></span>
	</div>
	<sec:authorize access="hasRole('ADMIN')">
	<table id="systemInformation" class="information">
		<tbody>
			<tr>
				<th><fmt:message key="contextPathHeader"/></th>
				<td><c:out value="${pageContext.servletContext.contextPath}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="serverInformationHeader"/></th>
				<td><c:out value="${pageContext.servletContext.serverInfo}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="servletVersionHeader"/></th>
				<td>
					<fmt:message key="versionString">
						<fmt:param><c:out value="${pageContext.servletContext.majorVersion}"/></fmt:param>
						<fmt:param><c:out value="${pageContext.servletContext.minorVersion}"/></fmt:param>
					</fmt:message>
				</td>
			</tr>
			<tr>
				<th><fmt:message key="localAddressHeader"/></th>
				<td><c:out value="${pageContext.request.localAddr}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="localNameHeader"/></th>
				<td><c:out value="${pageContext.request.localName}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="localPortHeader"/></th>
				<td><c:out value="${pageContext.request.localPort}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="protocolHeader"/></th>
				<td><c:out value="${pageContext.request.protocol}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="schemeHeader"/></th>
				<td><c:out value="${pageContext.request.scheme}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="javaClassPathHeader"/></th>
				<td><c:out value="${systemProperties['java.class.path']}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="javaHomeHeader"/></th>
				<td><c:out value="${systemProperties['java.home']}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="javaVendorHeader"/></th>
				<td><c:out value="${systemProperties['java.vendor']}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="javaVersionHeader"/></th>
				<td><c:out value="${systemProperties['java.version']}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="osArcHeader"/></th>
				<td><c:out value="${systemProperties['os.arch']}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="osNameHeader"/></th>
				<td><c:out value="${systemProperties['os.name']}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="osVersionHeader"/></th>
				<td><c:out value="${systemProperties['os.version']}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="freeMemoryHeader"/></th>
				<td>
					<fmt:message key="memoryString">
						<fmt:param>
							<fmt:formatNumber value="${freeMemory}"/>
						</fmt:param>
					</fmt:message>
				</td>
			</tr>
			<tr>
				<th><fmt:message key="totalMemoryHeader"/></th>
				<td>
					<fmt:message key="memoryString">
						<fmt:param>
							<fmt:formatNumber value="${totalMemory}"/>
						</fmt:param>
					</fmt:message>
				</td>
			</tr>
			<tr>
				<th><fmt:message key="maxMemoryHeader"/></th>
				<td>
					<fmt:message key="memoryString">
						<fmt:param>
							<fmt:formatNumber value="${maxMemory}"/>
						</fmt:param>
					</fmt:message>
				</td>
			</tr>
			<tr>
				<th><fmt:message key="availableProcessorsHeader"/></th>
				<td><c:out value="${availableProcessors}"/></td>
			</tr>
		</tbody>
	</table>
	</sec:authorize>
</body>
</fmt:bundle>
</html>