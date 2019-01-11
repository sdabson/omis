<%--
 - Author: Ryan Johns
 - Author: Kelly Churchill
 - Author: Jason Nelson
 - Author: Stephen Abson
 - Version: 0.1.0 (Apr 16, 2014)
 - Since: OMIS 3.0
 --%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<form id="filterForm" method="GET" action="${pageContext.request.contextPath}/health/referral/referralCenter.html">
	<input type="hidden" name="facility" value="${facility.id}"/>
	<div id="filterControlContainer" class="filterControlContainer">
	<span class="titleHeader"><fmt:message key="filterToolsTitle" bundle="${healthBundle}"/></span>
	<span class="seperator"><fmt:message key="offenderLabel" bundle="${healthBundle}"/></span>
	<span class="fieldGroup">
		<label for="filterByOffenderSearchText"><fmt:message key="offenderLabel" bundle="${healthBundle}"/></label>
		<input id="filterByOffenderSearchText" name="filterByOffenderSearchText" type="text" value="<c:if test='${not empty offender}'>${offender.name.lastName}, ${offender.name.firstName} ${offender.name.middleName} (${offender.offenderNumber})</c:if>"/>
		<a class="clearLink" href="${pageContext.request.contextPath}/health/referral/referralCenter.html?facility=${facility.id}&amp;filterByReferralType=${referralType.name}&amp;filterByStartDate=<fmt:formatDate value='${startDate}' pattern='MM/dd/yyyy'/>&amp;filterByEndDate=<fmt:formatDate value='${endDate}' pattern='MM/dd/yyyy'/>&amp;filterByAppointmentStatus=${appointmentStatus.name}&searchType=OFFENDER"><span class="linkLabel"><fmt:message key="clearLink" bundle="${commonBundle}"/></span></a>
		<input id="filterByOffender" type="hidden" name="filterByOffender" value="${offender.id}"/>
	</span>
	<p class="buttons">
		<button type="submit" name="searchType" value="OFFENDER"><fmt:message key="searchLabel" bundle="${commonBundle}"/></button>
	</p>
	<span class="seperator"><fmt:message key="referralsTitle" bundle="${healthBundle}"/></span>
	<span class="fieldGroup">
		<label for="filterByReferralType"><fmt:message key="referralTypeLabel" bundle="${healthBundle}"/></label> 
		<select name="filterByReferralType">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:choose>
				<c:when test="${'ALL' eq referralType.name}">
					<option value="ALL" selected="selected"><fmt:message key="referralTypeLabel.ALL" bundle="${healthBundle}"/></option>
				</c:when>
				<c:otherwise>
					<option value="ALL"><fmt:message key="referralTypeLabel.ALL" bundle="${healthBundle}"/></option>
				</c:otherwise>
			</c:choose>
			<c:forEach var="referralTypeItr" items="${referralTypes}">
				<c:choose>
					<c:when test="${referralType eq referralTypeItr}">
						<option value="${referralTypeItr.name}" selected="selected"><fmt:message key="referralTypeLabel.${referralTypeItr.name}" bundle="${healthBundle}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${referralTypeItr.name}"><fmt:message key="referralTypeLabel.${referralTypeItr.name}" bundle="${healthBundle}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>			
	</span>
	<span class="fieldGroup">
		<label for="filterByStartDate"><fmt:message key="startDateLabel" bundle="${commonBundle}"/></label>
		<input id="filterByStartDate" name="filterByStartDate" type="text" class="date" value="<fmt:formatDate value='${filterByStartDate}' pattern='MM/dd/yyyy'/>"/>
	</span>
	<span class="fieldGroup">
		<label for="filterByEndDate"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></label>
		<input id="filterByEndDate" name="filterByEndDate" type="text" class="date" value="<fmt:formatDate value='${filterByEndDate}' pattern='MM/dd/yyyy'/>"/>			
	</span>
	<span class="fieldGroup">
		<label for="filterByAppointmentStatus"><fmt:message key="appointmentStatusLabel" bundle="${healthBundle}"/></label> 
		<select name="filterByAppointmentStatus">
			<c:choose>
				<c:when test="${empty filterByAppointmentStatus}">
					<option value="" selected="selected"><fmt:message key="allHealthAppointmentStatusesLabel" bundle="${healthBundle}"/></option>
				</c:when>
				<c:otherwise>
					<option value=""><fmt:message key="allHealthAppointmentStatusesLabel" bundle="${healthBundle}"/></option>
				</c:otherwise>
			</c:choose>
			<c:forEach var="appointmentStatus" items="${appointmentStatuses}">
				<c:choose>
					<c:when test="${filterByAppointmentStatus eq appointmentStatus}">
						<option value="${appointmentStatus.name}" selected="selected"><fmt:message key="healthAppointmentStatusLabel.${appointmentStatus.name}" bundle="${healthBundle}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${appointmentStatus.name}"><fmt:message key="healthAppointmentStatusLabel.${appointmentStatus.name}" bundle="${healthBundle}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</span>
	<p class="buttons">
		<button type="submit" name="searchType" value="REFERRAL"><fmt:message key="searchLabel" bundle="${commonBundle}"/></button>
	</p>
</div>
<br>
<br>
<br>
<div>
	<c:if test="${enableSearchResultCountLimit && not empty exceededSearchResultType}">
		<c:choose>
			<c:when test="${exceededSearchResultType eq 'AUTHORIZED_REFERRAL_REQUEST'}">
				<fmt:message key="warningMessageLabelAR" bundle="${healthBundle}">
					<fmt:param value="${countLimit}"/>
				</fmt:message>
			</c:when>
			<c:when test="${exceededSearchResultType eq 'EXTERNAL_REFERRAL_REQUEST'}">
				<fmt:message key="warningMessageLabelER" bundle="${healthBundle}">
					<fmt:param value="${countLimit}"/>
				</fmt:message>
			</c:when>
			<c:when test="${exceededSearchResultType eq 'HEALTH_REQUEST'}">
				<fmt:message key="warningMessageLabelH" bundle="${healthBundle}">
					<fmt:param value="${countLimit}"/>
				</fmt:message>
			</c:when>
			<c:when test="${exceededSearchResultType eq 'INTERNAL_REFERRAL_REQUEST'}">
				<fmt:message key="warningMessageLabelIR" bundle="${healthBundle}">
					<fmt:param value="${countLimit}"/>
				</fmt:message>
			</c:when>
			<c:when test="${exceededSearchResultType eq 'PENDING_REFERRAL_AUTHORIZATION_REQUEST'}">
				<fmt:message key="warningMessageLabelPRA" bundle="${healthBundle}">
					<fmt:param value="${countLimit}"/>
				</fmt:message>
			</c:when>
			<c:when test="${exceededSearchResultType eq 'REFERRAL_REQUEST'}">
				<fmt:message key="warningMessageLabelR" bundle="${healthBundle}">
					<fmt:param value="${countLimit}"/>
				</fmt:message>
			</c:when>
		</c:choose>
	</c:if>
</div>
</form>