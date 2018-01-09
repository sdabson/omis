<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.visitation.msgs.facilityVisitation">
		<form:form commandName="facilityVisitationLogForm" class="filterForm" method="post" action="${pageContext.request.contextPath}/visitation/facility/list.html">
			<fieldset class="foregroundUltraLight">
				<legend class="foregroundLight"><fmt:message key="facilityLogFilterLegend"/></legend>
				<div>
					<span class="fieldGroup">
						<form:label class="fieldLabel" path="facility"><fmt:message key="facilityLabel"/></form:label>
						<form:select path="facility">
							<form:option value="">...</form:option>
							<form:options items="${facilities}" itemValue="id" itemLabel="name"/>
						</form:select>
					</span>
				</div>
				<div>
					<span class="fieldGroup">
						<label class="fieldLabel" for="date"><fmt:message key="dateLabel"/></label>
						<form:input id="date" path="date" class="date"/>
					</span>
				</div>
				<p class="buttons">
					<input type="submit" value="Search"/>
				</p>
			</fieldset>
		</form:form>
	</fmt:bundle>
</html>