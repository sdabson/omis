<%--
 - Resources for offender search fields.
 -
 - Author: Stephen Abson
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty offenderSearchFieldsLib}">
	<c:set var="offenderSearchFieldsLib" value="${true}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offender/scripts/searchFields.js?VERSION=1"> </script>
</c:if>