<%--
 - Author: Ryan Johns
 - Version: 0.1.0 (Jan 02, 2014)
 - Since: OMIS 3.0
 --%>
<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery-ui-custom.min.js"> </script>
<script src="${pageContext.request.contextPath}/resources/common/scripts/ServerConfig.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/EventRunner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/caseload/scripts/list.js"></script>
console("here ltbc");
<style>
	.nestedListTable {
		max-height: 300px;
		width:100%;
		overflow:auto;
		display:inline-block;
	}
	
	#caseMemberTable {
		margin-left: 2%;
    	width: 97%;
	}
	
	td {
		vertical-align:top;
	}
</style>

<fmt:bundle basename="omis.caseload.msgs.caseload">
<c:forEach var="cas" items="${caseLoadList}">
	<tr>
		<td>		
			<a href="${pageContext.request.contextPath}/caseload/edit.html?caseload=${cas.id}"><fmt:message key="edit"/></a>
		<td>
			<c:out value="${cas.title}"/>
		</td>
		<td>
			<c:out value="${cas.description}"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<table id="caseMemberTable">
				<tr><td>
					<div><fmt:message key="caseLoadOffendersTitleLabel"/></div>
					<table id="${cas.id}" class="offenderCaseList nestedListTable">
					<thead><tr>
						<td></td>
						<td><fmt:message key="offenderNameLabel"/></td>
						<td><fmt:message key="dateRangeLabel"/></td>
					</thead>
					<tbody>
					</tbody>
					</table>
				</td>
				<td>
					<div><fmt:message key="caseLoadWorkersTitleLabel"/></div>
					<table id="${cas.id}" class="workerCaseList nestedListTable">
						<thead><tr>
							<td></td>
							<td><fmt:message key="workerNameLabel"/></td>
							<td><fmt:message key="capacityNameLabel"/></td>
							<td><fmt:message key="dateRangeLabel"/></td>
						</thead>
						<tbody>
						</tbody>
					</table>
				</td></tr>
			</table>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>