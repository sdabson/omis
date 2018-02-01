<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.boardhearing.msgs.boardHearing">
<c:if test="${not empty boardHearingSummary}">
<div id="boardHearingHeader">
	<fieldset class="foregroundUltraLight">
		<legend class="foregroundLight"><fmt:message key="hearingDetailsTitle" /></legend>
		<span class="fieldGroup">
			<label class="fieldLabel detailsLabel">
				<fmt:message key="locationLabel"/>
			</label>
			<span class="detail">
				<c:out value="${boardHearingSummary.hearingLocationName}"/>
			</span>
		</span>
		<span class="fieldGroup">
			<label class="fieldLabel detailsLabel">
				<fmt:message key="dateLabel"/>
			</label>
			<span class="detail">
				<fmt:formatDate value="${boardHearingSummary.hearingDate}" pattern="MM/dd/yyyy" />
			</span>
		</span>
		<c:if test="${not empty boardHearingSummary.appearanceCategoryName}">
			<span class="fieldGroup">
				<label class="fieldLabel detailsLabel">
					<fmt:message key="appearanceTypeLabel"/>
				</label>
				<span class="detail">
					<c:out value="${boardHearingSummary.appearanceCategoryName}"/>
				</span>
			</span>
		</c:if>
	</fieldset>
</div>
</c:if>
</fmt:bundle>