<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<span class="fieldGroup">
	<form:label path="victimFields.registerDate" class="fieldLabel"><fmt:message key="registerDateLabel" bundle="${victimBundle}"/></form:label>
	<form:input id="victimFieldsRegisterDate" path="victimFields.registerDate" class="date"/>
	<form:errors path="victimFields.registerDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="victimFields.packetSent" class="fieldLabel"><fmt:message key="packetSentLabel" bundle="${victimBundle}"/></form:label>
	<form:checkbox id="victimFieldsPacketSent" path="victimFields.packetSent"/>
	<form:errors path="victimFields.packetSent" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="victimFields.packetSendDate" class="fieldLabel"><fmt:message key="packetSendDateLabel" bundle="${victimBundle}"/></form:label>
	<form:input id="victimFieldsPacketSendDate" path="victimFields.packetSendDate" class="date"/>
	<form:errors path="victimFields.packetSendDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="victimFields.victim" class="fieldLabel"><fmt:message key="victimFlagLabel" bundle="${victimBundle}"/></form:label>
	<form:checkbox id="victimFieldsVictim" path="victimFields.victim"/>
	<form:errors path="victimFields.victim" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="victimFields.docRegistered" class="fieldLabel"><fmt:message key="docRegisteredLabel" bundle="${victimBundle}"/></form:label>
	<form:checkbox id="victimFieldsDocRegistered" path="victimFields.docRegistered"/>
	<form:errors path="victimFields.docRegistered" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="victimFields.business" class="fieldLabel"><fmt:message key="businessLabel" bundle="${victimBundle}"/></form:label>
	<form:checkbox id="victimFieldsBusiness" path="victimFields.business"/>
	<form:errors path="victimFields.business" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="victimFields.vineRegistered" class="fieldLabel"><fmt:message key="vineRegisteredLabel" bundle="${victimBundle}"/></form:label>
	<form:checkbox id="victimFieldsVineRegistered" path="victimFields.vineRegistered"/>
	<form:errors path="victimFields.vineRegistered" cssClass="error"/>
</span>