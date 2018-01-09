<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 17, 2016)
 - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.stg.msgs.stg">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/stg/list.html?offender=${offenderSummary.id}">
			<span>
				<fmt:message key="stgCountLabel">
    				<fmt:param value="${stgCount}"/>
    			</fmt:message>
			</span>
    	</a>
    </div>
</fmt:bundle>