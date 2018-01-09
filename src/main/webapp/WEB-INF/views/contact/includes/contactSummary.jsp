<%--
  - Contact summary.
  -
  - Author: Stephen Abson, Joel Norris
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.contact.msgs.contact" var="contact"/>
<c:if test="${not empty contactSummary}">
	<span class="contactSummarySection">
<%-- 		<c:if test="${contactSummary.personIdentity}"> --%>
				<span class="personSummarySubSection">
					<c:set value="${contactSummary}" var="personSummarizable" scope="request"/>
					<jsp:include page="../../person/includes/personSummary.jsp"/>
				</span>
<%-- 		</c:if> --%>
	</span> 
	<span class="contactSummarySection">
		<c:if test="${contactSummary.address}">
			<span class="contactSummarySubSection">
				<c:set value="${contactSummary}" var="addressSummarizable" scope="request"/>
				<label class="subSectionLabel"><fmt:message key="addressLabel" bundle="${contact}"/></label>
				<jsp:include page="../../address/includes/addressSummary.jsp"/>
			</span>
		</c:if>
		<c:if test="${contactSummary.telephoneNumber}">
			<span class="contactSummarySubSection">
				<c:set value="${contactSummary}" var="telephoneNumberSummarizable" scope="request"/>
				<label class="subSectionLabel"><fmt:message key="telephoneLabel" bundle="${contact}"/></label>
				<jsp:include page="telephoneNumberSummary.jsp"/>
			</span>
		</c:if>
		<c:if test="${contactSummary.onlineAccount}">
			<span class="contactSummarySubSection">
				<c:set value="${contactSummary}" var="onlineAccountSummarizable" scope="request"/>
				<label class="subSectionLabel"><fmt:message key="onlineAccountLabel" bundle="${contact}"/></label>
				<jsp:include page="onlineAccountSummary.jsp"/>
			</span>
		</c:if>
		<c:if test="${contactSummary.poBox}">
			<span class="contactSummarySubSection">
				<c:set value="${contactSummary}" var="poBoxSummarizable" scope="request"/>
				<label class="subSectionLabel"><fmt:message key="poBoxLabel" bundle="${contact}"/></label>
				<jsp:include page="poBoxSummary.jsp"/>
			</span>
		</c:if>
	</span>
</c:if>