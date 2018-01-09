<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 18, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.user.msgs.userAccount">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/user/admin/userAccount/list.html?user=${offenderSummary.id}">
			<span>
	    		<fmt:message key="userAccountLinkLabel"/>
	    	</span>
	    </a>
    	<div class="moduleInfo">
    		<span>
    			<fmt:message key="userAccountCountLabel">
    				<fmt:param value="${userAccountCount}"/>
    			</fmt:message>
    		</span>
    	</div>
    </div>
</fmt:bundle>