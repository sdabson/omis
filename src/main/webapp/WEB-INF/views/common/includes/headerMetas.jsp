<%--Include header metas if not loaded.
 - Author: Ryan Johns
 - Version: 0.1.0 (Oct 21, 2014)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${empty headerMetas}">
		<c:set var="headerMetas" value="true" scope="request"/>
		<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1"/>
        <meta http-equiv="pragma" content="no-cache"/>
        <meta http-equiv="cache-control" content="no-cache"/>
        <meta http-equiv="expires" content="0"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	</c:if>