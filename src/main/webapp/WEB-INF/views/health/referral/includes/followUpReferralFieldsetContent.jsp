<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
		<span class="fieldGroup">
			<form:label path="followUpReferralType" class="fieldLabel">
				<fmt:message key="followUpReferralTypeLabel" bundle="${healthBundle}"/></form:label>
			<form:radiobutton id="followUpReferralType.NOT_REQUIRED" path="followUpReferralType" value="${null}" class="fieldValue"/>
			<label for="followUpReferralType.NOT_REQUIRED" class="fieldLabelValue"><fmt:message key="notRequiredLabel" bundle="${healthBundle}"/></label>
			<c:forEach var="followUpReferralType" items="${followUpReferralTypes}">
				<form:radiobutton id="followUpReferralType.${followUpReferralType.name}" path="followUpReferralType" value="${followUpReferralType.name}" class="fieldValue"/>
				<label for="followUpReferralType.${followUpReferralType.name}" class="fieldLabelValue"><fmt:message key="referralTypeLabel.${followUpReferralType.name}" bundle="${healthBundle}"/></label>
			</c:forEach>
			<form:errors path="followUpReferralType" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="followUpAsap" class="fieldLabel">
				<fmt:message key="followUpAsapLabel" bundle="${healthBundle}"/></form:label>
			<form:checkbox id="followUpAsap" path="followUpAsap" class="fieldLabelValue"/>
			<form:errors path="followUpAsap" cssClass="error"/>
		</span>
		<sec:authorize access="hasRole('DISABLED')">
			<span class="fieldGroup">
				<form:label path="followUpLabsRequired" class="fieldLabel">
					<fmt:message key="followUpLabsRequiredLabel" bundle="${healthBundle}"/></form:label>
				<form:checkbox id="followUpLabsRequired" path="followUpLabsRequired"/>
				<form:errors path="followUpLabsRequired" cssClass="error"/>
			</span>
		</sec:authorize>
		<span class="fieldGroup">
			<form:label path="followUpRequestProviderLevel" class="fieldLabel">
				<fmt:message key="followUpRequestProviderLevelLabel" bundle="${healthBundle}"/>
			</form:label>
			<form:select path="followUpRequestProviderLevel">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemLabel="name" itemValue="id" items="${followUpRequestProviderLevels}"/>
			</form:select>
			<form:errors cssClass="error" path="followUpRequestProviderLevel"/>
		</span>
		<span class="fieldGroup">
			<form:label path="followUpRequestNotes" class="fieldLabel">
				<fmt:message key="followUpRequestNotesLabel" bundle="${healthBundle}"/>
			</form:label>
			<form:textarea path="followUpRequestNotes" />
			<form:errors cssClass="error" path="notes"/>
		</span>