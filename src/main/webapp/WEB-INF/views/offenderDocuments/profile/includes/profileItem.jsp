<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 15, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.document.msgs.document">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/documents/profile.html?offender=${offenderSummary.id}">
			<span>
    			<fmt:message key="documentLinkLabel"/>
    		</span>
    	</a>
    	<div class="moduleInfo">
    		<span>
	    		<fmt:message key="documentCountLabel">
	    			<fmt:param value="${documentCount}"/>
	    		</fmt:message>
	    	</span>
    	</div>
    </div>
</fmt:bundle>