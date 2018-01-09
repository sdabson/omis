<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
	<span id="interstateDetainerActivityItem${interstateDetainerActivityItemIndex}" class="inFieldSetDiv">
		<a class="removeLink" id="removeInterstateDetainerActivityLink${interstateDetainerActivityItemIndex}"
		href="${pageContext.request.contextPath}/detainerNotification/removeInterstateDetainerActivity.html?detainer=${detainer.id}">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="interstateDetainerActivityId${interstateDetainerActivityItemIndex}"
				name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].interstateDetainerActivity"
				value="${interstateDetainerActivityItem.interstateDetainerActivity.id}"/>
		<form:errors path="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].interstateDetainerActivity" cssClass="error"/>
		<input type="hidden" id="interstateDetainerActivityOperation${interstateDetainerActivityItemIndex}"
				name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].itemOperation" value="${interstateDetainerActivityItem.itemOperation}"/>
		<form:errors path="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].itemOperation" cssClass="error"/>
		
		<span class="fieldGroup">
			<label for="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].activityDate" class="fieldLabel">
				<fmt:message key="activityDateLabel"/>
			</label>
			<fmt:formatDate var="interstateDetainerActivityDate" value="${interstateDetainerActivityItem.activityDate}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${interstateDetainerActivityDate}" id="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].activityDate"
					name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].activityDate" class="date" />
			<form:errors path="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].activityDate" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label for="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].category" class="fieldLabel">
				<fmt:message key="categoryLabel"/>
			</label>
			<select id="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].category" name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].category">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${detainerActivityCategories}" var="cat">
					<option value="${cat.id}" ${cat eq interstateDetainerActivityItem.category ? 'selected="selected"' : ''} >
						<c:out value="${cat.name}${not empty cat.description ? ' - ' : ''}${cat.description}"/>
					</option>
				</c:forEach>
			</select>
			<form:errors path="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].category" cssClass="error"/>
		</span>
		
		
		<span class="fieldGroup">
			<label for="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].direction" class="fieldLabel">
				<fmt:message key="sentReceivedLabel"/>
			</label>
			<select name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].direction" id="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].direction">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${directions}" var="direction">
					<option value="${direction}" ${direction == interstateDetainerActivityItem.direction ? 'selected="selected"' : ''}>
						<fmt:message key="direction${direction.name}Label"/>
					</option>
				</c:forEach>
			</select>
			<form:errors path="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].direction" cssClass="error"/>
		</span>
		
		
		
		
		
		<%-- Document --%>
		<span class="fieldGroup">
			<label for="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].title"
					name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].title"
				value="${interstateDetainerActivityItems[interstateDetainerActivityItemIndex].title}" />
			<form:errors path="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].title" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label for="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].fileDate" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="interstateDetainerActivityDate" value="${interstateDetainerActivityItem.fileDate}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${interstateDetainerActivityDate}" id="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].fileDate"
					name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].fileDate" class="date" />
			<form:errors path="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].fileDate" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${interstateDetainerActivityItemIndex}" href="#" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		
		<span id="documentTagItems${interstateDetainerActivityItemIndex}">
			<c:forEach var="documentTagItem" items="${interstateDetainerActivityItems[interstateDetainerActivityItemIndex].documentTagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.itemOperation}">
					<jsp:include page="documentTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		
		
		<span class="fieldGroup">
			<label for="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			
			<c:choose>
				<c:when test="${not empty interstateDetainerActivityItem.document}">
					<c:set var="filename" value = "${interstateDetainerActivityItems[interstateDetainerActivityItemIndex].document.filename}"/>
					<fmt:formatDate value="${interstateDetainerActivityItems[interstateDetainerActivityItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="interstateDetainerActivityDownloadLink${interstateDetainerActivityItemIndex}"
		 					href="${pageContext.request.contextPath}/detainerNotification/retrieveFile.html?document=${interstateDetainerActivityItems[interstateDetainerActivityItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].data"
						id="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].data"
						value="${interstateDetainerActivityItems[interstateDetainerActivityItemIndex].data}"/>
					<input type="hidden" name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].document"
						id="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].document"
						value="${interstateDetainerActivityItems[interstateDetainerActivityItemIndex].document.id}"/>
				</c:when>
				<c:otherwise>
						<input id="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].data" type="file"
								name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].fileExtension"
					id="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].fileExtension"
					value="${interstateDetainerActivityItems[interstateDetainerActivityItemIndex].fileExtension}"/>
			<form:errors path="interstateDetainerActivityItems[${interstateDetainerActivityItemIndex}].data" cssClass="error"/>
		</span>
		
		
		
		
		
	</span>
</fmt:bundle>