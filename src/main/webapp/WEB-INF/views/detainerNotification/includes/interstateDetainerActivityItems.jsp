	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
<a id="createInterstateDetainerActivityItemLink" href="#" class="createLink addLink">
	<span class="visibleLinkLabel">
		<fmt:message key="addActivityLink"/>
	</span>
</a>
<span id="interstateDetainerActivityItems">
	<c:forEach var="interstateDetainerActivityItem" items="${interstateDetainerActivityItems}" varStatus="status">
		<c:set var="interstateDetainerActivityItem" value="${interstateDetainerActivityItem}" scope="request"/>
		<c:set var="interstateDetainerActivityItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty interstateDetainerActivityItem.itemOperation}">
			<jsp:include page="interstateDetainerActivityItemContent.jsp"/>
		</c:if>
	</c:forEach>
</span>
</fmt:bundle>