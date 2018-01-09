<%--
 - Resources for contact summary.
 - 
 - Author: Joel Norris
 - Version: 0.1.0 (January 17, 2017)
 - Since: OMIS 3.0
--%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty contactSummaryLIB}">
	<c:set var="contactSummaryLIB" value="true" scope="request"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/contact/style/contactSummary.css?VERSION=1"/>
</c:if>