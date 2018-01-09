<!-- 
 - Author: Annie Jacques
 - Version: 0.1.0 (Aug 11, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:bundle basename="omis.disciplinaryCode.msgs.disciplinaryCode">
<form:form commandName="codeDateForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="viewDisciplinaryCodesTitle"/></legend>
		
		<span class="line">
			<form:radiobutton id="useEffectiveDate" class="fieldValue" path="usingEffectiveDate"  value="true" checked ="checked"/>
			<form:label path="effectiveDate" class="fieldLabel">
				<fmt:message key="effectiveDateLabel"/>
			</form:label>
			<form:input path="effectiveDate" class="date"/>
			<form:errors path="effectiveDate" cssClass="error"/>
		</span>
		
		<span class="line">
			<form:radiobutton id="useDateRange" class="fieldValue" path="usingEffectiveDate"  value="false"/>
			
			<form:label path="fromDate" class="fieldLabel">
				<fmt:message key="fromDateLabel"/>
			</form:label>
			<form:input path="fromDate" class="date"/>
			<form:errors path="fromDate" cssClass="error"/>
			<form:label path="toDate" class="fieldLabel">
				<fmt:message key="toDateLabel"/>
			</form:label>
			<form:input path="toDate" class="date"/>
			<form:errors path="toDate" cssClass="error"/>
		</span>
		
		<span class="lineButton">
			<input type="submit" value="<fmt:message key='updateListLabel'/>"/>
		</span>

	</fieldset>
</form:form>
</fmt:bundle>