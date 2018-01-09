<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Nov 30, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleboarditinerary.msgs.paroleBoardItinerary">
<form:form commandName="paroleBoardItineraryForm" class="editForm">
<fieldset>
	<legend><fmt:message key="paroleBoardItineraryDetailsTitle"/></legend>
	
	<span class="fieldGroup">
		<form:label path="startDate" class="fieldLabel">
			<fmt:message key="startDateLabel"/></form:label>
		<form:input path="startDate" class="date"/> 
		<form:errors path="startDate" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="endDate" class="fieldLabel">
			<fmt:message key="endDateLabel"/></form:label>
		<form:input path="endDate" class="date"/> 
		<form:errors path="endDate" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="location" class="fieldLabel">
			<fmt:message key="locationLabel"/></form:label>
		<form:select path="location">
			<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
			<form:options items="${locations}" itemLabel="organization.name" itemValue="id"/>
		</form:select>
		<form:errors path="location" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="boardMember1" class="fieldLabel">
			<fmt:message key="boardMember1Label"/></form:label>
		<c:set var="boardMember" value="${paroleBoardItineraryForm.boardMember1}" scope="request"/>
		<form:select path="boardMember1">
			<jsp:include page="/WEB-INF/views/paroleBoardItinerary/includes/boardMemberOptions.jsp"/>
		</form:select>
		<form:errors path="boardMember1" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="boardMember2" class="fieldLabel">
			<fmt:message key="boardMember2Label"/></form:label>
		<c:set var="boardMember" value="${paroleBoardItineraryForm.boardMember2}" scope="request"/>
		<form:select path="boardMember2">
			<jsp:include page="/WEB-INF/views/paroleBoardItinerary/includes/boardMemberOptions.jsp"/>
		</form:select>
		<form:errors path="boardMember2" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="boardMember3" class="fieldLabel">
			<fmt:message key="boardMember3Label"/></form:label>
		<c:set var="boardMember" value="${paroleBoardItineraryForm.boardMember3}" scope="request"/>
		<form:select path="boardMember3">
			<jsp:include page="/WEB-INF/views/paroleBoardItinerary/includes/boardMemberOptions.jsp"/>
		</form:select>
		<form:errors path="boardMember3" cssClass="error"/>
	</span>
	
	<span class="fieldGroup">
		<form:label path="boardMemberAlternate" class="fieldLabel">
			<fmt:message key="boardMemberAlternateLabel"/></form:label>
		<c:set var="boardMember" value="${paroleBoardItineraryForm.boardMemberAlternate}" scope="request"/>
		<form:select path="boardMemberAlternate">
			<jsp:include page="/WEB-INF/views/paroleBoardItinerary/includes/boardMemberOptions.jsp"/>
		</form:select>
		<form:errors path="boardMemberAlternate" cssClass="error"/>
	</span>
</fieldset>
<fieldset id="boardMeetingSitesHolder">
	<legend><fmt:message key="boardMeetingSitesTitle"/></legend>
	<jsp:include page="boardMeetingSitesTable.jsp"/>
	<form:errors path="boardMeetingSiteItems" cssClass="error"/>
</fieldset>
<fieldset id="boardItineraryNotesHolder">
	<legend><fmt:message key="boardItineraryNotesTitle"/></legend>
	<jsp:include page="boardItineraryNotesTable.jsp"/>
	<form:errors path="boardItineraryNoteItems" cssClass="error"/>
</fieldset>
<c:if test="${not empty paroleBoardItinerary}">
<c:set var="updatable" value="${paroleBoardItinerary}" scope="request"/>
<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
</c:if>
<p class="buttons">
	<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
</p>
</form:form>
</fmt:bundle>