<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
<form:form commandName="referralLabWorkForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="requiredLabsLegendLabel"/></legend>
			<a href="${pageContext.request.contextPath}/health/referral/internal/addLabWorker.html" class="createLink" id="addLabWorkLink">
				<span class="visibleLinkLabel"><fmt:message key="createLabWorkerLink"/></span></a>	
		<jsp:include page="/WEB-INF/views/health/referral/internal/schedule/includes/requiredLabWorkTable.jsp"/>
	</fieldset>
	<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
</form:form>
</fmt:bundle>