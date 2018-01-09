<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 14, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.offenderdetails.msgs.offenderDetails">
	<div class="profileItem">
			<a href="${pageContext.request.contextPath}/offender/demographics/edit.html?offender=${offenderSummary.id}">
				<span>
  	  				<fmt:message key="offenderDemographicsLink"/>
  	  			</span>
  	  		</a>
  	</div>
 </fmt:bundle>