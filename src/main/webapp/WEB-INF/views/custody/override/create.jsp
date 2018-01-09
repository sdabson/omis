<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.custody.msgs.custodyReview">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/specialNeed/scripts/detail.js"> </script>
<title><fmt:message key="custodyOverrideCreateTitle"></fmt:message></title>
</head>
<body>
	<h1><fmt:message key="custodyOverrideCreateTitle"></fmt:message></h1>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<form:form commandName="custodyOverrideForm" class="editForm">
			<fieldset>
				<p>
					<span class="fieldGroup">
						<form:label path="custodyLevel" class="fieldLabel"><fmt:message key="custodyLevelLabel"/></form:label>
						<form:select path="custodyLevel">
							<form:option value="">...</form:option>
							<form:options items="${levels}" itemLabel="name" itemValue="id"/>
						</form:select>
						<form:errors cssClass="error" path="custodyLevel"/>
					</span>
				</p>
			</fieldset>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='custodyOverrideSaveLabel'/>"/>
		</p>
		</form:form>
</body>
</fmt:bundle>
</html>