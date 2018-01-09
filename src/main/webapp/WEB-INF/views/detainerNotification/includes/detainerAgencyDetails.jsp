<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
	<span class="detainerAgencyDetail"><c:out value="${detainerAgencySummary.telephoneNumber}"/></span>
	<span class="detainerAgencyDetail"><c:out value="${detainerAgencySummary.addressValue}"/></span>
	<span class="detainerAgencyDetail"><c:out value="${detainerAgencySummary.addressCityName}, ${detainerAgencySummary.addressStateAbbreviation} ${detainerAgencySummary.addressZipCodeValue}"/></span>
</fmt:bundle>