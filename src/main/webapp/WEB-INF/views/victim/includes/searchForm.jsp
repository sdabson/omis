<%--
  - Victim search form.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.victim.msgs.victim">
<form id="searchForm" method="POST">
<p>
	<label><fmt:message key="searchVictimsByLabel"/></label>
</p>
<p>
	<c:choose>
	<c:when test="${victimSearchForm.type.name eq 'NAME'}">
		<input type="radio" id="searchTypeNameRadio" name="type" value="NAME" checked="checked"/>
	</c:when>
	<c:otherwise>
		<input type="radio" id="searchTypeNameRadio" name="type" value="NAME"/>
	</c:otherwise>
	</c:choose>
	<label for="searchTypeName" id="searchTypeNameLabel"><fmt:message key="searchVictimByLastNameLabel"/></label>
	<input id="searchTypeName" name="lastName" value="${victimSearchForm.lastName}"/>
	<label for="firstName" id="firstNameLabel"><fmt:message key="searchVictimByFirstNameLabel"/></label>
	<input id="firstName" name="firstName" value="${victimSearchForm.firstName}"/>
	<form:errors path="lastName" cssClass="error"/>
</p>
<p class="buttons">
	<input type="submit" value="<fmt:message key='searchVictimsLabel'/>"/>
</p>
</form>
</fmt:bundle>