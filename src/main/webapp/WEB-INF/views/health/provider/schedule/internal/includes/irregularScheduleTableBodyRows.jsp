<%-- Author: Ryan Johns
   - Version: 0.1.0 (Jul 10, 2014)
   - Since OMIS 3.0--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="isdi" items="${irregularScheduleDays}" varStatus="status">
		<c:if test="${not empty isdi}">
			<c:set var="mindex" value="${index + status.index}"  scope="request"/>
			<c:set var="irregularScheduleDayItem" value="${isdi}" scope="request"/>
			<jsp:include page="irregularScheduleTableBodyContent.jsp"/>
		</c:if>
	</c:forEach>