<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 14, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.demographics.msgs.demographics">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/offender/demographics/edit.html?offender=${offenderSummary.id}">
			<span>
  				<fmt:message key="offenderDemographicsLink"/>
  			</span>
  		</a>
  		<div class="moduleInfo">
  			<span>
  			</span>
  		</div>
  	</div>
</fmt:bundle>