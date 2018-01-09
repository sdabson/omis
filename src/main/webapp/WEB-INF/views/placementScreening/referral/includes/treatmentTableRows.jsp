<%-- Author: Ryan Johns
   - Version: 0.1.0 (Mar 16, 2015)
   - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${screeningItems}" var="treatmentScreeningItem" varStatus="status">
		<c:set var="index" value="${status.index + index}" scope="request"/>
		<c:set var="treatmentScreeningItem" value="${treatmentScreeningItem}" scope="request"/>
		<jsp:include page="treatmentTableBodyContent.jsp"/>
</c:forEach>